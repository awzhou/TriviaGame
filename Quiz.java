package com.example.truefalsequiz;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<Question> questions;
    private List<Integer> asked;
    private int score;
    private int currentQ;

    public Quiz (List<Question> questions) {
        this.questions = questions;
        asked = new ArrayList<Integer>();
        score = 0;
        currentQ = 0;
    }

    public boolean isAnotherQ() {
        return asked.size() < 10;
    }

    public Question nextQuestion() {
        //currentQ++;

        currentQ = (int)(questions.size() * Math.random());

        if (asked.indexOf(currentQ) >= 0) {
            nextQuestion();
        }
        else {
            asked.add(currentQ);
        }


        return questions.get(currentQ);
    }

    public void checkAnswer(boolean response) {
        if (questions.get(currentQ).getAnswer() == response) {
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
