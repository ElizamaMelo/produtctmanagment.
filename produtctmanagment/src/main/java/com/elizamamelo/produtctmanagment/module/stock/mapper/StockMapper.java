package com.elizamamelo.produtctmanagment.module.stock.mapper;

import com.elizamamelo.produtctmanagment.module.stock.dto.StockResponse;
import com.elizamamelo.produtctmanagment.module.stock.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockMapper {

    public StockResponse toResponse(Stock stock) {
        return new StockResponse(
                stock.getId(),
                stock.getMinimumStock(),
                stock.getMaximumStock(),
                stock.getCurrentStock());

    }

    public List<StockResponse> toList(List<Stock> objects) {
        List<StockResponse> stocks = new ArrayList<>();

        objects.forEach(obj -> {
            stocks.add(this.toResponse(obj));
        });
        return stocks;
    }
}
