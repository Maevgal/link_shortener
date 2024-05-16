package com.maevgal.link_shortener.mapper;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.model.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class LinkMapperTest {
    LinkMapper mapper = Mappers.getMapper(LinkMapper.class);
    @Test
    void map() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";
        LinkCreateDTO testLinkDto = new LinkCreateDTO();
        testLinkDto.setLink(url);

        Link link = new Link();
        link.setLink(url);

        var result = mapper.map(testLinkDto);
        Assertions.assertThat(result).isEqualTo(link);
    }
}