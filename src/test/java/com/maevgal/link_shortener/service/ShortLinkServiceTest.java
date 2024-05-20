package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.repository.LinkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class ShortLinkServiceTest {
    private LinkRepository linkRepository = Mockito.mock(LinkRepository.class);
    private ShortLinkService shortLinkService;

    @BeforeEach
    void setUp() {
        shortLinkService = new ShortLinkService(linkRepository);
    }

    @Test
    void createShortLink() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";
        String expected = "b8dab21";
        Mockito.when(linkRepository.findByShortLink(expected)).thenReturn(Optional.empty());

        Assertions.assertThat(shortLinkService.createShortLink(url)).isEqualTo(expected);
    }

    @Test
    void createShortLink_createNewShortLink_whenShortUrlIsExist() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";
        String existedShortLink = "b8dab21";
        String expected = "ff72d31";

        Mockito.when(linkRepository.findByShortLink(existedShortLink)).thenReturn(Optional.of(new Link()));
        Mockito.when(linkRepository.findByShortLink(expected)).thenReturn(Optional.empty());

        Assertions.assertThat(shortLinkService.createShortLink(url)).isEqualTo(expected);
    }
}