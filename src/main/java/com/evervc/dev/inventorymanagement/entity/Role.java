package com.evervc.dev.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
