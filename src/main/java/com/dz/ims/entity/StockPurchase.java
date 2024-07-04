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

    /* one-to-one relationship between StockProduct and StockPurchase
     where each product can only be associated with one purchase, and vice versa.*/

  /*  @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", unique = true)
    private StockProduct stockProduct;

*/
}
