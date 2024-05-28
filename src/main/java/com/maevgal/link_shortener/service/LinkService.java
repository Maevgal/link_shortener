package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.exception.ResourceNotFoundException;
import com.maevgal.link_shortener.mapper.LinkMapper;
import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LinkService {
    private final LinkRepository repository;
    private final LinkMapper mapper;
    private final ShortLinkService shortLinkService;

    public LinkDTO createLink(LinkCreateDTO linkCreateDTO) {
        return repository.findByLink(linkCreateDTO.getLink())
                .or(() -> createAndSaveLink(linkCreateDTO))
                .map(mapper::map)
                .orElseThrow(()->new RuntimeException("Cannot create link"));
    }

    private Optional<Link> createAndSaveLink(LinkCreateDTO linkCreateDTO) {
        Link link;
        link = mapper.map(linkCreateDTO);
        link.setShortLink(shortLinkService.createShortLink(linkCreateDTO.getLink()));
        repository.save(link);
        return Optional.of(link);
    }

    public String findLinkByShortLink(String shortLink) {
        Link link = repository.findByShortLink(shortLink)
                .orElseThrow(() -> new ResourceNotFoundException("Not found short link " + shortLink));
        link.setCount(link.getCount() + 1);
        repository.save(link);
        return link.getLink();
    }
}
