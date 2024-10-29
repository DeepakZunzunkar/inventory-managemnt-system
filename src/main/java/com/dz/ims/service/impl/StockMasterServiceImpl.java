package com.dz.ims.service.impl;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.entity.BaseProperties;
import com.dz.ims.entity.StockMaster;
import com.dz.ims.repository.StockMasterRepository;
import com.dz.ims.service.StockMasterService;
import com.dz.ims.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class StockMasterServiceImpl implements StockMasterService {

    @Autowired
    StockMasterRepository stockMasterRepository;

    @Override
    public BaseResponse<StockMaster> addUpdateStock(StockMaster stockMaster,String flag) {
        try {
//           StockMaster master = stockMasterRepository.findStockProductById(stockMaster.getStockProduct().getId());
            StockMaster master = stockMasterRepository.findProductMastertById(stockMaster.getProductMaster().getId());
            if(master!=null){
                    master.getBaseProperties().setUpdatedBy("SYSTEM");
                    master.getBaseProperties().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    Integer totalQuantity = master.getQuantity();
                    if(flag.equalsIgnoreCase("PURCHASE")){
                        totalQuantity = master.getQuantity() + stockMaster.getQuantity();
                    }else if(flag.equalsIgnoreCase("SALE")){
                        totalQuantity= master.getQuantity() - stockMaster.getQuantity();
                    }
                    master.setQuantity(totalQuantity);
                    stockMasterRepository.save(master);
                return BaseResponse.<StockMaster>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("updated successfully..")
                        .build();
            }else if(flag.equalsIgnoreCase("PURCHASE")) {
                // first time add
                stockMaster.setBaseProperties(new BaseProperties("SYSTEM",new Timestamp(System.currentTimeMillis()),null,null));
                stockMasterRepository.save(stockMaster);
                return BaseResponse.<StockMaster>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("added successfully..")
                        .build();
            }else{
                return BaseResponse.<StockMaster>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("nothing added or updated ")
                        .build();
            }
        } catch (Exception e) {
            return BaseResponse.<StockMaster>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }
}
