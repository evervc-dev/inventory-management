package com.evervc.dev.inventorymanagement.repository;

import com.evervc.dev.inventorymanagement.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByActiveTrue(Pageable pageable);

    // Retorna todas las categorías y sus productos, se usa left
    // para que traiga categorías aunque no tengan productos asociados
    @Query("select c from Category c left join fetch c.products where c.active=true")
    Page<Category> findAllByActiveTrueWithProducts(Pageable pageable);

    Optional<Category> findByIdAndActiveTrue(Long id);

    @Query("select c from Category c left join fetch c.products where c.id=?1 and c.active=true")
    Optional<Category> findByIdAndActiveTrueWithProducts(Long id);

    Optional<Category> findByNameAndActiveTrue(String name);

    boolean existsByName(String name);
}