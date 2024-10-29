package com.dz.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockPurchaseDto {

    private Long id;
    private ProductMasterDto product;
    private Integer quantity;
    private SupplierMasterDto supplier;
    private Double totalAmount;
    private String invoiceNumber;
    private BasePropertiesDto baseProperties;
}
