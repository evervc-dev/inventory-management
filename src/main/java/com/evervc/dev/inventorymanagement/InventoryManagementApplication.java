package com.evervc.dev.inventorymanagement;

import com.evervc.dev.inventorymanagement.entity.Role;
import com.evervc.dev.inventorymanagement.entity.User;
import com.evervc.dev.inventorymanagement.repository.RoleRepository;
import com.evervc.dev.inventorymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class InventoryManagementApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String @NonNull ... args) throws Exception {
		saveRole("USER");
		saveRole("ADMIN");
		saveAdmin();
	}

	private void saveRole(String role) {
		if (roleRepository.existsByName(role)) {
			log.warn("No se pudo crear el rol [{}] porque ya existe en la base de datos.", role);
		} else {
			roleRepository.save(Role.builder().name(role).build());
			log.info("Rol [{}] creado exitosamente.", role);
		}
	}

	private void saveAdmin() {
		if (userRepository.existsByEmail("admin@admin.com")) {
			log.warn("No se pudo crear el usuario porque ya existe en la base de datos.");
			return;
		}

		List<Role> roles = List.of(getRole("USER"), getRole("ADMIN"));

		User admin = User.builder()
				.firstName("Super")
				.lastName("Admin")
				.email("admin@admin.com")
				.password(passwordEncoder.encode("Adm1n@32"))
				.enabled(true)
				.roles(roles)
				.build();


		User adminSaved = userRepository.save(admin);
		log.info("Usuario [{} {}] creado exitosamente.", adminSaved.getFirstName(), adminSaved.getLastName());
	}

	private Role getRole(String roleName) {
		Optional<Role> optnlRole = roleRepository.findByName(roleName);
		Role role = null;

		if (optnlRole.isPresent()) {
			role = optnlRole.get();
		} else {
			log.error("El rol [{}] no existe en la base de datos... Pasando a crear rol", roleName);
		}

		return role;
	}
}
