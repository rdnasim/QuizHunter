package com.example.omarf.firstprojectdemo.Activity.User;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.Model.QuestionSingleTon;
import com.example.omarf.firstprojectdemo.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    public static final String TIME_UP_TAG = "time_up";
    public static final String TOTAL_ANSWERED_TAG = "total_answer";
    public static final String WRONG_ANSWER_TAG = "wrong_answer";
    public static final String TOTAL_QUESTION_TAG = "total_question";

    private TextView mTotalQuestionTextView;
    private TextView mCorrectAnswerTextView;
    private TextView mTotalAnsweredTextView;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mTotalQuestionTextView = (TextView) findViewById(R.id.total_question_text_view);
        mCorrectAnswerTextView = (TextView) findViewById(R.id.correct_answer_text_view);
        mTotalAnsweredTextView = (TextView) findViewById(R.id.total_answered_text_view);
        mResultTextView= (TextView) findViewById(R.id.result_text_view);
        Button correctAnswerButton= (Button) findViewById(R.id.correct_answer_button);

        int totatQuestion=getIntent().getIntExtra(TOTAL_QUESTION_TAG,0);
        int totatAnswer=getIntent().getIntExtra(TOTAL_ANSWERED_TAG,0);
        int totatWrongAnswer=getIntent().getIntExtra(WRONG_ANSWER_TAG,0);

        int totalCorrectAnswer=totatAnswer-totatWrongAnswer;

        if (totatWrongAnswer==0)
            correctAnswerButton.setVisibility(View.GONE);


        mTotalQuestionTextView.setText(String.valueOf(totatQuestion) );
        mTotalAnsweredTextView.setText(String.valueOf(totatAnswer));
        mCorrectAnswerTextView.setText(String.valueOf(totalCorrectAnswer));


        String timeUp=getIntent().getStringExtra(TIME_UP_TAG);
        if (timeUp!=null){
            mResultTextView.setText("Time Up");
        }




    }

    public void onClickPlayAgainButton(View view) {
      startActivity(PlayActivity.startIntent(ResultActivity.this));

        finish();
    }

    public void onClickCorrectAnswer(View view) {
        startActivity(WrongAnswersActivity.startIntent(ResultActivity.this));
    }

    public static Intent startIntent(Context context) {
        return new Intent(context, ResultActivity.class);
    }

    //public void calculateBestScore()
}
