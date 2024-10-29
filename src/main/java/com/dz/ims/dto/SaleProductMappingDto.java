package com.dz.ims.dto;

import com.dz.ims.entity.SaleMaster;
import com.dz.ims.entity.ProductMaster;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleProductMappingDto {

    private Long mapId;
    private SaleMaster saleMaster;
    private ProductMaster product;
    private Integer quantity;
    private Double totalAmount;
//    private BaseProperties baseProperties;

}
