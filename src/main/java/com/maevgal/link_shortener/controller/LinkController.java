package com.maevgal.link_shortener.controller;

import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.dto.LinkModelDTO;
import com.maevgal.link_shortener.service.LinkService;
import com.maevgal.link_shortener.service.ShortLinkService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Data
public class LinkController {
    private final LinkService service;
    private final ShortLinkService shortLinkService;
    private final LinkModelDTO linkModelDTO;

    @PostMapping("/links")
    @ResponseStatus(HttpStatus.CREATED)
    public LinkDTO create(@Valid @RequestBody LinkCreateDTO linkCreateDTO) {
        return service.createLink(linkCreateDTO);
    }

    @GetMapping("/{short-url}")
    public ResponseEntity showShortLink(@PathVariable String shortLink) {
        String url = service.findLinkByShortLink(shortLink);
        int count = 0;
        if (url.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("asdwfw");
        }
        linkModelDTO.setLink(url);
        linkModelDTO.setShortLink(shortLink);
        count++;
        linkModelDTO.setCount(count);

        return ResponseEntity.status(302)
                .header("Location", url)
                .body("asd");
    }
}
