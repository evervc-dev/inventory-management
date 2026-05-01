package com.evervc.dev.inventorymanagement;

import com.evervc.dev.inventorymanagement.entity.Role;
import com.evervc.dev.inventorymanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class InventoryManagementApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String @NonNull ... args) throws Exception {
		saveRole("USER");
		saveRole("ADMIN");
	}

	private void saveRole(String role) {
		if (roleRepository.existsByName(role)) {
			log.warn("No se pudo crear el rol [{}] porque ya existe en la base de datos.", role);
		} else {
			roleRepository.save(Role.builder().name(role).build());
			log.info("Rol [{}] creado exitosamente.", role);
		}
	}
}
