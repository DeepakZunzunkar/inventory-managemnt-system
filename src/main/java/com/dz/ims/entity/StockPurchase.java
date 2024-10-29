package com.dz.ims.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "purchase_master")
public class StockPurchase {

//    at a time only one product can be purchased

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* one-to-one relationship between ProductMaster and StockPurchase
     where each product can only be associated with one purchase, and vice versa
     but here only one directional relationship .*/

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", unique = true)
    private ProductMaster productMaster;

    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", unique = true)
    private SupplierMaster supplier;

    private Double totalAmount;
    private String invoiceNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="createdAt",column=@Column(name="created_at",updatable= false))
    })
    private BaseProperties baseProperties;
}
