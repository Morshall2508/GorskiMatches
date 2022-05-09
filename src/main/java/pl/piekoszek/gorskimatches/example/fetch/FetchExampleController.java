package pl.piekoszek.gorskimatches.example.fetch;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
class FetchExampleController {

    @GetMapping("text")
    String fetchString() {
        return "dupa jasia kleofasa";
    }

    @GetMapping("person")
    Person fetchPerson() {
        return new Person("Marian", "Paździoch");
    }

    @PostMapping("person")
    PersonInfo checkPerson(@RequestBody Person person) {
        return new PersonInfo(
                person.age >= 18,
                person.name.endsWith("a") ? Gender.FEMALE : Gender.MALE); // see https://www.baeldung.com/java-ternary-operator
    }

}
