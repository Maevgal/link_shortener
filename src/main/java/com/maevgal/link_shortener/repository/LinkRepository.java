package com.maevgal.link_shortener.repository;

import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.model.LinkStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
    Optional<Link> findByLink(String link);

    Optional<Link> findByShortLink(String shortLink);

    @Query(value = "SELECT new com.maevgal.link_shortener.model.LinkStatistic(link, shortLink, count, ROW_NUMBER() OVER(ORDER BY count ASC)) " +
            "FROM Link ORDER BY count")
    List<LinkStatistic> findAllStatisticByLink();
}
