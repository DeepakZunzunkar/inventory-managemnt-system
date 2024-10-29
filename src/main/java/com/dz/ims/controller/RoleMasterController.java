package com.dz.ims.controller;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.RoleMasterDto;
import com.dz.ims.dto.SupplierMasterDto;
import com.dz.ims.service.RoleMasterService;
import com.dz.ims.util.RequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = RequestPath.ROLE)
public class RoleMasterController {

    @Autowired
    RoleMasterService service;

    @PostMapping(value = RequestPath.ADD)
    public ResponseEntity<BaseResponse<?>> addUpdate(@RequestBody RoleMasterDto dto){
        BaseResponse<?> response = service.addUpdate(dto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @DeleteMapping(value = RequestPath.DELETE)
    public ResponseEntity<BaseResponse<?>> delete(@RequestBody RoleMasterDto dto){
        BaseResponse<?> response = service.delete(dto);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping(value = RequestPath.GETBYID)
    public ResponseEntity<BaseResponse<?>> getById(@PathVariable("id") Long id){
        BaseResponse<?> response = service.getById(id);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping(value = RequestPath.GETALL)
    public ResponseEntity<BaseResponse<?>> getAll(){
        BaseResponse<?> response = service.getAll();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
