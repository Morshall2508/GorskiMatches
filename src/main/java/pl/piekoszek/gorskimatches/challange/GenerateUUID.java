package pl.piekoszek.gorskimatches.challange;

import org.hibernate.id.UUIDGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class GenerateUUID extends UUIDGenerator {

    public UUID generateUUID(){
        return UUID.randomUUID();
    }
}
