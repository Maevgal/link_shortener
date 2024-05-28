package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.dto.LinkStatsDTO;
import com.maevgal.link_shortener.mapper.LinkMapper;
import com.maevgal.link_shortener.model.LinkStatistic;
import com.maevgal.link_shortener.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LinkStatsService {
    private final LinkRepository linkRepository;
    private final LinkMapper mapper;

    public List<LinkStatsDTO> showStats() {
        List<LinkStatistic> linkStatistics = linkRepository.findAllStatisticByLink();
        List<LinkStatsDTO> result = mapper.map(linkStatistics);
        return result;
    }
}
