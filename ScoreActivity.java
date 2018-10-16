package com.example.truefalsequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextView textViewScore;
    private TextView textViewPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        wireWidgets();

        textViewScore.setText(getIntent().getStringExtra(QuizActivity.EXTRA_SCORE));

        setListeners();
    }

    private void setListeners() {
        textViewPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playAgainIntent = new Intent(ScoreActivity.this, QuizActivity.class);

                startActivity(playAgainIntent);
            }
        });
    }

    private void wireWidgets() {
        textViewTitle = findViewById(R.id.textView_score_title);
        textViewScore = findViewById(R.id.textView_score_score);
        textViewPlayAgain = findViewById(R.id.textView_score_playagain);
    }
}
