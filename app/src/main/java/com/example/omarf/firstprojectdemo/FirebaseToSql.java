package com.example.omarf.firstprojectdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.omarf.firstprojectdemo.Activity.SignInActivity;
import com.example.omarf.firstprojectdemo.Database.DatabaseHelper;
import com.example.omarf.firstprojectdemo.Database.FirebaseHelper;
import com.example.omarf.firstprojectdemo.Model.Question;

import java.util.ArrayList;

/**
 * Created by omarf on 1/15/2017.
 */

public class FirebaseToSql implements FirebaseHelper.QuestionListListener {
    private final Context mContext;
    private FirebaseHelper mFirebaseHelper;
    private DatabaseHelper mDatabaseHelper;


    public FirebaseToSql(Context context) {
        mFirebaseHelper = new FirebaseHelper();
        mDatabaseHelper = new DatabaseHelper(context);
        mFirebaseHelper.setmQuestionListListener(this);
        mContext = context;
    }


    public void syncSqlite() {
        mFirebaseHelper.getAllQuestion();
    }

    @Override
    public void onReceiveQuestionList(ArrayList<Question> questions) {
        mDatabaseHelper.deleteAllRow();

        int lastQuestionNumber=QuestionPreference.getUserLastQuestionNumber(mContext);
        int highestNumberInFB=0;

        for (Question question :
                questions) {
            mDatabaseHelper.addQuestion(question);
            highestNumberInFB=question.getmQuestionNumber();

        }

        if (!QuestionPreference.isUserLoginFirstTime(mContext) && (highestNumberInFB>lastQuestionNumber))
        {


            Intent intent=new Intent(mContext, SignInActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
            Notification notification = new NotificationCompat.Builder(mContext)
                    .setTicker("New Question Available")
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContentTitle("New Question Available")
                    .setContentText("Play now")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(mContext);
            managerCompat.notify(0, notification);

            QuestionPreference.setUserLastQuestionNumber(mContext,highestNumberInFB);
        }




    }






    /* ArrayList<Question> fireQuestions = questions;

/*//***********************************sqlite read operation**************************************************//**//*
        ArrayList<Question> sqlQuestions = mDatabaseHelper.getAllQuestion();

        int fireSize = fireQuestions.size();
/*//***********************************sqlite delete operation**************************************************//**//*
        while (sqlQuestions.size() > fireQuestions.size()) {
            mDatabaseHelper.deleteQuestion(sqlQuestions.get(fireSize).getmFireId());
            sqlQuestions.remove(fireSize);
            fireSize++;
        }

        if (!sqlQuestions.isEmpty()) {
            for (int i = 0; i < fireQuestions.size(); i++) {
                Question fireQuestion = fireQuestions.get(i);
                Question sqlQuestion;
                if(sqlQuestions.size()<=i){

                   sqlQuestion = sqlQuestions.get(i);
                }
                else {
/*//***********************************sqlite create operation**************************************************//**//*
                    mDatabaseHelper.addQuestion(fireQuestion);
                    continue;
                }



                boolean hasChange = false;
                if (!fireQuestion.getmCorrectAnswer().equals(sqlQuestion.getmCorrectAnswer())) {
                    sqlQuestion.setmCorrectAnswer(fireQuestion.getmCorrectAnswer());
                    hasChange = true;
                }
                if (!fireQuestion.getmQuestion().equals(sqlQuestion.getmQuestion())) {
                    sqlQuestion.setmQuestion(fireQuestion.getmQuestion());
                    hasChange = true;
                }
                if (!fireQuestion.getmFirstChoice().equals(sqlQuestion.getmFirstChoice())) {
                    sqlQuestion.setmFirstChoice(fireQuestion.getmFirstChoice());
                    hasChange = true;
                }
                if (!fireQuestion.getmSecondChoice().equals(sqlQuestion.getmSecondChoice())) {
                    sqlQuestion.setmSecondChoice(fireQuestion.getmSecondChoice());
                    hasChange = true;
                }
                if (!fireQuestion.getmThirdChoice().equals(sqlQuestion.getmThirdChoice())) {
                    sqlQuestion.setmThirdChoice(fireQuestion.getmThirdChoice());
                    hasChange = true;
                }
                if (!fireQuestion.getmFourthChoice().equals(sqlQuestion.getmFourthChoice())) {
                    sqlQuestion.setmFourthChoice(fireQuestion.getmFourthChoice());
                    hasChange = true;
                }
/*//***********************************sqlite update operation**************************************************//**//*
                if (hasChange) {
                    mDatabaseHelper.updateQuestion(sqlQuestion, fireQuestion.getmFireId());
                }
            }
        } else {
            for (Question question : fireQuestions
                    ) {
                mDatabaseHelper.addQuestion(question);
                sqlQuestions.add(question);
            }

        }*/


}
