package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.mapper.LinkMapper;
import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.repository.LinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LinkServiceTest {

    private LinkMapper linkMapper = Mockito.mock(LinkMapper.class);

    private LinkRepository linkRepository = Mockito.mock(LinkRepository.class);
    private ShortLinkService shortLinkService = Mockito.mock(ShortLinkService.class);
    private LinkService linkService;

    @BeforeEach
    void setUp() {
        linkService = new LinkService(linkRepository, linkMapper,shortLinkService);
    }

    @Test
    void createLink() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";
        LinkCreateDTO testLinkDto = new LinkCreateDTO();
        testLinkDto.setLink(url);
        Link link = new Link();
        link.setLink(url);

        Link expected = new Link();
        expected.setLink(url);
        expected.setShortLink("123");

        Mockito.when(linkMapper.map(testLinkDto)).thenReturn(link);
        Mockito.when(shortLinkService.createShortLink(url)).thenReturn("123");

        linkService.createLink(testLinkDto);
        Mockito.verify(linkRepository).save(expected);
    }
}