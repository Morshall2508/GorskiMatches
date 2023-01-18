package pl.piekoszek.gorskimatches.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EquationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCorrectFormat() throws Exception {
        var equation = mockMvc.perform(get("/api/equation/random")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(equation.matches("\\d[-+]\\d=\\d")).isTrue();
    }

    @Test
    void shouldReturnTrueWhenCorrectAnswerIsGivenToQuiz() throws Exception {
        mockMvc.perform(post("/api/equation/solution").content("""
                {
                    "quiz": "5+8=9",
                    "answer": "9+0=9"
                }
                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(true))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnFalseWhenCorrectAnswerIsGivenToQuiz() throws Exception {
        mockMvc.perform(post("/api/equation/solution").content("""
                {
                    "quiz": "5+8=9",
                    "answer": "1+0=1"
                }
                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(false))
                .andExpect(status().isOk());
    }
}
