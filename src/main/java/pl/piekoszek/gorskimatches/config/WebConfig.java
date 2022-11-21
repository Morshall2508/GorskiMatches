package pl.piekoszek.gorskimatches.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.piekoszek.gorskimatches.config.authorization.HeaderArgumentResolver;
import pl.piekoszek.gorskimatches.token.TokenService;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TokenService tokenService;

    public WebConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HeaderArgumentResolver(tokenService));
    }
}
