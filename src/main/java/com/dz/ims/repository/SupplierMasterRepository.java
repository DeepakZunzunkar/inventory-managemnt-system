package com.dz.ims.repository;

import com.dz.ims.entity.SupplierMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierMasterRepository extends JpaRepository<SupplierMaster,Long> {


}
