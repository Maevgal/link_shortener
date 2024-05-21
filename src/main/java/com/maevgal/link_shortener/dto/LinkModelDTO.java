package com.maevgal.link_shortener.dto;

import lombok.Data;

@Data
public class LinkModelDTO {
    private String link;
    private String shortLink;
    private int count;
}
