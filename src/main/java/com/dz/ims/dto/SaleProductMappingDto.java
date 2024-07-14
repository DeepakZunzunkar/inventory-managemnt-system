package com.dz.ims.dto;

import com.dz.ims.entity.BaseProperties;
import com.dz.ims.entity.SaleMaster;
import com.dz.ims.entity.StockProduct;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleProductMappingDto {

    private Long mapId;
    private SaleMaster saleMaster;
    private StockProduct product;
    private Integer quantity;
    private Double totalAmount;
//    private BaseProperties baseProperties;

}
