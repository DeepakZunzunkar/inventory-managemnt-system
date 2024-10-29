package com.dz.ims.entity;

import lombok.*;

import javax.persistence.*;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
@Builder // Generates a builder for the class
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "product_master")
public class ProductMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String description;
    private Double price;
    private Boolean isActive;
    private String remark;
    private String category;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="createdAt",column=@Column(name="created_at",updatable= false))
    })
    private BaseProperties baseProperties;


   /* @OneToOne(mappedBy = "stockProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private StockPurchase stockPurchase;
*/

}
