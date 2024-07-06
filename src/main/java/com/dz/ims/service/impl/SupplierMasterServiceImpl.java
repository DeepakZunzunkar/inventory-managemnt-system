package com.dz.ims.service.impl;

import com.dz.ims.dto.BaseResponse;
import com.dz.ims.dto.SupplierMasterDto;
import com.dz.ims.entity.BaseProperties;
import com.dz.ims.entity.SupplierMaster;
import com.dz.ims.repository.SupplierMasterRepository;
import com.dz.ims.service.SupplierMasterService;
import com.dz.ims.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SupplierMasterServiceImpl implements SupplierMasterService {


    @Autowired
    SupplierMasterRepository masterRepository;

    @Override
    public BaseResponse<?> addSupplier(SupplierMasterDto dto) {
        try{
            ModelMapper modelMapper=new ModelMapper();
            SupplierMaster entity = modelMapper.map(dto, SupplierMaster.class);
            entity.setBaseProperties(new BaseProperties("SYSTEM",new Timestamp(System.currentTimeMillis()),null,null));
            entity = masterRepository.save(entity);
            dto.setId(entity.getId());
            return BaseResponse.<SupplierMasterDto>builder()
                    .responseCode(ResponseCode.SUCCESS.value())
                    .responseMessage("added successfully..")
                    .responseData(dto)
                    .build();

        }catch (Exception e){
            return BaseResponse.<SupplierMasterDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> updateSupplier(SupplierMasterDto dto) {
        try {
            Optional<SupplierMaster> optional = masterRepository.findById(dto.getId());
            if(optional.isPresent()){
                SupplierMaster entity = optional.get();
                entity.setName(dto.getName());
                entity.setAddress(dto.getAddress());
                entity.setIsActive(dto.getIsActive());
                entity.getBaseProperties().setUpdatedBy("SYSTEM");
                entity.getBaseProperties().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                masterRepository.save(entity);
                return BaseResponse.<SupplierMasterDto>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("updated successfully..")
                        .responseData(dto)
                        .build();
            }else {
                return BaseResponse.<SupplierMasterDto>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("record not found.")
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<SupplierMasterDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> deleteSupplier(SupplierMasterDto dto) {
        try{
            Optional<SupplierMaster> optional = masterRepository.findById(dto.getId());
            if(optional.isPresent()) {
                SupplierMaster entity = optional.get();
                // hard delete
                masterRepository.delete(entity);
                return BaseResponse.<String>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("deleted successfully.")
                        .build();
            }else{
                return BaseResponse.<String>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("record not found.")
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<String>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> getSupplierById(Long id) {
        try{
            Optional<SupplierMaster> optional = masterRepository.findById(id);
            if(optional.isPresent()){
                SupplierMaster entity = optional.get();
                ModelMapper modelMapper=new ModelMapper();
                SupplierMasterDto dto = modelMapper.map(entity,SupplierMasterDto.class);
                return BaseResponse.<SupplierMasterDto>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("success")
                        .responseData(dto)
                        .build();
            }else{
                return BaseResponse.<SupplierMasterDto>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("record not found.")
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<SupplierMasterDto>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> getAllSupplier() {
        ArrayList<SupplierMasterDto> list = new ArrayList<>();
        try{
            List<SupplierMaster> entityList = masterRepository.findAll();
            if(!entityList.isEmpty()){
                ModelMapper modelMapper=new ModelMapper();
                entityList.forEach(entity->{
                    list.add(modelMapper.map(entity,SupplierMasterDto.class));
                });
                return BaseResponse.<List<SupplierMasterDto>>builder()
                        .responseCode(ResponseCode.SUCCESS.value())
                        .responseMessage("success")
                        .responseData(list)
                        .build();
            }else{
                return BaseResponse.<List<SupplierMasterDto>>builder()
                        .responseCode(ResponseCode.NO_CONTENT.value())
                        .responseMessage("record not found.")
                        .responseData(list)
                        .build();
            }
        }catch (Exception e){
            return BaseResponse.<List<SupplierMasterDto>>builder()
                    .responseCode(ResponseCode.PROCCESSING_FAIL.value())
                    .responseMessage("oops something went wrong..")
                    .build();
        }
    }
}
