package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ProductStockMovementDao;
import com.hy.salon.basic.entity.ProductStockMovement;
import com.hy.salon.basic.vo.ProductStockMovementVo;
import com.hy.salon.stock.dao.ProductSockDAO;
import org.springframework.stereotype.Component;
import com.hy.salon.stock.entity.ProductStock;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productStockMovementService")
public class ProductStockMovementService {
    @Resource(name = "productStockMovementDao")
    private ProductStockMovementDao productStockMovementDao;

    @Resource(name = "productSockDAO")
    private ProductSockDAO ProductSockDAO;

    public List<ProductStockMovement> getProductBymovementType(Integer recordCreateType,Long movementType) {
        return productStockMovementDao.getProductBymovementType(recordCreateType,movementType);
    }

    public Boolean addProductBymovement(ProductStockMovement productStockMovement) {
        if(productStockMovement.getProductId()!=null){
            int i = productStockMovementDao.insert(productStockMovement);
        }else{
            return false;
        }
        ProductStock productStock=new ProductStock();
        productStock.setProductId(productStockMovement.getProductId());
        productStock.setWarehouseId(productStockMovement.getWarehouseId());
        productStock.setStockQty(productStockMovement.getMovementQty());
        ProductSockDAO.insert(productStock);
        return true;
    }

    public void outProductBymovement(ProductStockMovement productStockMovement) {
        productStockMovementDao.insert(productStockMovement);
        Map parameters = new HashMap();
        parameters.put("productId", productStockMovement.getProductId());
        String where = "product_id=#{productId}";
        ProductStock one = ProductSockDAO.getOne(where, parameters);
        one.setStockQty(one.getStockQty()-productStockMovement.getMovementQty());
        ProductSockDAO.update(one);
    }

    public List<ProductStockMovementVo> findProductStock(Integer movementType,String createTime,String endTime) {
        return productStockMovementDao.findProductStock(movementType,createTime,endTime);
    }

    public void updateProduct(ProductStockMovement productStockMovement) {
        productStockMovementDao.insert(productStockMovement);
        Map parameters = new HashMap();
        parameters.put("productId", productStockMovement.getProductId());
        String where = "product_id=#{productId}";
        ProductStock one = ProductSockDAO.getOne(where, parameters);
        one.setStockQty(productStockMovement.getMovementQty());
        ProductSockDAO.update(one);
    }
}
