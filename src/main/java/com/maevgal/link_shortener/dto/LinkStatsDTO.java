package com.maevgal.link_shortener.dto;

import lombok.Data;

@Data
public class LinkStatsDTO {
    private String link;
    private String shortLink;
    private int count;
    private long order;
}
