package com.elizamamelo.produtctmanagment.module.product.service;

import com.elizamamelo.produtctmanagment.enums.Error;
import com.elizamamelo.produtctmanagment.exception.ProductException;
import com.elizamamelo.produtctmanagment.exception.UserAccountException;
import com.elizamamelo.produtctmanagment.module.product.dto.request.ProductRequest;
import com.elizamamelo.produtctmanagment.module.product.dto.response.ProductResponse;
import com.elizamamelo.produtctmanagment.module.product.mapper.ProductMapper;
import com.elizamamelo.produtctmanagment.module.product.model.Product;
import com.elizamamelo.produtctmanagment.module.product.repository.ProductRepository;
import com.elizamamelo.produtctmanagment.module.stock.model.Stock;
import com.elizamamelo.produtctmanagment.module.stock.repository.StockRepository;
import com.elizamamelo.produtctmanagment.module.userAccount.dto.response.UserAccountResponse;
import com.elizamamelo.produtctmanagment.module.userAccount.model.UserAccount;
import com.elizamamelo.produtctmanagment.module.userAccount.service.UserAccountServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private UserAccountServices userAccountServices;


    public ProductResponse create(ProductRequest request) throws Exception {

        try {

            Stock stock = Stock.builder()
                    .currentStock(request.getStock().getCurrentQuantity())
                    .build();
            Stock savedStock = stockRepository.saveAndFlush(stock);

            UserAccount user = userAccountServices.getLoggedUser();

            Product product = Product.builder()
                    .name(request.getName())
                    .code(request.getCode())
                    .price(request.getPrice())
                    .stock(savedStock)
                    .userAccount(user)
                    .build();

            Product savedProduct = productRepository.saveAndFlush(product);
            return mapper.toResponse(savedProduct);
        } catch (Exception e) {
            throw new Exception("Server error has ocurred", e);
        }
    }

    public List<Product> findByNameContaining(String name) {
        return this.productRepository.findByNameContainingAndUserAccount(name, this.userAccountServices.getLoggedUser())
                .orElseThrow(() -> new UserAccountException(Error.PRODUCT_NOT_FOUND.getError()));
    }

    public ProductResponse findByIdAndLoggedUser(Long id) throws Exception {
        try {
            UserAccount userAccount = userAccountServices.getLoggedUser();
            Product product = productRepository.findByIdAndUserAccount(id, userAccount);

            return mapper.toResponse(product);
        } catch (Exception e) {
            throw new ProductException(Error.PRODUCT_NOT_FOUND.getError());
        }
    }




}

