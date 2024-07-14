package com.dz.ims.repository;

import com.dz.ims.entity.SaleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesMasterRepository extends JpaRepository<SaleMaster, Long> {
}