package com.maevgal.link_shortener.controller;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.service.LinkService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Data
public class LinkController {
    private final LinkService service;

    @PostMapping("/links")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody LinkCreateDTO linkCreateDTO) {
        service.createLink(linkCreateDTO);
    }
}
