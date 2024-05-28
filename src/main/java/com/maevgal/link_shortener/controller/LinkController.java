package com.maevgal.link_shortener.controller;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.dto.LinkStatsDTO;
import com.maevgal.link_shortener.service.LinkService;
import com.maevgal.link_shortener.service.LinkStatsService;
import com.maevgal.link_shortener.service.ShortLinkService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Data
public class LinkController {
    private final LinkService service;
    private final ShortLinkService shortLinkService;
    private final LinkStatsService linkStatsService;

    @PostMapping("/links")
    @ResponseStatus(HttpStatus.CREATED)
    public LinkDTO create(@Valid @RequestBody LinkCreateDTO linkCreateDTO) {
        return service.createLink(linkCreateDTO);
    }

    @GetMapping("/{short-url}")
    public ResponseEntity showShortLink(@PathVariable(value = "short-url") String shortLink) {
        String url = service.findLinkByShortLink(shortLink);

        return ResponseEntity.status(302)
                .header("Location", url)
                .build();
    }

    @GetMapping("/stats")
    public List<LinkStatsDTO> showStats() {
        return linkStatsService.showStats();
    }
}
