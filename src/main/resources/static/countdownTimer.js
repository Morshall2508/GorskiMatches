export default class CountdownTimer {

    maxTimeSeconds;
    currentTimeSeconds = 0;
    intervalId;
    startingElement = document.getElementById("buttonStart");
    displayElement = document.getElementById("countdownTimer");
    onFinish;

    constructor(maxTimeSeconds, displayElement, onFinish) {
        this.maxTimeSeconds = maxTimeSeconds;
        this.displayElement = displayElement;
        this.onFinish = onFinish;
    }

    start() {
        this.startingElement.onclick = () => {
            this.currentTimeSeconds = this.maxTimeSeconds;
            this.intervalId = setInterval(() => {
                this.currentTimeSeconds--;

                if (this.currentTimeSeconds >= 0) {
                    this.displayElement.innerText = `${this.currentTimeSeconds}s left`;
                }
                if (this.currentTimeSeconds === 0) {
                    this.onFinish();
                }

            }, 1000);
        }
    }
    reset() {
        this.currentTimeSeconds = this.maxTimeSeconds;
    }

    stop() {
        clearInterval(this.intervalId);
    }
}
