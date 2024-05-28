package com.maevgal.link_shortener.repository;

import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.model.LinkStatistic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class LinkRepositoryTest {
    @Autowired
    LinkRepository linkRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

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

    @Test
    void findAllStatisticByLink() {
        String longLink = "http://some-link";
        String shortLink = "/somelink";
        int count = 4;
        long order = 1;
        Link link = new Link();
        link.setLink(longLink);
        link.setShortLink(shortLink);
        link.setCount(count);
        linkRepository.save(link);

        System.out.println(jdbcTemplate.queryForList("SELECT * FROM INFORMATION_SCHEMA.TABLES"));
        LinkStatistic linkStatistic = new LinkStatistic();
        linkStatistic.setOrder(order);
        linkStatistic.setLink(longLink);
        linkStatistic.setShortLink(shortLink);
        linkStatistic.setCount(count);
        List<LinkStatistic> listLinkStatistics = new ArrayList<>();
        listLinkStatistics.add(linkStatistic);

        Assertions.assertThat(linkRepository.findAllStatisticByLink())
                .isEqualTo(listLinkStatistics);
    }
}