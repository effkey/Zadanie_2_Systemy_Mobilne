package com.example.quizzzzzzz;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity"; // potrzebne do logow

    private static final String KEY_CURRENT_INDEX = "currentIndex"; // potrzebne do zachowywania stanu przy zniszczeniu głównej aktywności
    public static final String KEY_EXTRA_ANSWER = "com.example.quizzzzzzz.correctAnswer";
    private static final int REQUEST_CODE_PROMPT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int currentIndex = 0;

    //to dadalem
    private Button promptButton;
    private boolean answerWasShown;

    //https://developer.android.com/reference/android/util/Log#d(java.lang.String,%20java.lang.String,%20java.lang.Throwable)

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Wywolana zostala metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    ///// to dodalem
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Zostala wywolana metoda onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Zostala wywolana metoda onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Zostala wywolana metoda onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Zostala wywolana metoda onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Zostala wywolana metoda onResume()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Zostala wywolana metoda onCreate()"); // do logow
        setContentView(R.layout.activity_main);
//////////////
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        /// to dodałem od siebie//////
        promptButton = findViewById(R.id.promptButton);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % questions.length;
                answerWasShown = false;
                setNextQuestion();
            }
        });
        setNextQuestion();
        //to dodalem
        promptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { // to dodalem
                Intent intent = new Intent(MainActivity.this, PromptActivity.class);
                boolean correctAnswer = questions[currentIndex].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                startActivityForResult(intent, REQUEST_CODE_PROMPT);
            }
        }); ////

    }


    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    private Question[] questions = new Question[]{
            new Question(R.string.q_activity, false),
            new Question(R.string.q_version, true),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, false),
            new Question(R.string.q_find_resources, true)
    };


    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (answerWasShown) {
            resultMessageId = R.string.answer_was_shown;
        } else {
            if (userAnswer == correctAnswer) {
                resultMessageId = R.string.correct_answer;
            } else {
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_PROMPT) {
            if (data == null) {
                return;
            }
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }
}