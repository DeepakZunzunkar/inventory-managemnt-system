package com.dz.ims.controller;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.StockProductDto;
import com.dz.ims.entity.StockProduct;
import com.dz.ims.service.StockProductService;
import com.dz.ims.util.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = RequestPath.PRODUCT)
public class StockProductController {

    @Autowired
    StockProductService productService;

    @PostMapping(value = RequestPath.ADD)
    public ResponseEntity<BaseResponse<StockProductDto>> addStockProduct(@RequestBody StockProductDto stockProductDto){
        BaseResponse<StockProductDto> response = productService.addProduct(stockProductDto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PutMapping(value = RequestPath.UPDATE)
    public ResponseEntity<BaseResponse<StockProductDto>> updateStockProduct(@RequestBody StockProductDto stockProductDto){
        BaseResponse<StockProductDto> response = productService.updateProduct(stockProductDto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @DeleteMapping(value = RequestPath.DELETE)
    public ResponseEntity<BaseResponse<?>> deleteStockProduct(@RequestBody StockProductDto stockProductDto){
        BaseResponse<?> response = productService.deleteProduct(stockProductDto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping(value = RequestPath.GETBYID)
    public ResponseEntity<BaseResponse<?>> getSStockProductById(@PathVariable("id") Long productId){
        BaseResponse<?> response = productService.getProductById(productId);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllStockProduct(){
        BaseResponse<?> response = productService.getAllProduct();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
