export default class Chall {
    static saveQuizzes() {
        sessionStorage.setItem('fiveQuizzes', fiveQuizzes);
    }
    static countdown() {
        let timeLeft = 30;
        const element = document.getElementById('countdownTimer');
        const timerId = setInterval(countdown, 1000);
        if (timeLeft === -1) {
            clearTimeout(timerId);
            element.innerHTML = "Time's up";

        } else {
            element.innerHTML = timeLeft + 's remaining';
            timeLeft--;
        }
    }
}