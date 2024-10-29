package com.dz.ims.repository;

import com.dz.ims.entity.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster,Long> {
}
