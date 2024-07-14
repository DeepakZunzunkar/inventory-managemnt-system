package com.dz.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleMasterDto {

    private Long id;
    private String customerName;
    private String mobileNumber;
    private Double billAmount;
    private List<SaleProductMappingDto> saleProductMapping;
    private BasePropertiesDto baseProperties;
}
