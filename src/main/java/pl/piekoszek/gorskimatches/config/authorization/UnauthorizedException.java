package pl.piekoszek.gorskimatches.config.authorization;

public class UnauthorizedException extends RuntimeException {

    UnauthorizedException(String message) {
        super(message);
    }
}
