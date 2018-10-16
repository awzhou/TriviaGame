package com.example.truefalsequiz;

import java.util.List;

public class Quiz {
    private List<Question> questions;
    private int score;
    private int currentQ;

    public Quiz (List<Question> questions) {
        this.questions = questions;
        score = 0;
        currentQ = 0;
    }

    public boolean isAnotherQ() {
        return currentQ < questions.size();
    }

    public Question nextQuestion() {
        currentQ++;
        return questions.get(currentQ - 1);
    }

    // TODO: checkAnswer method
    public void checkAnswer(boolean response) {
        if (questions.get(currentQ - 1).getAnswer() == response) {
            score++;
        }
        else {
            score--;
        }
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentQ() {
        return currentQ;
    }

    public void setCurrentQ(int currentQ) {
        this.currentQ = currentQ;
    }
}
