package com.dz.ims.service;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.StockProductDto;
import com.dz.ims.entity.StockProduct;

import java.util.List;

public interface StockProductService {

    public BaseResponse<StockProductDto> addProduct(StockProductDto product);
    public  Long updateProduct(StockProduct product);
    public  Long deleteProduct(StockProduct product);
    public  StockProduct getProductById(StockProduct product);

    public List<StockProduct> getAllProduct(StockProduct product);

}
