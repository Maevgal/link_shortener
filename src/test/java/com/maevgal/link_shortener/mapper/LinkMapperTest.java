package com.maevgal.link_shortener.mapper;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkStatsDTO;
import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.model.LinkStatistic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class LinkMapperTest {
    LinkMapper mapper = Mappers.getMapper(LinkMapper.class);

    @Test
    public void map() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";
        LinkCreateDTO testLinkDto = new LinkCreateDTO();
        testLinkDto.setLink(url);

        Link link = new Link();
        link.setLink(url);

        var result = mapper.map(testLinkDto);
        Assertions.assertThat(result).isEqualTo(link);
    }

    @Test
    public void testMap() {
        String link = "http://some-link";
        String shortLink = "/somelink";
        int count = 4;
        long order = 1;

        LinkStatsDTO testLinkStatsDTO = new LinkStatsDTO();
        testLinkStatsDTO.setLink(link);
        testLinkStatsDTO.setShortLink(shortLink);
        testLinkStatsDTO.setCount(count);
        testLinkStatsDTO.setOrder(order);

        List<LinkStatsDTO> listLinkStatsDTO = new ArrayList<>();
        listLinkStatsDTO.add(testLinkStatsDTO);

        LinkStatistic linkStatistic = new LinkStatistic();
        linkStatistic.setLink(link);
        linkStatistic.setShortLink(shortLink);
        linkStatistic.setCount(count);
        linkStatistic.setOrder(order);

        List<LinkStatistic> listLinkStatistics = new ArrayList<>();
        listLinkStatistics.add(linkStatistic);

        var result = mapper.map(listLinkStatistics);
        Assertions.assertThat(result).isEqualTo(listLinkStatsDTO);
    }
}