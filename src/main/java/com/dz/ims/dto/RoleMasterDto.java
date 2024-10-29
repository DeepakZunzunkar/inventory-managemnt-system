package com.dz.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleMasterDto {

        private Long id;
        private String roleName;
        private Boolean isActive;
        private BasePropertiesDto baseProperties;


}
