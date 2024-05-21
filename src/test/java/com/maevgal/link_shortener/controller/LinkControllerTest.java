package com.maevgal.link_shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maevgal.link_shortener.dto.LinkCreateDTO;
import com.maevgal.link_shortener.dto.LinkDTO;
import com.maevgal.link_shortener.dto.LinkModelDTO;
import com.maevgal.link_shortener.model.Link;
import com.maevgal.link_shortener.service.LinkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static java.nio.file.Paths.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        mockMvc.perform((RequestBuilder) get("/somelink"))
                .andExpect(status().isFound());
    }

}
