package com.reca.apiRestFull.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_CURRENCIE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "symbols",length = 3)
    private String symbols;

    @Column(name = "name",length = 256)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
