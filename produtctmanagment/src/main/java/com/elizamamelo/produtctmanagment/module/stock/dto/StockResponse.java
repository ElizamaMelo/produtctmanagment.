package com.elizamamelo.produtctmanagment.module.stock.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {

    private Long id;
    private Integer minQuantity;
    private Integer maxQuantity;
    private Integer currentQuantity;
}
