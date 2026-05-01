package com.evervc.dev.inventorymanagement.entity;

import com.evervc.dev.inventorymanagement.entity.utils.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Role extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
