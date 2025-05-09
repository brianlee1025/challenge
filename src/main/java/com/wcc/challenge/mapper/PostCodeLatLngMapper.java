package com.wcc.challenge.mapper;

import com.wcc.challenge.model.dto.PostCodeLatLngDto;
import com.wcc.challenge.model.entity.PostCodeLatLng;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostCodeLatLngMapper {

    PostCodeLatLngMapper INSTANCE = Mappers.getMapper(PostCodeLatLngMapper.class);

    @Mapping(target = "id", ignore = true)
    PostCodeLatLngDto postCodeLatLngToPostCodeLatLngDto(PostCodeLatLng postCodeLatLng);

    PostCodeLatLng postCodeLatLngDtoToPostCodeLatLng(PostCodeLatLngDto postCodeLatLngDto);

}
