package com.elizamamelo.produtctmanagment.module.userAccount.model;

import com.elizamamelo.produtctmanagment.module.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "username", length = 60, unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    /* Relationship References */

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER)
    private Set<Role> roles;


    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy =  "userAccount", fetch = FetchType.LAZY)
    private Set<Product> products;






}
