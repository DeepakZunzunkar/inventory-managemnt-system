package com.dz.ims.service;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.StockProductDto;
import com.dz.ims.entity.StockProduct;

import java.util.List;

public interface StockProductService {

    public BaseResponse<StockProductDto> addProduct(StockProductDto product);
    public  BaseResponse<StockProductDto> updateProduct(StockProductDto product);
    public  BaseResponse<?> deleteProduct(StockProductDto product);
    public  BaseResponse<StockProductDto> getProductById(Long productId);

    public BaseResponse<List<StockProductDto>> getAllProduct();

}
