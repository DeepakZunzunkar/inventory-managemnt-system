package com.dz.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse <T> implements Serializable {

    private Integer responseCode;
    private String responseMessage;
    private T responseData;

}
