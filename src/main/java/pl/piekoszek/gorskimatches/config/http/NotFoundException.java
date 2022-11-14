package pl.piekoszek.gorskimatches.config.http;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
