package pl.piekoszek.gorskimatches.facebook;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FacebookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacebookMessageService facebookMessageService;

    @MockBean
    private EquationRandomizer equationRandomizer;

    @Value("${VERIFY_TOKEN}")
    private String VERIFY_TOKEN;

    @Test
    void shouldReturnForbiddenStatusWhenTokenIsWrong() throws Exception {
        mockMvc.perform(get("/api/webhook/facebook/page/message")
                        .param("hub.verify_token", "a")
                        .param("hub.challenge", "a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnOkWhenTokenIsCorrect() throws Exception {
        mockMvc.perform(get("/api/webhook/facebook/page/message")
                        .param("hub.verify_token", VERIFY_TOKEN)
                        .param("hub.challenge", "a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRespondToHiMessageWithWelcomeMessages() throws Exception {
        mockMvc.perform(post("/api/webhook/facebook/page/message").content(getJsonString("hi"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        Mockito.verify(facebookMessageService).sendReply("5533019560146899", "Welcome to my facebook site! Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct.");
        Mockito.verify(facebookMessageService).sendReply("5533019560146899", "To start simply type in: challenge. You will receive a quiz to solve, then type in your answer in format : 0+0=0\nGood luck!");
    }

    @Test
    void shouldRespondWithContactInformationWhenContactMessageIsReceived() throws Exception {
        mockMvc.perform(post("/api/webhook/facebook/page/message").content(getJsonString("contact"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        Mockito.verify(facebookMessageService).sendReply("5533019560146899", "To contact me please write to: gorskimatchesserver@gmail.com");
    }

    @Test
    void shouldSendAttachmentUrlToUserWhenChallengeMessageIsSent() throws Exception {
        Mockito.when(equationRandomizer.randomEquation()).thenReturn("7-5=4");
        mockMvc.perform(post("/api/webhook/facebook/page/message").content(getJsonString("challenge"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        Mockito.verify(facebookMessageService).sendAttachmentPhoto("5533019560146899", "https://maciej.piekoszek.pl/api/image/equation/fb/7-5=4");
    }

    @Test
    void shouldSendReplyWithCongratulationMessageWhenQuizIsCorrect() throws Exception {
        Mockito.when(equationRandomizer.randomEquation()).thenReturn("7-5=4");
        mockMvc.perform(post("/api/webhook/facebook/page/message").content(getJsonString("challenge"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(post("/api/webhook/facebook/page/message").content(getJsonString("7-3=4"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        Mockito.verify(facebookMessageService).sendReply("5533019560146899", "Great Job! For another quiz, type in: challenge");
    }

    @Test
    void shouldSendReplyWithTryAgainMessageWhenQuizIsCorrect() throws Exception {
        Mockito.when(equationRandomizer.randomEquation()).thenReturn("7-5=4");
        mockMvc.perform(post("/api/webhook/facebook/page/message").content(getJsonString("challenge"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(post("/api/webhook/facebook/page/message").content(getJsonString("7-3=0"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        Mockito.verify(facebookMessageService).sendReply("5533019560146899", "Hmm, try again!");
    }

    private String getJsonString(String text) {
        return """
                {"entry":[{
                "id":"101650416067938",
                "time":1673444243864,
                    "messaging":[{
                    "sender":{
                        "id":"5533019560146899"},
                    "recipient":{
                        "id":"101650416067938"},
                    "timestamp":1673444242999,
                    "message":{
                    "mid":"m_W6wvCjMgU-OuVylXuH5Iop5x1RXIqJ5aTf5qnNKQgJ-5o5iTO8njIWduCQ1jQ2INh_kJcw2sAIDunbDeO-0k4w",
                    "text":"%s"
                    }}]}]}
                 """.formatted(text);
    }
}