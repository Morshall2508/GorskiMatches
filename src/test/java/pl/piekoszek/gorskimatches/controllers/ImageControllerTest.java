package pl.piekoszek.gorskimatches.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ImageControllerTest {

    private static final String equation = "4+4=8";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnPNG() throws Exception {
        mockMvc.perform(get("/api/image/equation/" + equation))
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnJPEG() throws Exception {
        mockMvc.perform(get("/api/image/equation/fb/" + equation))
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(status().isOk());
    }
}
