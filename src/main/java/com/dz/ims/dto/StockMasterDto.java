package com.dz.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMasterDto {

    private Long id;
    private StockProductDto product;
    private Integer quantity;
    private BasePropertiesDto baseProperties;
}
