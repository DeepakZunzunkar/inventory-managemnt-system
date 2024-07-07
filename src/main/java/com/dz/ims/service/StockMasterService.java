package com.dz.ims.service;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.entity.StockMaster;

public interface StockMasterService {

    BaseResponse<StockMaster> addUpdateStock(StockMaster stockMaster);
}
