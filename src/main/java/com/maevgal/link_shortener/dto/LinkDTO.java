package com.maevgal.link_shortener.dto;

import lombok.Data;

@Data
public class LinkDTO {
    private Long id;
    private String link;
    private String shortLink;
}
