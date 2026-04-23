package com.evervc.dev.inventorymanagement.entity;

import com.evervc.dev.inventorymanagement.entity.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "stock_movements")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private MovementType type;
}
