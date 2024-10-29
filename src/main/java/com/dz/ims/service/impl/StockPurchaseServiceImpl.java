package com.dz.ims.service.impl;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.StockPurchaseDto;
import com.dz.ims.dto.SupplierMasterDto;
import com.dz.ims.entity.*;
import com.dz.ims.repository.ProductMasterRepository;
import com.dz.ims.repository.StockPurchaseRepository;
import com.dz.ims.repository.SupplierMasterRepository;
import com.dz.ims.service.StockMasterService;
import com.dz.ims.service.StockPurchaseService;
import com.dz.ims.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class StockPurchaseServiceImpl implements StockPurchaseService {

    @Autowired
    StockPurchaseRepository purchaseRepository;

    @Autowired
    ProductMasterRepository productRepository;

    @Autowired
    SupplierMasterRepository supplierMasterRepository;

    @Autowired
    StockMasterService stockMasterService;

    @Override
    public BaseResponse<?> add(StockPurchaseDto dto) {
        try{
            Optional<ProductMaster> optionalStockProduct = productRepository.findById(dto.getProduct().getId());
            if(optionalStockProduct.isPresent()){
                Optional<SupplierMaster> optionalSupplierMaster = supplierMasterRepository.findById(dto.getSupplier().getId());
                if(optionalSupplierMaster.isPresent()){
                    ModelMapper modelMapper=new ModelMapper();
                    StockPurchase entity = modelMapper.map(dto, StockPurchase.class);
                    entity.setBaseProperties(new BaseProperties("SYSTEM",new Timestamp(System.currentTimeMillis()),null,null));
                    entity = purchaseRepository.save(entity);
                    dto.setId(entity.getId());
                    // if present in stock increase stock size
                    StockMaster stockMaster = StockMaster.builder()
                            .productMaster(optionalStockProduct.get())
                            .quantity(dto.getQuantity())
                            .build();
                    BaseResponse<StockMaster> response = stockMasterService.addUpdateStock(stockMaster,"PURCHASE");
                    return BaseResponse.<StockPurchaseDto>builder()
                            .responseCode(ResponseCode.SUCCESS.value())
                            .responseMessage("added successfully..")
                            .responseData(dto)
                            .build();

                }else{
                    return BaseResponse.<StockPurchaseDto>builder()
                            .responseCode(ResponseCode.NO_CONTENT.value())
                            .responseMessage("supplier not found")
                            .build();
                }
            }else{
                return BaseResponse.<StockPurchaseDto>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("product not found")
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<SupplierMasterDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }
}
