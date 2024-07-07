package com.dz.ims.service;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.StockPurchaseDto;

public interface StockPurchaseService {

    BaseResponse<?> add(StockPurchaseDto dto);
}
