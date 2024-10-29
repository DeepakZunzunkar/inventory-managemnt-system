package com.dz.ims.service;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.RoleMasterDto;

public interface RoleMasterService {

    BaseResponse<?> addUpdate(RoleMasterDto dto);

    BaseResponse<?> delete(RoleMasterDto dto);
    BaseResponse<?> getAll();

    BaseResponse<?> getById(Long id);
}
