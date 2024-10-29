package com.dz.ims.controller;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.ProductMasterDto;
import com.dz.ims.service.ProductMasterService;
import com.dz.ims.util.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = RequestPath.PRODUCT)
public class ProductMasterController {

    @Autowired
    ProductMasterService productService;

    @PostMapping(value = RequestPath.ADD)
    public ResponseEntity<BaseResponse<ProductMasterDto>> addStockProduct(@RequestBody ProductMasterDto productMasterDto){
        BaseResponse<ProductMasterDto> response = productService.addProduct(productMasterDto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PutMapping(value = RequestPath.UPDATE)
    public ResponseEntity<BaseResponse<ProductMasterDto>> updateStockProduct(@RequestBody ProductMasterDto productMasterDto){
        BaseResponse<ProductMasterDto> response = productService.updateProduct(productMasterDto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @DeleteMapping(value = RequestPath.DELETE)
    public ResponseEntity<BaseResponse<?>> deleteStockProduct(@RequestBody ProductMasterDto productMasterDto){
        BaseResponse<?> response = productService.deleteProduct(productMasterDto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping(value = RequestPath.GETBYID)
    public ResponseEntity<BaseResponse<?>> getStockProductById(@PathVariable("id") Long productId){
        BaseResponse<?> response = productService.getProductById(productId);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllStockProduct(){
        BaseResponse<?> response = productService.getAllProduct();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
