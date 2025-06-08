package com.resturant.service.mapper;

import com.resturant.dto.ContactInfoDto;
import com.resturant.model.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMapper {

    ContactMapper contact = Mappers.getMapper(ContactMapper.class);

    ContactInfoDto toDto(ContactInfo contactInfo);

    ContactInfo DtoToEntity (ContactInfoDto contactInfoDto);

    List<ContactInfoDto> toDtoList(List<ContactInfo> contactInfo);

    List<ContactInfo> ToEntityList (List<ContactInfoDto> contactInfoDto);

}
