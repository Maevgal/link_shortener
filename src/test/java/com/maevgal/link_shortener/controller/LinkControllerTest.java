package com.maevgal.link_shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.dto.LinkStatsDTO;
import com.maevgal.link_shortener.service.LinkService;
import com.maevgal.link_shortener.service.LinkStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LinkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper om;

    @MockBean
    private LinkService linkService;
    private LinkCreateDTO testLinkCreateDto;
    private LinkDTO testLinkDTO;
    @MockBean
    private LinkStatsService linkStatsService;
    private LinkStatsDTO linkStatsDTO;

    @BeforeEach
    public void setUp() {
        testLinkCreateDto = new LinkCreateDTO();
        testLinkCreateDto.setLink("https://for-each.dev/lessons/b/-spring-boot-testing");
        testLinkDTO = new LinkDTO();
        testLinkDTO.setLink("http://some-link");
        testLinkDTO.setShortLink("/somelink");


    }

    @Test
    public void testCreateLink_shouldReturn201() throws Exception {
        var request = post("/links")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(testLinkCreateDto));
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setLink("http://some-link");
        linkDTO.setShortLink("/somelink");
        Mockito.when(linkService.createLink(testLinkCreateDto)).thenReturn(linkDTO);
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                          "link": "http://some-link",
                          "shortLink": "/somelink"
                        }
                        """));

        Mockito.verify(linkService).createLink(testLinkCreateDto);
    }

    @Test
    public void testCreateLink_shouldReturn400WhenLinkIsNull() throws Exception {
        testLinkCreateDto.setLink(null);
        var request = post("/links")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(testLinkCreateDto));

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(linkService);
    }

    @Test
    public void testShowShortLink() throws Exception {
        Mockito.when(linkService.findLinkByShortLink("somelink")).thenReturn("http://some-link");
        mockMvc.perform(get("/somelink"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "http://some-link"));
    }

    @Test
    public void showStats() throws Exception {
        String longLink = "http://some-link";
        String shortLink = "/somelink";
        int count = 4;
        long order = 1;
        linkStatsDTO = new LinkStatsDTO();
        linkStatsDTO.setOrder(order);
        linkStatsDTO.setLink(longLink);
        linkStatsDTO.setShortLink(shortLink);
        linkStatsDTO.setCount(count);
        List<LinkStatsDTO> listLinkStatistics = List.of(linkStatsDTO);

        Mockito.when(linkStatsService.showStats()).thenReturn(listLinkStatistics);

        var request = get("/stats");
        mockMvc.perform(request)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

/*    @Test
    public void testShowShortLink_shouldReturn404WhenShortLinkIsEmpty() throws Exception {
        Mockito.when(linkService.findLinkByShortLink("somelink")).thenReturn(null);
        mockMvc.perform(get("/somelink"))
                .andExpect(status().isNotFound());
    }*/


}
