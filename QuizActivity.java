package com.example.truefalsequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private Button buttonTrue;
    private Button buttonFalse;

    private Quiz quiz;

    public static final String EXTRA_SCORE = "the score";

    public static final String TAG = "QuizActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();

        initializeQuiz();

        displayNextQ();

    }

    private void displayNextQ() {
        if (quiz.isAnotherQ()) {
            textViewQuestion.setText(quiz.nextQuestion().getQuestion());
        }
        else {
            Intent displayScoreIntent = new Intent(QuizActivity.this, ScoreActivity.class);

            String score = "" + quiz.getScore();
            displayScoreIntent.putExtra(EXTRA_SCORE, score);

            startActivity(displayScoreIntent);
        }
    }

    private void initializeQuiz() {
        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions); // getting XML
        String jsonString = readTextFile(XmlFileInputStream);
        Log.d(TAG, "onCreate: " + jsonString);

        // create a gson object
        Gson gson = new Gson();
        // read your json file into an array of questions
        Question[] questions = gson.fromJson(jsonString, Question[].class);
        // convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        // verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());

        quiz = new Quiz(questionList);
    }

    private void setListeners() {
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.checkAnswer(true);
                displayNextQ();
            }
        });
        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.checkAnswer(false);
                displayNextQ();
            }
        });
    }

    private void wireWidgets() {
        textViewQuestion = findViewById(R.id.textView_quiz_question);
        buttonTrue = findViewById(R.id.button_quiz_true);
        buttonFalse = findViewById(R.id.button_quiz_false);
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

}
