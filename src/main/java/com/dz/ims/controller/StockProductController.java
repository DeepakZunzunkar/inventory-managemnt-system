package com.dz.ims.controller;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.StockProductDto;
import com.dz.ims.entity.StockProduct;
import com.dz.ims.service.StockProductService;
import com.dz.ims.util.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
