package com.dz.ims.service.impl;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.ProductMasterDto;
import com.dz.ims.dto.RoleMasterDto;
import com.dz.ims.entity.BaseProperties;
import com.dz.ims.entity.RoleMaster;
import com.dz.ims.repository.RoleMasterRepository;
import com.dz.ims.service.RoleMasterService;
import com.dz.ims.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleMasterServiceImpl implements RoleMasterService {

    @Autowired
    RoleMasterRepository roleMasterRepository;

    @Override
    public BaseResponse<?> addUpdate(RoleMasterDto dto) {
        RoleMaster roleMaster = null;
        String responseMessage = "added";
        try {
            roleMaster = roleMasterRepository.findByRoleNameIgnoreCase(dto.getRoleName());
            if (roleMaster != null) {
                roleMaster.setIsActive(dto.getIsActive());
                roleMaster.getBaseProperties().setUpdatedBy("SYSTEM");
                roleMaster.getBaseProperties().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                responseMessage = "updated";
            } else {
                ModelMapper modelMapper = new ModelMapper();
                roleMaster = modelMapper.map(dto, RoleMaster.class);
                roleMaster.setBaseProperties(new BaseProperties("SYSTEM", new Timestamp(System.currentTimeMillis()), null, null));
            }
            roleMasterRepository.save(roleMaster);
            return BaseResponse.<RoleMasterDto>builder()
                    .responseCode(ResponseCode.SUCCESS.value())
                    .responseMessage("role " + responseMessage + " successfully..")
                    .responseData(dto)
                    .build();
        } catch (Exception e) {
            return BaseResponse.builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> delete(RoleMasterDto dto) {
        try {
            Optional<RoleMaster> optionalRoleMaster = roleMasterRepository.findById(dto.getId());
            if (optionalRoleMaster.isPresent()) {
                roleMasterRepository.delete(optionalRoleMaster.get());
                return BaseResponse.<RoleMasterDto>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("role deleted successfully..")
                        .build();
            } else {
                return BaseResponse.<RoleMasterDto>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("role not found..")
                        .build();
            }

        } catch (Exception e) {
            return BaseResponse.builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> getAll() {
        List<RoleMasterDto> roleList = new ArrayList<>();
        try {
            List<RoleMaster> roleMasters = roleMasterRepository.findAll();
            if (!roleMasters.isEmpty()) {
                ModelMapper modelMapper = new ModelMapper();
                roleMasters.forEach(rolemaster -> {
                    roleList.add(modelMapper.map(rolemaster, RoleMasterDto.class));
                });
                return BaseResponse.<List<RoleMasterDto>>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("success")
                        .responseData(roleList)
                        .build();
            } else {
                return BaseResponse.<List<ProductMasterDto>>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("products not found.")
                        .build();
            }
        } catch (Exception e) {
            return BaseResponse.builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> getById(Long id) {
        try {
            Optional<RoleMaster> optionalRoleMaster = roleMasterRepository.findById(id);
            if (optionalRoleMaster.isPresent()) {
                RoleMaster master = optionalRoleMaster.get();
                ModelMapper modelMapper = new ModelMapper();
                RoleMasterDto dto=modelMapper.map(master,RoleMasterDto.class);
                return BaseResponse.<RoleMasterDto>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("fetched successfully..")
                        .responseData(dto)
                        .build();
            } else {
                return BaseResponse.<RoleMasterDto>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("role not found..")
                        .build();
            }
        } catch (Exception e) {
            return BaseResponse.builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }
}
