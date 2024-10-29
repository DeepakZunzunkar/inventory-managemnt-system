package com.dz.ims.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "product_sales_mapping")
public class SaleProductMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mapId;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private SaleMaster saleMaster;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductMaster productMaster;

    private Integer quantity;
    private Double totalAmount;

    /*@Embedded
    @AttributeOverrides({
            @AttributeOverride(name="createdAt",column=@Column(name="created_at",updatable= false))
    })
    private BaseProperties baseProperties;*/

}
