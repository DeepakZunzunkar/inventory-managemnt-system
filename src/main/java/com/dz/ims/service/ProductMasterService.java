package com.dz.ims.service;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.ProductMasterDto;

import java.util.List;

public interface ProductMasterService {

    public BaseResponse<ProductMasterDto> addProduct(ProductMasterDto product);
    public  BaseResponse<ProductMasterDto> updateProduct(ProductMasterDto product);
    public  BaseResponse<?> deleteProduct(ProductMasterDto product);
    public  BaseResponse<ProductMasterDto> getProductById(Long productId);

    public BaseResponse<List<ProductMasterDto>> getAllProduct();

}
