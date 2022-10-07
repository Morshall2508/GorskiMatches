
# Matches Quizzes

Webapp that generates and handles quizzes in the style of "move one match to solve equation"




## Features

- Generate and solve a random quizz
- Generate a challenge for you and your friend to solve
- Register an account and adjust info to your preferences

![Home page](src/main/resources/static/screenshots/Home_page.jpg?raw=true "Home Page")
![Random Equation](src/main/resources/static/screenshots/Random_equation.jpg?raw=true "Random Equation")

## Try for yourself
You can check out the latest version here: https://gorski-matches.herokuapp.com/
## Feedback

If you have any feedback, please reach out to us at gorskimatchesserver@gmail.com


## Run Locally

Clone the project

```bash
  git clone https://github.com/piekaa/GorskiMatches
```

Install maven

```bash
  mvn clean install
```


Go to the project directory

```bash
  cd target
```


Start the application

```bash
   java -jar GorskiMatches-0.0.1-SNAPSHOT.jar
```

Port is 8080
## Deployment

To deploy this project to heroku

```bash
  git remote add heroku  https://git.heroku.com/gorski-matches.git
  git push heroku master
```

