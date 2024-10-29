package com.dz.ims.service.impl;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.ProductMasterDto;
import com.dz.ims.entity.BaseProperties;
import com.dz.ims.entity.ProductMaster;
import com.dz.ims.repository.ProductMasterRepository;
import com.dz.ims.service.ProductMasterService;
import com.dz.ims.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {

    @Autowired
    ProductMasterRepository productRepository;


    @Override
    public BaseResponse<ProductMasterDto> addProduct(ProductMasterDto productDto) {
        try{
            ModelMapper modelMapper=new ModelMapper();
            ProductMaster productEntity = modelMapper.map(productDto, ProductMaster.class);
            productEntity.setBaseProperties(new BaseProperties("SYSTEM",new Timestamp(System.currentTimeMillis()),null,null));
            productEntity = productRepository.save(productEntity);
            productDto.setId(productEntity.getId());
            return BaseResponse.<ProductMasterDto>builder()
                    .responseCode(ResponseCode.SUCCESS.value())
                    .responseMessage("product added successfully..")
                    .responseData(productDto)
                    .build();

        }catch (Exception e){
            return BaseResponse.<ProductMasterDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<ProductMasterDto> updateProduct(ProductMasterDto product) {
        try {
            Optional<ProductMaster> optionalStockProduct = productRepository.findById(product.getId());
            if(optionalStockProduct.isPresent()){
                ProductMaster productEntity = optionalStockProduct.get();
                productEntity.setName(product.getName());
                productEntity.setCategory(product.getCategory());
                productEntity.setPrice(product.getPrice());
                productEntity.setDescription(product.getDescription());
                productEntity.setRemark(product.getRemark());
                productEntity.setIsActive(product.getIsActive());
                productEntity.getBaseProperties().setUpdatedBy("SYSTEM");
                productEntity.getBaseProperties().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                productRepository.save(productEntity);
                return BaseResponse.<ProductMasterDto>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("product updated successfully..")
                    .responseData(product)
                        .build();
            }else {
                return BaseResponse.<ProductMasterDto>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("product not found.")
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<ProductMasterDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> deleteProduct(ProductMasterDto product) {
        try{
            Optional<ProductMaster> optionalStockProduct = productRepository.findById(product.getId());
            if(optionalStockProduct.isPresent()) {
                ProductMaster productEntity = optionalStockProduct.get();
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
    public BaseResponse<ProductMasterDto> getProductById(Long productId) {
       try{
           Optional<ProductMaster> optionalStockProduct = productRepository.findById(productId);
           if(optionalStockProduct.isPresent()){
               ProductMaster productEntity = optionalStockProduct.get();
               ModelMapper modelMapper=new ModelMapper();
               ProductMasterDto productDto = modelMapper.map(productEntity, ProductMasterDto.class);
               return BaseResponse.<ProductMasterDto>builder()
                       .responseCode(ResponseCode.SUCCESS.value())
                       .responseMessage("success")
                       .responseData(productDto)
                       .build();
           }else{
               return BaseResponse.<ProductMasterDto>builder()
                       .responseCode(ResponseCode.NO_CONTENT.value())
                       .responseMessage("product not found.")
                       .build();
           }
       }catch (Exception e){
           return BaseResponse.<ProductMasterDto>builder()
                   .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                   .responseMessage("oops something went wrong..")
                   .build();
       }
    }

    @Override
    public BaseResponse<List<ProductMasterDto>> getAllProduct() {
        ArrayList<ProductMasterDto> list = new ArrayList<>();
        try{
            List<ProductMaster> productList = productRepository.findAll();
            if(!productList.isEmpty()){
                ModelMapper modelMapper=new ModelMapper();
                productList.forEach(product->{
                    list.add(modelMapper.map(product, ProductMasterDto.class));
                });
                return BaseResponse.<List<ProductMasterDto>>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("success")
                        .responseData(list)
                        .build();
            }else{
                return BaseResponse.<List<ProductMasterDto>>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("products not found.")
                        .responseData(list)
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<List<ProductMasterDto>>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }
}
