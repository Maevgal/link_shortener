package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.mapper.LinkMapper;
import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LinkService {
    private final LinkRepository repository;
    private final LinkMapper mapper;
    private final ShortLinkService shortLinkService;

    public LinkDTO createLink(LinkCreateDTO linkCreateDTO) {
        Link link = mapper.map(linkCreateDTO);
        link.setShortLink(shortLinkService.createShortLink(linkCreateDTO.getLink()));
        repository.save(link);
        return mapper.map(link);
    }

    public void findLinkByShortLink(String shortLink) {
        Link link = repository.findByLink(shortLink).get();
        LinkDTO linkDTO = mapper.map(link);
    }

}
