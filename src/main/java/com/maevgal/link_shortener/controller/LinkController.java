package com.maevgal.link_shortener.controller;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.service.LinkService;
import com.maevgal.link_shortener.service.ShortLinkService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Data
public class LinkController {
    private final LinkService service;
    private final ShortLinkService shortLinkService;

    @PostMapping("/links")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody LinkCreateDTO linkCreateDTO) {
        service.createLink(linkCreateDTO);
    }

    @GetMapping("/{short-url}")
    @ResponseStatus(HttpStatus.FOUND)
    public LinkDTO showShortLink(@PathVariable String shortLink){
        s
    }
}
