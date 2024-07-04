package com.dz.ims.service.impl;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.StockProductDto;
import com.dz.ims.entity.BaseProperties;
import com.dz.ims.entity.StockProduct;
import com.dz.ims.repository.StockProductRepository;
import com.dz.ims.service.StockProductService;
import com.dz.ims.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StockProductServiceImpl implements StockProductService {

    @Autowired
    StockProductRepository productRepository;


    @Override
    public BaseResponse<StockProductDto> addProduct(StockProductDto productDto) {
        try{
            ModelMapper modelMapper=new ModelMapper();
            StockProduct productEntity = modelMapper.map(productDto,StockProduct.class);
            productEntity.setBaseProperties(new BaseProperties("SYSTEM",new Timestamp(System.currentTimeMillis()),null,null));
            productEntity = productRepository.save(productEntity);
            productDto.setId(productEntity.getId());
            return BaseResponse.<StockProductDto>builder()
                    .responseCode(ResponseCode.SUCCESS.value())
                    .responseMessage("product added successfully..")
                    .responseData(productDto)
                    .build();

        }catch (Exception e){
            return BaseResponse.<StockProductDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public Long updateProduct(StockProduct product) {
        return null;
    }

    @Override
    public Long deleteProduct(StockProduct product) {
        return null;
    }

    @Override
    public StockProduct getProductById(StockProduct product) {
        return null;
    }

    @Override
    public List<StockProduct> getAllProduct(StockProduct product) {
        return null;
    }
}
