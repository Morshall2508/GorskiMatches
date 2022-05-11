package pl.piekoszek.gorskimatches.equation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/equation")
class EquationController {

    private final EquationRandomizer equationRandomizer;

    EquationController(EquationRandomizer equationRandomizer) {
        this.equationRandomizer = equationRandomizer;

    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }

}
