package pl.piekoszek.gorskimatches.equation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/equation")
class EquationController {

    private final EquationRandomizer equationRandomizer;

    EquationController(EquationRandomizer equationRandomizer, SolvableEquations solvableEquations) {
        this.equationRandomizer = equationRandomizer;
        this.solvableEquations = solvableEquations;
    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }

    private final SolvableEquations solvableEquations;
    @GetMapping("random/solution")
    String fetch(){
        return solvableEquations.checkForCorrectAnswer(equationRandomizer.randomEquation(),"7-3=4");
    }
}
