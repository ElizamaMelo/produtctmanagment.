package com.elizamamelo.produtctmanagment.module.stock.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {

    @NotNull
    @NotBlank(message = "Current quantity is required")
    private Integer currentQuantity;
}

