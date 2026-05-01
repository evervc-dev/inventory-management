package com.evervc.dev.inventorymanagement.entity;

import com.evervc.dev.inventorymanagement.entity.utils.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Category extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "category",  fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> products;
}
