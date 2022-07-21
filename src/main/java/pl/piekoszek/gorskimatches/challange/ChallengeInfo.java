package pl.piekoszek.gorskimatches.challange;

import java.util.ArrayList;
import java.util.UUID;

public class ChallengeInfo {

    public UUID uuid;

    public ArrayList<String> challengeQuizzes;

    public  ChallengeInfo(){

    }
    public  ChallengeInfo (UUID uuid, ArrayList<String> challengeQuizzes){
        this.uuid = uuid;
        this.challengeQuizzes = challengeQuizzes;
    }

    public UUID getUuid(){
        return uuid;
    }

    public ArrayList<String> getChallengeQuizzes(){
        return challengeQuizzes;
    }

}
