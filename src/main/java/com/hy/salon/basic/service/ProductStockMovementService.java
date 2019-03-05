package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ProductStockMovementDao;
import com.hy.salon.basic.dao.ReservationDao;
import com.hy.salon.basic.entity.ProductStockMovement;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("productStockMovementService")
public class ProductStockMovementService {
    @Resource(name = "productStockMovementDao")
    private ProductStockMovementDao productStockMovementDao;

    public List<ProductStockMovement> getProductBymovementType(Long movementType) {
        return productStockMovementDao.getProductBymovementType(movementType);
    }
}
