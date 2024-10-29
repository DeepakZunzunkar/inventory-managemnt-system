package com.dz.ims.service.impl;

import com.dz.ims.dto.*;
import com.dz.ims.entity.*;
import com.dz.ims.repository.SalesMasterRepository;
import com.dz.ims.service.SaleMasterService;
import com.dz.ims.service.StockMasterService;
import com.dz.ims.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleMasterServiceImpl implements SaleMasterService {

    @Autowired
    SalesMasterRepository salesMasterRepository;

    @Autowired
    StockMasterService stockMasterService;

    @Override
    public BaseResponse<?> add(SaleMasterDto dto) {
        try{
            if(dto.getSaleProductMapping()!=null && !dto.getSaleProductMapping().isEmpty()){

                ModelMapper modelMapper=new ModelMapper();
                SaleMaster entity = modelMapper.map(dto, SaleMaster.class);
                entity.setBaseProperties(new BaseProperties("SYSTEM",new Timestamp(System.currentTimeMillis()),null,null));


               /* this code gives stackoverflow issue as there is cyclic  reference SaleMaster and SaleProductMapping , have same getter method cause this issues
               entity.getSaleProductMapping()
                        .forEach(saleItem->{
                            StockProduct stockProduct = saleItem.getStockProduct();
//                            here we will not validate the product , will validate stock quantity
//                            or in front end when selecting product there will be check to validate the stock for the product is available or not
                            saleItem.setBaseProperties(new BaseProperties("SYSTEM",new Timestamp(System.currentTimeMillis()),null,null));
                            saleItem.setSaleMaster(entity);
                        });*/


                List<SaleProductMapping> saleProductMappings = new ArrayList<>();
                for (SaleProductMappingDto saleItemDto : dto.getSaleProductMapping()) {
                    SaleProductMapping saleItem = modelMapper.map(saleItemDto, SaleProductMapping.class);
//                    saleItem.setBaseProperties(new BaseProperties("SYSTEM", new Timestamp(System.currentTimeMillis()), null, null));
                    saleItem.setSaleMaster(entity);
                    saleProductMappings.add(saleItem);
                }
                entity.setSaleProductMapping(saleProductMappings);
                entity = salesMasterRepository.save(entity);
                dto.setId(entity.getId());

                // if present in stock decrease stock size
                entity.getSaleProductMapping().forEach(saleItem ->{
                    StockMaster stockMaster = StockMaster.builder()
                            .productMaster(saleItem.getProductMaster())
                            .quantity(saleItem.getQuantity())
                            .build();
                    BaseResponse<StockMaster> response = stockMasterService.addUpdateStock(stockMaster,"SALE");
                });
                return BaseResponse.<SaleMasterDto>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("successfully added")
                        .responseData(dto)
                        .build();

            }else{
                return BaseResponse.<SaleMasterDto>builder()
                        .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                        .responseMessage("minimum one product should be added")
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<SaleMasterDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }
}
