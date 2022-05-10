package pl.piekoszek.gorskimatches.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/equation/newequation")
class EquationController {

    private final EquationRandomizer equationRandomizer;

    EquationController(EquationRandomizer equationRandomizer) {
        this.equationRandomizer = equationRandomizer;

    }

    @GetMapping("randomNumber")
    String fetchString() {
        return equationRandomizer.randomNumber;
    }


}
