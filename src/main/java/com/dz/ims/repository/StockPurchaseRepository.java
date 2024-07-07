package com.dz.ims.repository;


import com.dz.ims.entity.StockPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPurchaseRepository extends JpaRepository<StockPurchase,Long> {
}
