package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.JwtException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;

public class HeaderVersionArgumentResolver implements HandlerMethodArgumentResolver {
    private final TokenDecoder tokenDecoder;
    public HeaderVersionArgumentResolver(TokenDecoder tokenDecoder) {
        this.tokenDecoder = tokenDecoder;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Email.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        var authorizationHeaderValue = request.getHeader("authorization");
        if (authorizationHeaderValue == null) {
            throw new UnauthorizedException("Missing Authorization Header");
        }
        var token = authorizationHeaderValue.split(" ")[1];

        try {
            return tokenDecoder.decode(token).email;
        } catch (JwtException jwtException) {
            throw new UnauthorizedException(jwtException.getMessage());
        }
    }
}
