package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.dto.LinkStatsDTO;
import com.maevgal.link_shortener.mapper.LinkMapper;
import com.maevgal.link_shortener.model.LinkStatistic;
import com.maevgal.link_shortener.repository.LinkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class LinkStatsServiceTest {
    private LinkRepository linkRepository = Mockito.mock(com.maevgal.link_shortener.repository.LinkRepository.class);
    private LinkStatsService linkStatsService;
    private LinkMapper mapper = Mockito.mock(LinkMapper.class);

    @BeforeEach
    void setUp() {
        linkStatsService = new LinkStatsService(linkRepository, mapper);
    }

    @Test
    void showStats() {
        String longLink = "http://some-link";
        String shortLink = "/somelink";
        int count = 4;
        long order = 1;
        LinkStatistic linkStatistic = new LinkStatistic();
        linkStatistic.setLink(longLink);
        linkStatistic.setShortLink(shortLink);
        linkStatistic.setCount(count);
        linkStatistic.setOrder(order);

        List<LinkStatistic> linkStatistics = new ArrayList<>();
        linkStatistics.add(linkStatistic);


        LinkStatsDTO linkStatsDTO = new LinkStatsDTO();
        linkStatsDTO.setLink(longLink);
        linkStatsDTO.setShortLink(shortLink);
        linkStatsDTO.setCount(count);
        linkStatsDTO.setOrder(order);
        List<LinkStatsDTO> linkStatsDTOS = List.of(linkStatsDTO);


        Mockito.when(linkRepository.findAllStatisticByLink()).thenReturn(linkStatistics);
        Mockito.when(mapper.map(linkStatistics)).thenReturn(linkStatsDTOS);
        Assertions.assertThat(linkStatsService.showStats()).isEqualTo(linkStatsDTOS);

    }
}