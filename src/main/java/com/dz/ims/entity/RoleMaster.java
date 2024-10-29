package com.dz.ims.entity;

import com.dz.ims.dto.BasePropertiesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "role_master")
public class RoleMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private Boolean isActive;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="createdAt",column=@Column(name="created_at",updatable= false))
    })
    private BaseProperties baseProperties;

}
