package com.maevgal.link_shortener.service;

import com.maevgal.link_shortener.model.Link;
import jakarta.transaction.NotSupportedException;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkService {
    public String createShortLink(String link){
        throw new RuntimeException();
    }

}
