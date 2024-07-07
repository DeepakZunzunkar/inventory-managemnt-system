package com.dz.ims.service.impl;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.StockMasterDto;
import com.dz.ims.dto.StockProductDto;
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
    public BaseResponse<StockMaster> addUpdateStock(StockMaster stockMaster) {
        try {
           StockMaster master = stockMasterRepository.findStockProductById(stockMaster.getStockProduct().getId());
            if(master!=null){
                    master.getBaseProperties().setUpdatedBy("SYSTEM");
                    master.getBaseProperties().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    Integer totalQuantity= master.getQuantity() + stockMaster.getQuantity();
                    master.setQuantity(totalQuantity);
                    stockMasterRepository.save(master);
                return BaseResponse.<StockMaster>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("added successfully..")
                        .build();
            }else{
                stockMaster.setBaseProperties(new BaseProperties("SYSTEM",new Timestamp(System.currentTimeMillis()),null,null));
                stockMasterRepository.save(stockMaster);
                return BaseResponse.<StockMaster>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("updated successfully..")
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
