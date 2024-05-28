package com.maevgal.link_shortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkStatistic extends Link {
    private String link;
    private String shortLink;
    private int count;
    private long order;
}
