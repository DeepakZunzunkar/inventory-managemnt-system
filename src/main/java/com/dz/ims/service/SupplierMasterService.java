package com.dz.ims.service;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.SupplierMasterDto;

public interface SupplierMasterService {

    public BaseResponse<?> addSupplier(SupplierMasterDto dto);
    public  BaseResponse<?> updateSupplier(SupplierMasterDto supplier);
    public  BaseResponse<?> deleteSupplier(SupplierMasterDto supplier);
    public  BaseResponse<?> getSupplierById(Long supplierId);

    public BaseResponse<?> getAllSupplier();
}
