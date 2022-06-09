//package pl.piekoszek.gorskimatches.token;
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.function.ToLongBiFunction;
//
//@RestController
//@RequestMapping("auth")
//class AccountController {
//    private TokenCreator tokenCreator;
//    AccountController(TokenCreator tokenCreator){
//        this.tokenCreator = tokenCreator;
//    }
//
//    @GetMapping("email")
//    String fetchString() {
//        return tokenCreator.jwtToken;
//    }
////@PostMapping("emailconf")
////    (@RequestBody AccountInfo accountInfo)
//
//}
/*


    private final EquationRandomizer equationRandomizer;

    EquationController(EquationRandomizer equationRandomizer, QuizAnswerChecker solvableEquations) {
        this.equationRandomizer = equationRandomizer;
        this.solvableEquations = solvableEquations;
    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }

    private final QuizAnswerChecker solvableEquations;

    @PostMapping("solution")
    boolean checkAnswer(@RequestBody Equation equation) {
        return solvableEquations.checkForCorrectAnswer(equation.quiz, equation.answer);
    }
}
*/