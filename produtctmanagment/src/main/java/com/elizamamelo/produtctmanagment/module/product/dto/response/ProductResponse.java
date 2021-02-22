package com.elizamamelo.produtctmanagment.module.product.dto.response;

import com.elizamamelo.produtctmanagment.module.stock.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String code;
    private BigDecimal price;
    private Stock stock;

}
