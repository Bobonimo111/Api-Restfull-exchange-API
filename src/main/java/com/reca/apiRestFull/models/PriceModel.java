package com.reca.apiRestFull.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRICE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate")
    @Comment("Valuation for base in determinant timestap")
    private Double rate;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "value")
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_id")
    private CurrencieModel base;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
