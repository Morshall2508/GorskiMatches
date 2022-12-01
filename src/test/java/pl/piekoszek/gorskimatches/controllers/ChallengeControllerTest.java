package pl.piekoszek.gorskimatches.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.piekoszek.gorskimatches.token.TokenService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ChallengeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService tokenService;

    @Test
    void shouldReturnUuid() throws Exception {
        mockMvc.perform(get("/api/challenge/generate"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveScoreOfUserWithEmail() throws Exception {
        var uuid = mockMvc.perform(get("/api/challenge/generate"))
                .andReturn().getResponse().getContentAsString();

        var json = """
                {
                 "uuid": %s,
                 "score" : "4",
                 "time" : "12.21"
                }
                """.formatted(uuid);
        System.out.println(json);

        mockMvc.perform(post("/api/challenge/score")
                        .header("Authorization", "Bearer " + getToken())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveScoreOfUserWithoutEmail() throws Exception {
        var uuid = mockMvc.perform(get("/api/challenge/generate"))
                .andReturn().getResponse().getContentAsString();

        var json = """
                {
                 "uuid": %s,
                 "score" : "4",
                 "time" : "12.21"
                }
                """.formatted(uuid);

        mockMvc.perform(post("/api/challenge/score")
                        .header("Authorization", "Bearer " + getToken())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/score")
                        .content("""
                                {
                                 "uuid": %s,
                                 "score" : "3",
                                 "time" : "14.21"
                                }
                                """.formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnResultForNonRegisteredUser() throws Exception {
        var uuid = mockMvc.perform(get("/api/challenge/generate"))
                .andReturn().getResponse().getContentAsString();
        var json = """
                {
                 "uuid": %s,
                 "score" : "4",
                 "time" : "12.21"
                }
                """.formatted(uuid);

        mockMvc.perform(post("/api/challenge/score")
                        .header("Authorization", "Bearer " + getToken())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/score")
                        .content("""
                                {
                                 "uuid": %s,
                                 "score" : "3",
                                 "time" : "14.21"
                                }
                                """.formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //TODO Make sure that url is in correct format - in this way it doesn't work.
        mockMvc.perform(get("/api/challenge/resultForNonRegistered/{uuid}", uuid))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAListOfQuizzesThatIsNotEmpty() throws Exception {
        var uuid = mockMvc.perform(get("/api/challenge/generate"))
                .andReturn().getResponse().getContentAsString();

        var json = """
                {
                 "uuid": %s,
                 "score" : "4",
                 "time" : "12.21"
                }
                """.formatted(uuid);

        mockMvc.perform(post("/api/challenge/score")
                        .header("Authorization", "Bearer " + getToken())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/challenge/score")
                        .content("""
                                {
                                 "uuid": %s,
                                 "score" : "3",
                                 "time" : "14.21"
                                }
                                """.formatted(uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //TODO Make sure that url is in correct format - in this way it doesn't work.
        mockMvc.perform(get("/api/challenge/quizzes/{$uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldSaveInformationAboutQuizzesForRegistratedUser() throws Exception {
        var uuid = mockMvc.perform(get("/api/challenge/generate"))
                .andReturn().getResponse().getContentAsString();
        var scoreAndAnswers1 = """
                {
                 "scoreUser1":[1,1,1,1,1],
                 "answerUser1":["1+1=1","1+1=1","1+1=1","1+1=1","1+1=1"]
                }
                """;

        //TODO Make sure that url is in correct format - in this way it doesn't work.
        mockMvc.perform(post("/api/challenge/challengeQuizzesAndAnswers/{$uuid}", uuid)
                        .header("Authorization", "Bearer " + getToken())
                        .content(scoreAndAnswers1))
                .andExpect(status().isOk());
    }
    @Test
    void shouldSaveInformationAboutQuizzesForNonRegisteredUser() throws Exception {
        var uuid = mockMvc.perform(get("/api/challenge/generate"))
                .andReturn().getResponse().getContentAsString();
        var scoreAndAnswers = """
                {
                 "scoreUser2":[1,1,1,1,1],
                 "answerUser2":["1+1=1","1+1=1","1+1=1","1+1=1","1+1=1"]
                }
                """;

        //TODO Make sure that url is in correct format - in this way it doesn't work.
        mockMvc.perform(post("/api/challenge/challengeQuizzesAndAnswers/{$uuid}", uuid)
                        .content(scoreAndAnswers))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAllInformationAboutChallenges() throws Exception {
        mockMvc.perform(get("/api/challenge/challenges"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnInformationAboutQuiz() throws Exception{
        var uuid = mockMvc.perform(get("/api/challenge/generate"))
                .andReturn().getResponse().getContentAsString();

        //TODO Make sure that url is in correct format - in this way it doesn't work.
        mockMvc.perform(get("/api/challenge/{$uuid}", uuid))
                .andExpect(status().isOk());
    }

    @Test
    private String getToken() {
        return tokenService.encode("gorskimatchesserver@gmail.com");
    }


}