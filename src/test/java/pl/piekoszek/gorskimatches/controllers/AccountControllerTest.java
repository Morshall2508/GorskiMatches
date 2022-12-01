package pl.piekoszek.gorskimatches.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.piekoszek.gorskimatches.token.TokenService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    private final String email = "gorskimatchesserver@gmail.com";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService tokenService;

    @Test
    public void shouldSendEmailAndReturnStringWhenEmailIsInCorrectFormat() throws Exception {
        var string = mockMvc.perform(post("/api/auth/email/").content("""
                                {
                                 "email": "gorskimatchesserver@gmail.com"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(string.matches("Email has been sent")).isTrue();
    }

    @Test
    public void shouldNotSendEmailWhenEmailIsInIncorrectFormat() throws Exception {
        mockMvc.perform(post("/api/auth/email/").content("""
                                {
                                 "email": "a1s2.com"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("Email has to have proper format"))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void shouldNotSendEmailWhenEmailIsEmpty() throws Exception {
        mockMvc.perform(post("/api/auth/email/").content("""
                                {
                                 "email": ""
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("Email cannot be blank"))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void shouldNotChangeAccountInfoAndReturnStringWhenAuthorizationHeaderIsMissing() throws Exception {
        mockMvc.perform(post("/api/auth/account/")
                        .content("""
                                {
                                 "email": "gorskimatchesserver@gmail.com",
                                 "accountName" : "Maciek",
                                 "avatar" : "123"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").value("Missing Authorization Header"));
    }

    @Test
    public void shouldNotChangeTheAccountInfoWhenTokenSignatureIsNotCorrect() throws Exception {
        TokenService tokenService = new TokenService("5olTsaOR52ihTM3jtaw0RVTAtcNhajLs");
        mockMvc.perform(post("/api/auth/account/")
                        .header("Authorization", "Bearer " + tokenService.encode(email))
                        .content("""
                                {
                                 "email": "gorskimatchesserver@gmail.com",
                                 "accountName" : "Maciek",
                                 "avatar" : "123"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldNotChangeAccountInfoAndReturnStringWhenAccountNameIsEmpty() throws Exception {
        mockMvc.perform(post("/api/auth/account/")
                        .header("Authorization", "Bearer " + getToken())
                        .content("""
                                {
                                 "email": "gorskimatchesserver@gmail.com",
                                 "accountName" : "",
                                 "avatar" : "123"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountName").value("Account name is mandatory"))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void shouldNotChangeAccountInfoAndReturnStringWhenEmailIsIncorrect() throws Exception {
        mockMvc.perform(post("/api/auth/account/")
                        .header("Authorization", "Bearer " + getToken())
                        .content("""
                                {
                                 "email": "matchesserver@gmail.com",
                                 "accountName" : "123",
                                 "avatar" : "123"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value("Incorrect email"))
                .andExpect(status().isForbidden()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void shouldChangeTheAccountInfoWhenTokenSignatureIsCorrect() throws Exception {
        mockMvc.perform(post("/api/auth/account/")
                        .header("Authorization", "Bearer " + getToken())
                        .content("""
                                {
                                 "email": "gorskimatchesserver@gmail.com",
                                 "accountName" : "Maciek",
                                 "avatar" : "123"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAccountInfo() throws Exception {
        mockMvc.perform(post("/api/auth/account/")
                .header("Authorization", "Bearer " + getToken())
                .content("""
                        {
                         "email": "gorskimatchesserver@gmail.com",
                         "accountName" : "Maciek",
                         "avatar" : "123"
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/auth/accountInfo/" + email))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotReturnAccountInfoWhenUserIsNotInDatabase() throws Exception {
        mockMvc.perform(get("/api/auth/accountInfo/" + email))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Cannot find user with email: " + email));
    }

    private String getToken(){
        return tokenService.encode("gorskimatchesserver@gmail.com");
    }
}
