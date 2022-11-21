package pl.piekoszek.gorskimatches.config.authorization;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message) {
        super(message);
    }
}
