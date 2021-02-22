package com.elizamamelo.produtctmanagment.module.product.model;

import com.elizamamelo.produtctmanagment.module.stock.model.Stock;
import com.elizamamelo.produtctmanagment.module.userAccount.model.UserAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "name", length = 60, unique = true)
    private String name;

    @NotNull
    @Column(name = "price")
    private BigDecimal price = BigDecimal.ZERO;

    /* Relationship References */


    @EqualsAndHashCode.Exclude
    @NotNull
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private Stock stock;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne
    private UserAccount userAccount;
}
