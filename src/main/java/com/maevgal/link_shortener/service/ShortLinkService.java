package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.zip.CRC32;

@RequiredArgsConstructor
@Service
public class ShortLinkService {
    private final LinkRepository linkRepository;
    private static final String CONST_STRING = "Smile";

    public String createShortLink(String link) {
        CRC32 crc32 = new CRC32();
        crc32.update(link.getBytes());
        long value = crc32.getValue();
        String token = Long.toHexString(value).substring(0, 7);
        while (linkRepository.findByShortLink(token).isPresent()) {
            crc32.update(CONST_STRING.getBytes());
            value = crc32.getValue();
            token = Long.toHexString(value).substring(0, 7);
        }
        return token;
    }
}
