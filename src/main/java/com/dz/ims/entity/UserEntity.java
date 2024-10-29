package com.dz.ims.entity;

import com.dz.ims.dto.BasePropertiesDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
//@Table(name = "user")
public class UserEntity {

    private Long userId;
    private String userName;
    private String password;
    private String userType;
    private Boolean isActive;
    private BasePropertiesDto baseProperties;

}
