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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public BaseResponse<StockProductDto> updateProduct(StockProductDto product) {
        try {
            Optional<StockProduct> optionalStockProduct = productRepository.findById(product.getId());
            if(optionalStockProduct.isPresent()){
                StockProduct productEntity = optionalStockProduct.get();
                productEntity.setName(product.getName());
                productEntity.setCategory(product.getCategory());
                productEntity.setPrice(product.getPrice());
                productEntity.setDescription(product.getDescription());
                productEntity.setRemark(product.getRemark());
                productEntity.setIsActive(product.getIsActive());
                productEntity.getBaseProperties().setUpdatedBy("SYSTEM");
                productEntity.getBaseProperties().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                productRepository.save(productEntity);
                return BaseResponse.<StockProductDto>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("product updated successfully..")
                    .responseData(product)
                        .build();
            }else {
                return BaseResponse.<StockProductDto>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("product not found.")
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<StockProductDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> deleteProduct(StockProductDto product) {
        try{
            Optional<StockProduct> optionalStockProduct = productRepository.findById(product.getId());
            if(optionalStockProduct.isPresent()) {
                StockProduct productEntity = optionalStockProduct.get();
                // hard delete
                productRepository.delete(productEntity);
                return BaseResponse.<String>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("product deleted successfully.")
                        .build();
            }else{
                return BaseResponse.<String>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("product not found.")
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<String>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<StockProductDto> getProductById(Long productId) {
       try{
           Optional<StockProduct> optionalStockProduct = productRepository.findById(productId);
           if(optionalStockProduct.isPresent()){
               StockProduct productEntity = optionalStockProduct.get();
               ModelMapper modelMapper=new ModelMapper();
               StockProductDto productDto = modelMapper.map(productEntity,StockProductDto.class);
               return BaseResponse.<StockProductDto>builder()
                       .responseCode(ResponseCode.SUCCESS.value())
                       .responseMessage("success")
                       .responseData(productDto)
                       .build();
           }else{
               return BaseResponse.<StockProductDto>builder()
                       .responseCode(ResponseCode.NO_CONTENT.value())
                       .responseMessage("product not found.")
                       .build();
           }
       }catch (Exception e){
           return BaseResponse.<StockProductDto>builder()
                   .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                   .responseMessage("oops something went wrong..")
                   .build();
       }
    }

    @Override
    public BaseResponse<List<StockProductDto>> getAllProduct() {
        ArrayList<StockProductDto> list = new ArrayList<>();
        try{
            List<StockProduct> productList = productRepository.findAll();
            if(!productList.isEmpty()){
                ModelMapper modelMapper=new ModelMapper();
                productList.forEach(product->{
                    list.add(modelMapper.map(product,StockProductDto.class));
                });
                return BaseResponse.<List<StockProductDto>>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("success")
                        .responseData(list)
                        .build();
            }else{
                return BaseResponse.<List<StockProductDto>>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("products not found.")
                        .responseData(list)
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<List<StockProductDto>>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }
}
