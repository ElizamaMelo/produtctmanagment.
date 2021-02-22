package com.elizamamelo.produtctmanagment.module.product.repository;

import com.elizamamelo.produtctmanagment.module.product.model.Product;
import com.elizamamelo.produtctmanagment.module.userAccount.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

    Optional<Product> findByCodeAndUserAccount(String code, UserAccount userAccount);

    Optional<List<Product>> findByNameContainingAndUserAccount(String name, UserAccount userAccount);

    Product findByIdAndUserAccount(Long id, UserAccount userAccount);
}
