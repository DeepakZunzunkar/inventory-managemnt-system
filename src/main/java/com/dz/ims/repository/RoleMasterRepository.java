package com.dz.ims.repository;


import com.dz.ims.entity.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster,Long> {

    RoleMaster findByRoleNameIgnoreCase(String roleName);
}
