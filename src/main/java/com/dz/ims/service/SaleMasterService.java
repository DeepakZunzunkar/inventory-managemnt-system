package com.dz.ims.service;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.SaleMasterDto;

public interface SaleMasterService {

    BaseResponse<?> add(SaleMasterDto dto);
}
