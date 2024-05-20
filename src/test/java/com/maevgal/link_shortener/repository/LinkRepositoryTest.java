package com.maevgal.link_shortener.repository;

import com.maevgal.link_shortener.model.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class LinkRepositoryTest {
    @Autowired
    LinkRepository linkRepository;

    @Test
    void findByLink() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";

        Link link = new Link();
        link.setLink(url);
        link.setShortLink("short_link");
        link.setCount(2);
        linkRepository.save(link);

        Link actual = linkRepository.findByLink(url).get();
        Assertions.assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(link);
    }

    @Test
    void existByShortLink() {
        String url = "https://for-each.dev/lessons/b/-spring-boot-testing";

        Link link = new Link();
        link.setLink(url);
        link.setShortLink("short_link");
        link.setCount(2);
        linkRepository.save(link);

        Assertions.assertThat(linkRepository.findByShortLink("short_link").get())
                .usingRecursiveComparison().ignoringFields("id").isEqualTo(link);
        Assertions.assertThat(linkRepository.findByShortLink("short_link_not_found")).isEqualTo(Optional.empty());
    }
}