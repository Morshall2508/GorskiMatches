package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class GenerateUUID {

    public UUID generateUUID(){
        return UUID.randomUUID();
    }
}
