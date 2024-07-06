package com.dz.ims.controller;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.SupplierMasterDto;
import com.dz.ims.service.SupplierMasterService;
import com.dz.ims.util.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = RequestPath.SUPPLIER)
public class SupplierMasterController {

    @Autowired
    SupplierMasterService service;

    @PostMapping(value = RequestPath.ADD)
    public ResponseEntity<BaseResponse<?>> add(@RequestBody SupplierMasterDto dto){
        BaseResponse<?> response = service.addSupplier(dto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PutMapping(value = RequestPath.UPDATE)
    public ResponseEntity<?> update(@RequestBody SupplierMasterDto dto){
        BaseResponse<?> response = service.updateSupplier(dto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @DeleteMapping(value = RequestPath.DELETE)
    public ResponseEntity<BaseResponse<?>> delete(@RequestBody SupplierMasterDto dto){
        BaseResponse<?> response = service.deleteSupplier(dto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping(value = RequestPath.GETBYID)
    public ResponseEntity<BaseResponse<?>> getById(@PathVariable("id") Long id){
        BaseResponse<?> response = service.getSupplierById(id);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllStockProduct(){
        BaseResponse<?> response = service.getAllSupplier();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
