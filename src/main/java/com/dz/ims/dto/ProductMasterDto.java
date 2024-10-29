package com.dz.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMasterDto {

    private Long id;
    private String name;
    private String code;
    private String description;
    private Double price;
    private Boolean isActive;
    private String remark;
    private String category;
    private BasePropertiesDto baseProperties;
}
