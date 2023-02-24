package pl.piekoszek.gorskimatches.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.piekoszek.gorskimatches.token.TokenService;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChallengeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService tokenService;

    @Test
    void shouldCreateChallenge() throws Exception {
        mockMvc.perform(get("/api/challenge/generate"))
                .andExpect(jsonPath("$").isString())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveScoreOfUserWithEmail() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/save")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveScoreOfUserWithoutEmail() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/save")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/save")
                        .content(getJsonString(uuid)
                                .formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnResultsForNonRegisteredUser() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/save")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/save")
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/challenge/{uuid}", uuid))
                .andExpect(jsonPath("$.result").value("USER_2_WIN"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/save")
                        .content("""
                {
                 "uuid": "%s",
                 "answersForChallenge":["1+1=1","1+1=1","1+1=1","1+1=1","1+1=1"],
                 "timeToSolveChallenge" : "15.21"
                }
                """.formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/challenge/{uuid}", uuid))
                .andExpect(jsonPath("$.result").value("USER_1_WIN"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAListOfQuizzesThatIsNotEmpty() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/save")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/save")
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/challenge/quizzes/{$uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldSaveInformationAboutQuizzesForRegisteredUser() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/save")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveInformationForBothRegisteredUsers() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/save")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/save")
                        .header("Authorization", "Bearer " + getTokenForSecondUser())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAllInformationAboutChallenges() throws Exception {
        mockMvc.perform(get("/api/challenge/challenges"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnInformationAboutQuiz() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/save")
                .header("Authorization", "Bearer " + getToken())
                .content(getJsonString(uuid))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(post("/api/challenge/save")
                .header("Authorization", "Bearer " + getToken())
                .content(getJsonString(uuid))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/challenge/" + uuid))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.emailUser1").value("gorskimatchesserver@gmail.com"))
                .andExpect(jsonPath("$.emailUser1").isString())
                .andExpect(jsonPath("$.challengeQuizzes[0].answerUser1").value("1+1=1"))
                .andExpect(jsonPath("$.challengeQuizzes[0].scoreUser1").value("0"))
                .andExpect(status().isOk());
    }

    private String getToken() {
        return tokenService.encode("gorskimatchesserver@gmail.com");
    }

    private String getTokenForSecondUser() {
        return tokenService.encode("taktylkonaspotted@gmail.com");
    }

    private UUID getUuid() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(mockMvc.perform(get("/api/challenge/generate"))
                .andReturn().getResponse().getContentAsString(), UUID.class);
    }

    private String getJsonString(UUID uuid) {
        return """
                {
                 "uuid": "%s",
                 "answersForChallenge":["1+1=1","1+1=1","1+1=1","1+1=1","1+1=1"],
                 "timeToSolveChallenge" : "12.21"
                }
                """.formatted(uuid);
    }
}