package com.maevgal.link_shortener.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class LinkCreateDTO {
    @NotNull
    private String link;
}
