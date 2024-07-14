package com.dz.ims.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sales_master")
public class SaleMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String mobileNumber;
    private Double billAmount;

    @OneToMany(mappedBy = "saleMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleProductMapping> saleProductMapping;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="createdAt",column=@Column(name="created_at",updatable= false))
    })
    private BaseProperties baseProperties;

}
