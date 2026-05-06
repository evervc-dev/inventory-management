package com.evervc.dev.inventorymanagement.entity;

import com.evervc.dev.inventorymanagement.entity.enums.MovementType;
import com.evervc.dev.inventorymanagement.entity.utils.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "stock_movements")
@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class StockMovement extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private MovementType type;
}
