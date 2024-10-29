package com.dz.ims.entity;

import com.dz.ims.dto.BasePropertiesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
//@Table(name = "user_detail")
public class userDetail {

    private Long id;
    private String firstName;
    private String lastName;
    private String joiningDate;
    private String email;


}
