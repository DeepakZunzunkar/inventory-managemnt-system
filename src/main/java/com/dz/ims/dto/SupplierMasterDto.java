package com.dz.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierMasterDto {

    private Long id;
    private String name;
    private String address;
    private String code;
    private Boolean isActive;
    private BasePropertiesDto baseProperties;
}
