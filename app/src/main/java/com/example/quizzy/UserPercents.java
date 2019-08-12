package com.example.quizzy;

public class UserPercents implements Comparable<UserPercents>{

    private String username;
    private Float percentageScore;

    public UserPercents(String username, float percentageScore) {
        this.username = username;
        this.percentageScore = percentageScore;
    }



    public String getUsername() {
        return username;
    }

    public Float getPercentageScore() {
        return percentageScore;
    }

    @Override
    public int compareTo(UserPercents userPercents) {
        return this.getPercentageScore().compareTo(userPercents.getPercentageScore());
    }
}
