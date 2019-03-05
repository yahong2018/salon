package com.hy.salon.basic.service;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StockTransferApplicationDao;
import com.hy.salon.basic.dao.StockTransferApplicationItemDao;
import com.hy.salon.basic.entity.StockTransferApplication;
import com.hy.salon.basic.entity.StockTransferApplicationItem;
import com.hy.salon.basic.vo.StockTransferApplicationVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("stockTransferApplicationService")
public class StockTransferApplicationService {
    @Resource(name = "stockTransferApplicationDao")
    private StockTransferApplicationDao stockTransferApplicationDao;
    @Resource(name = "stockTransferApplicationItemDao")
    private StockTransferApplicationItemDao stockTransferApplicationItemDao;

    public void addStockTransferApplication(StockTransferApplicationVo stockTransferApplicationVo) {
        StockTransferApplication stockTransferApplication=new StockTransferApplication();
        stockTransferApplication.setApplicationNo(stockTransferApplicationVo.getApplicationNo());
        stockTransferApplication.setOutWarehouseId(stockTransferApplicationVo.getOutWarehouseId());
        //stockTransferApplication.setRecordId(stockTransferApplicationVo.getRecordId());
        stockTransferApplication.setRecordStatus(StatusUtil.TIJ);
        stockTransferApplication.setRemarkApplication(stockTransferApplicationVo.getRemarkApplication());
        stockTransferApplication.setRemarkAudit(stockTransferApplicationVo.getRemarkAudit());
        stockTransferApplicationDao.insert(stockTransferApplication);

        StockTransferApplicationItem stockTransferApplicationItem=new StockTransferApplicationItem();
        stockTransferApplicationItem.setQty(stockTransferApplicationVo.getQty());
        stockTransferApplicationItem.setStockTransferApplicationId(stockTransferApplication.getRecordId());
        stockTransferApplicationItem.setProductId(stockTransferApplicationVo.getProductId());
        stockTransferApplicationItemDao.insert(stockTransferApplicationItem);
    }

    public List<StockTransferApplication> getStockTransferApplication(Integer recordStatus) {
        return stockTransferApplicationDao.getStockTransferApplication(recordStatus);
    }
}
