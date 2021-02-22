package com.elizamamelo.produtctmanagment.module.product.mapper;

import com.elizamamelo.produtctmanagment.module.product.dto.response.ProductResponse;
import com.elizamamelo.produtctmanagment.module.product.model.Product;
import com.elizamamelo.produtctmanagment.module.userAccount.dto.response.UserAccountResponse;
import com.elizamamelo.produtctmanagment.module.userAccount.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getPrice(),
                product.getStock());

    }

    public List<ProductResponse> toList(List<Product> objects) {
        List<ProductResponse> products = new ArrayList<>();

        objects.forEach(obj -> {
            products.add(this.toResponse(obj));
        });
        return products;
    }
}
