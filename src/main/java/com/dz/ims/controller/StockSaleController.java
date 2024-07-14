package com.dz.ims.controller;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.SaleMasterDto;
import com.dz.ims.dto.StockPurchaseDto;
import com.dz.ims.service.SaleMasterService;
import com.dz.ims.util.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RequestPath.SALE)
public class StockSaleController {

    @Autowired
    SaleMasterService service;

    @PostMapping(value = RequestPath.ADD)
    public ResponseEntity<BaseResponse<?>> newPurchase(@RequestBody SaleMasterDto dto){
        BaseResponse<?> response = service.add(dto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
