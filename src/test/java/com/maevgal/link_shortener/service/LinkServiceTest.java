package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.mapper.LinkMapper;
import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.repository.LinkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LinkServiceTest {

    private LinkMapper linkMapper = Mockito.mock(LinkMapper.class);

    private LinkRepository linkRepository = Mockito.mock(LinkRepository.class);
    private ShortLinkService shortLinkService = Mockito.mock(ShortLinkService.class);
    private LinkService linkService;

    @BeforeEach
    void setUp() {
        linkService = new LinkService(linkRepository, linkMapper, shortLinkService);
    }

    @Test
    void createLink_shouldCreateLink_whenLinkNotExistInDB() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";
        LinkCreateDTO testLinkDto = new LinkCreateDTO();
        testLinkDto.setLink(url);
        Link link = new Link();
        link.setLink(url);

        Link expectedEntity = new Link();
        expectedEntity.setLink(url);
        expectedEntity.setShortLink("123");

        LinkDTO expectedDto = new LinkDTO();
        expectedDto.setLink(url);
        expectedDto.setShortLink("123");

        Mockito.when(linkMapper.map(testLinkDto)).thenReturn(link);
        Mockito.when(linkRepository.findByLink(url)).thenReturn(Optional.empty());
        Mockito.when(linkMapper.map(expectedEntity)).thenReturn(expectedDto);
        Mockito.when(shortLinkService.createShortLink(url)).thenReturn("123");

        LinkDTO actual = linkService.createLink(testLinkDto);
        Mockito.verify(linkRepository).save(expectedEntity);
        Assertions.assertThat(actual).isEqualTo(expectedDto);
    }

    @Test
    void createLink_shouldNotCreateLink_whenLinkExistInDB() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";
        LinkCreateDTO testLinkDto = new LinkCreateDTO();
        testLinkDto.setLink(url);
        Link link = new Link();
        link.setLink(url);

        Link selectedLink = new Link();
        selectedLink.setLink(url);
        selectedLink.setShortLink("123");

        LinkDTO expectedDto = new LinkDTO();
        expectedDto.setLink(url);
        expectedDto.setShortLink("123");

        Mockito.when(linkRepository.findByLink(url)).thenReturn(Optional.of(selectedLink));
        Mockito.when(linkMapper.map(selectedLink)).thenReturn(expectedDto);

        //test
        Assertions.assertThat(linkService.createLink(testLinkDto)).isEqualTo(expectedDto);
        Mockito.verify(linkRepository).findByLink(url);
        Mockito.verifyNoMoreInteractions(linkRepository);
    }

    @Test
    void findLinkByShortLink() {
        String url = "http://some-link";
        String shortLink = "somelink";
        Link link = new Link();
        link.setShortLink(shortLink);
        link.setLink(url);
        link.setCount(0);

        Link expected = new Link();
        expected.setShortLink(shortLink);
        expected.setLink(url);
        expected.setCount(1);

        Mockito.when(linkRepository.findByShortLink(shortLink)).thenReturn(Optional.of(link));
        Assertions.assertThat(linkService.findLinkByShortLink(shortLink)).isEqualTo(url);
        Mockito.verify(linkRepository).save(expected);
    }

    @Test
    void FindLinkByShortLink_shouldThrowException_whenShortLinkNotFound() {
        String shortLink = "somelink";

        Mockito.when(linkRepository.findByShortLink(shortLink)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> linkService.findLinkByShortLink(shortLink))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Not found short link somelink");
    }
}