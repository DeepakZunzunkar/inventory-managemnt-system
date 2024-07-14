package com.dz.ims.repository;

import com.dz.ims.entity.StockMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMasterRepository extends JpaRepository<StockMaster,Long> {
    StockMaster findStockProductById(Long id);

    StockMaster findByStockProductId(Long id);
}
