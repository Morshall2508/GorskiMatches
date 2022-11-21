package pl.piekoszek.gorskimatches.config.authorization;

import io.jsonwebtoken.JwtException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pl.piekoszek.gorskimatches.token.TokenService;

import javax.servlet.http.HttpServletRequest;

public class HeaderArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenService tokenService;

    public HeaderArgumentResolver(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Email.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        var email = parameter.getParameterAnnotation(Email.class);

        var required = email.required();

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        var authorizationHeaderValue = request.getHeader("authorization");
        if (authorizationHeaderValue == null) {
            if (required) {
                throw new UnauthorizedException("Missing Authorization Header");
            } else {
                return null;
            }
        }
        var token = authorizationHeaderValue.split(" ")[1];

        try {
            return tokenService.decode(token).email;
        } catch (JwtException jwtException) {
            throw new UnauthorizedException(jwtException.getMessage());
        }
    }
}
