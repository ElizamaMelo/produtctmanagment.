package com.elizamamelo.produtctmanagment.module.product.dto.request;


import com.elizamamelo.produtctmanagment.module.stock.dto.StockRequest;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@ToString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Code is required")
    private String code;

    private BigDecimal price;

    private StockRequest stock;
}


