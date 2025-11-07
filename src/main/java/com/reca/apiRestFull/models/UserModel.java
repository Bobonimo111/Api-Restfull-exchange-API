package com.reca.apiRestFull.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID) // Não precisa especificar mais eu especifiquei
    private UUID id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password",length = 256)
    private String password;

    //Posso salvar currencies ao salvar usuario, e ao remover um usuario, as currencies cadastradas irão permanecer
    @OneToMany(mappedBy = "user" ,cascade = {CascadeType.PERSIST},orphanRemoval = false)
    private List<CurrencieModel> currencies;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST},orphanRemoval = false)
    private List<PriceModel> price;

}
