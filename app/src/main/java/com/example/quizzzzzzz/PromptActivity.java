package com.example.quizzzzzzz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
    private boolean correctAnswer;

    /// to dodałem od siebie//////
    private Button showCorrectAnswerButton;
    private TextView answerTextView;
    public static final String KEY_EXTRA_ANSWER_SHOWN = "com.example.quizzzzzzz.answerShown";

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    // to dodalem
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        /// to dodałem od siebie//////
        showCorrectAnswerButton = findViewById(R.id.answer_button_view);
        answerTextView = findViewById(R.id.answer_text_view);
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER,true);

        showCorrectAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);
                setAnswerShownResult(true);
            }
        });
    }
    // dodalem
    private void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK,resultIntent);
    }
}