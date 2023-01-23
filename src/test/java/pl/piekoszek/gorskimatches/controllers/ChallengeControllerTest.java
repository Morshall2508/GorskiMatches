package pl.piekoszek.gorskimatches.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
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

    @MockBean
    private EquationRandomizer equationRandomizer;

    @Test
    void shouldCreateChallenge() throws Exception {
        mockMvc.perform(get("/api/challenge/generate"))
                .andExpect(jsonPath("$").isString())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void giveUsOurQuizzesComputer() throws Exception {
        Mockito.when(equationRandomizer.randomEquation()).thenReturn("0-6=0","6-9=3", "6+0=8", "8+0=5", "8+6=0");
        var uuid = getUuid();
        var scoreAndAnswers = """
                {
                                 "scoreUser1":[1,1,1,1,0],
                 "answerUser1":["0-0=0","8-5=3","8-0=8","6+0=6","8+0=0"]
                }
                """;

        mockMvc.perform(post("/api/challenge/challengeQuizzesAndAnswers/{$uuid}", uuid)
                        .header("Authorization", "Bearer " + getToken())
                        .content(scoreAndAnswers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        var answers  = """
                {
                                 "scoreUser2":[1,1,1,1,1],
                 "answerUser2":["0-0=0","","6+0=6","","8-8=0"]
                }
                """;

        mockMvc.perform(post("/api/challenge/challengeQuizzesAndAnswers/{$uuid}", uuid)
                        .content(answers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        System.out.println(mockMvc.perform(get("api/challenge/{$uuid}",uuid)).andReturn().getResponse().getContentAsString());
    }

    @Test
    void shouldSaveScoreOfUserWithEmail() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/score")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveScoreOfUserWithoutEmail() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/score")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/score")
                        .content(getJsonString(uuid)
                                .formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnResultsForNonRegisteredUser() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/score")
                        .header("Authorization", "Bearer " + getToken())
                        .content("""
                                {
                                 "uuid": "%s",
                                 "score" : "2",
                                 "time" : "14.21"
                                }
                                """.formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/score")
                        .content("""
                                {
                                 "uuid": "%s",
                                 "score" : "1",
                                 "time" : "14.21"
                                }
                                """.formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/challenge/resultForNonRegistered/{uuid}", uuid))
                .andExpect(jsonPath("$").value("Unfortunately you've lost :("))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/score")
                        .content("""
                                {
                                 "uuid": "%s",
                                 "score" : "4",
                                 "time" : "14.21"
                                }
                                """.formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/challenge/resultForNonRegistered/{uuid}", uuid))
                .andExpect(jsonPath("$").value("Congratulations you've won!"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAListOfQuizzesThatIsNotEmpty() throws Exception {
        var uuid = getUuid();

        mockMvc.perform(post("/api/challenge/score")
                        .header("Authorization", "Bearer " + getToken())
                        .content(getJsonString(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/score")
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
        var scoreAndAnswers = """
                {
                 "scoreUser1":[1,1,1,1,1],
                 "answerUser1":["1+1=1","1+1=1","1+1=1","1+1=1","1+1=1"]
                }
                """;

        mockMvc.perform(post("/api/challenge/challengeQuizzesAndAnswers/{$uuid}", getUuid())
                        .header("Authorization", "Bearer " + getToken())
                        .content(scoreAndAnswers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveInformationAboutQuizzesForNonRegisteredUser() throws Exception {
        var scoreAndAnswers = """
                {
                 "scoreUser2":[1,1,1,1,1],
                 "answerUser2":["1+1=1","1+1=1","1+1=1","1+1=1","1+1=1"]
                }
                """;

        mockMvc.perform(post("/api/challenge/challengeQuizzesAndAnswers/{$uuid}", getUuid())
                        .content(scoreAndAnswers)
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
        var scoreAndAnswers = """
                {
                 "scoreUser1":[1,1,1,1,1],
                 "answerUser1":["1+1=1","1+1=1","1+1=1","1+1=1","1+1=1"]
                }
                """;
        mockMvc.perform(post("/api/challenge/score")
                .header("Authorization", "Bearer " + getToken())
                .content(getJsonString(uuid))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(post("/api/challenge/challengeQuizzesAndAnswers/" + uuid)
                .header("Authorization", "Bearer " + getToken())
                .content(scoreAndAnswers)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/challenge/" + uuid))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.email").value("gorskimatchesserver@gmail.com"))
                .andExpect(jsonPath("$.email").isString())
                .andExpect(jsonPath("$.challengeQuizzes[0].answerUser1").value("1+1=1"))
                .andExpect(jsonPath("$.challengeQuizzes[0].scoreUser1").value("1"))
                .andExpect(status().isOk());
    }

    private String getToken() {
        return tokenService.encode("gorskimatchesserver@gmail.com");
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
                 "score" : "5",
                 "time" : "12.21"
                }
                """.formatted(uuid);
    }
}