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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "cl_value")
    private Double value;

    @Column(name = "cl_base")
    private String base;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currencie_id")
    private CurrencieModel currencie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
