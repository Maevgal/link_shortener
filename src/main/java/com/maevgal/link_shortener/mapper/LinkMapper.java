package com.maevgal.link_shortener.mapper;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.model.Link;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class LinkMapper {
    public abstract Link map(LinkCreateDTO dto);

    //public abstract LinkDTO map(Link model);
}
