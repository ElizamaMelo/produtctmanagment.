package com.elizamamelo.produtctmanagment.rest.api;


import com.elizamamelo.produtctmanagment.module.product.dto.request.ProductRequest;
import com.elizamamelo.produtctmanagment.module.product.dto.response.ProductResponse;
import com.elizamamelo.produtctmanagment.module.product.model.Product;
import com.elizamamelo.produtctmanagment.module.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductRest {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody ProductRequest request) throws Exception {
        return productService.create(request);

    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findByNameOrCode (@RequestBody @PathVariable String name) throws Exception {
        return productService.findByNameContaining(name);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findByIdAndUser(@RequestBody @PathVariable Long id) throws Exception {
        return productService.findByIdAndLoggedUser(id);
    }

    /*@PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserAccountResponse update(@PathVariable Long id, @RequestBody UserAccountRequest request) throws Exception {
        return service.update(id, request);
    }*/

}
