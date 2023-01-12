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

import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FacebookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacebookMessageService facebookMessageService;

    @MockBean
    private FacebookApiClient apiClient;

    @MockBean
    private FacebookResponseGenerator responseGenerator;

    @MockBean
    private FacebookRepository facebookRepository;

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
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void shouldRespondToHiMessageWithWelcomeMessages() throws Exception {
        mockMvc.perform(post("/api/webhook/facebook/page/message").content("""
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
                                "text":"hi"
                                }}]}]}
                             """)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        Mockito.verify(facebookMessageService).sendReply("5533019560146899", "Welcome to my facebook site! Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct.");
        Mockito.verify(facebookMessageService).sendReply("5533019560146899", "To start simply type in: challenge. You will receive a quiz to solve, then type in your answer in format : 0+0=0\nGood luck!");
    }

    @Test
    public void shouldRespondWithContactInformationWhenContactMessageIsReceived() throws Exception {
        mockMvc.perform(post("/api/webhook/facebook/page/message").content("""
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
                                "text":"contact"
                                }}]}]}
                             """)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        Mockito.verify(facebookMessageService).sendReply("5533019560146899", "To contact me please write to: gorskimatchesserver@gmail.com");
    }

    @Test
    public void shouldSendChallengeUrlWhenMessageReceivedIsChallenge() throws Exception {
        var a = Mockito.mock(FacebookRepository.class);
        mockMvc.perform(post("/api/webhook/facebook/page/message").content("""
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
                                "text":"challenge"
                                }}]}]}
                             """)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        var b = a.findById("5533019560146899").get().quiz;
        System.out.println(b);
//        var b = responseGenerator.attachmentResponse("5533019560146899").getMessage().getAttachment().getPayload().url;
//        Mockito.verify(facebookMessageService).sendAttachmentPhoto("5533019560146899", "a") ;
    }
}