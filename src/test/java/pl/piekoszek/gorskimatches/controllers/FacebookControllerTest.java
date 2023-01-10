package pl.piekoszek.gorskimatches.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class FacebookControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Value("${VERIFY_TOKEN}")
    private String VERIFY_TOKEN;

    @Test
    public void shouldReturnForbiddenStatusWhenTokenIsWrong() throws Exception {
        mockMvc.perform(get("/api/webhook/facebook/page/message")
                        .param("hub.verify_token", "a")
                        .param("hub.challenge", "a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldReturnOkWhenTokenIsCorrect() throws Exception {
        mockMvc.perform(get("/api/webhook/facebook/page/message")
                        .param("hub.verify_token", VERIFY_TOKEN)
                        .param("hub.challenge", "a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDoSmth() throws Exception {
        mockMvc.perform(post("/api/webhook/facebook/page/message").content("""
                                {
                                    "quiz": "5+8=9",
                                    "answer": "9+0=9"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

}
