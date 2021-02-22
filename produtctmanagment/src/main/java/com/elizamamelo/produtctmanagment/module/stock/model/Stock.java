package com.elizamamelo.produtctmanagment.module.stock.model;

import com.elizamamelo.produtctmanagment.module.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Min(200)
    @JsonIgnore
    private Integer minimumStock;

    @Max(1000)
    @JsonIgnore
    private Integer maximumStock;

    @Column(name = "current_stock")
    private Integer currentStock;

    /* Relationship References */

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToOne
    private Product product;

}
