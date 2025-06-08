package com.resturant.service.mapper;

import com.resturant.dto.ChefsDto;
import com.resturant.model.Chefs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChefsMapper {
    ChefsMapper chefsMapper= Mappers.getMapper(ChefsMapper.class);

    List<Chefs> dtoToEntity (List<ChefsDto> chefsDto);
    List<ChefsDto> toDto (List<Chefs> chefs);

}
