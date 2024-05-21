package com.maevgal.link_shortener.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LinkCreateDTO {
    @NotNull
    private String link;
}
