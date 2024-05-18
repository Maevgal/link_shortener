package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.model.Link;
import jakarta.transaction.NotSupportedException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShortLinkService {
    public String createShortLink(String link) {
        String symbols = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";
        int sizeShortLink = 7;
        String shortLink = new Random().ints(sizeShortLink, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        return shortLink;
    }


}
