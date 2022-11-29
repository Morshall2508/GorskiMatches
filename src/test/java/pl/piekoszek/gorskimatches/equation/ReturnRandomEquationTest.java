package pl.piekoszek.gorskimatches.equation;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.piekoszek.gorskimatches.token.TokenService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(EquationController.class)
public class ReturnRandomEquationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private EquationController equationController;

    @Test
    public void shouldReturnCorrectFormat() throws Exception {

        var equation = mockMvc.perform(get("/api/equation/random")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(equation);
    }
}
