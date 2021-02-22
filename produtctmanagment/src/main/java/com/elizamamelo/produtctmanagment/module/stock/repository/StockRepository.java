package com.elizamamelo.produtctmanagment.module.stock.repository;

import com.elizamamelo.produtctmanagment.module.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}