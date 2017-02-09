package com.example.omarf.firstprojectdemo.Activity.User;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.omarf.firstprojectdemo.Adapter.PlayPagerAdapter;
import com.example.omarf.firstprojectdemo.Database.DatabaseHelper;
import com.example.omarf.firstprojectdemo.Fragment.ScreenSlidePlayFragment;
import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.Model.QuestionSingleTon;
import com.example.omarf.firstprojectdemo.R;
import com.example.omarf.firstprojectdemo.SyncService;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity implements ScreenSlidePlayFragment.UserChooseAnswerListener{

    public static final String NOT_CHOOSEN = "not choose";
    private static final String TAG = "PlayActivityTag";
    private ViewPager mPager;
    private TextView mTimerTextView;
    private Button mSubmitButton;
    private ArrayList<Question> mWrongAnswers;
    private ArrayList<Question> mQuestions;

    private boolean [] mIsAnswered;
    private CountDownTimer mTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mPager = (ViewPager) findViewById(R.id.play_view_pager);
        mTimerTextView = (TextView) findViewById(R.id.timer_text_view);
        mSubmitButton=(Button) findViewById(R.id.submit_button);
        mWrongAnswers =new ArrayList<>();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionSingleTon.getInstance().setResultList(new ArrayList<Question>());
                QuestionSingleTon.getInstance().setResultList(mWrongAnswers);
                Intent intent= ResultActivity.startIntent(PlayActivity.this);
                intent.putExtra(ResultActivity.WRONG_ANSWER_TAG,mWrongAnswers.size());
                intent.putExtra(ResultActivity.TOTAL_ANSWERED_TAG,getTotalAnswered());
                intent.putExtra(ResultActivity.TOTAL_QUESTION_TAG,mQuestions.size());
                startActivity(intent);
                mWrongAnswers=null;
                finish();
            }
        });


        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        /*FirebaseToSql firebaseToSql=new FirebaseToSql(this);
        firebaseToSql.syncSqlite();*/

      //  SyncService.setSyncAlarm(this, false);
         mQuestions = databaseHelper.getAllQuestion();
        mIsAnswered=new boolean[mQuestions.size()];
         long totalDuration = mQuestions.size() * 5000;
        mTimer=  new CountDownTimer(totalDuration, 1000) {
            @Override
            public void onTick(long l) {
                long totalSec = l / 1000;

                if(totalSec==1)
                {
                    QuestionSingleTon.getInstance().setResultList(new ArrayList<Question>());
                    QuestionSingleTon.getInstance().setResultList(mWrongAnswers);
                    Intent intent= ResultActivity.startIntent(PlayActivity.this);
                    intent.putExtra(ResultActivity.TIME_UP_TAG,ResultActivity.TIME_UP_TAG);
                    intent.putExtra(ResultActivity.WRONG_ANSWER_TAG,mWrongAnswers.size());
                    intent.putExtra(ResultActivity.TOTAL_ANSWERED_TAG,getTotalAnswered());
                    intent.putExtra(ResultActivity.TOTAL_QUESTION_TAG,mQuestions.size());
                    startActivity(intent);
                    mWrongAnswers=null;
                    finish();
                }

                mTimerTextView.setText(totalSec / 60 + " : " + totalSec % 60);
            }

            @Override
            public void onFinish() {
                mTimerTextView.setText("done");
            }
        }.start();


        final PlayPagerAdapter adapter = new PlayPagerAdapter(getSupportFragmentManager(), mQuestions);
         mPager.setAdapter(adapter);



        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==(mQuestions.size()-1)){
                    mSubmitButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }




    @Override
    public void onBackPressed() {

        if (mPager.getCurrentItem() == 0)
            super.onBackPressed();
        else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    //    SyncService.setSyncAlarm(this, true);
        mTimer.cancel();
    }

    public static Intent startIntent(Context context) {
        return new Intent(context, PlayActivity.class);
    }

    @Override
    public void getClickAnswer(String userAnswer) {
        int position=mPager.getCurrentItem();

       mIsAnswered[position]=true;

        Log.i(TAG,"view pager position: "+position +" "+ userAnswer);
        Question question=mQuestions.get(position);
        if(!getResult(userAnswer,position)){

            if (!mWrongAnswers.contains(question))
            mWrongAnswers.add(question);
        }
        else {
            mWrongAnswers.remove(question);
        }


    }

    private boolean getResult(String userAnswer, int position) {
        String correctAnswer=mQuestions.get(position).getmCorrectAnswer();
        return userAnswer.equals(correctAnswer);
    }


    private int getTotalAnswered(){
        int count=0;
        for (boolean temp :
                mIsAnswered) {
            if (temp){
                count++;
            }
        }
        return count;
    }

}
