package com.example.omarf.firstprojectdemo.Model;

import com.example.omarf.firstprojectdemo.Database.FirebaseHelper;

import java.util.ArrayList;

/**
 * Created by omarf on 1/11/2017.
 */
public class QuestionSingleTon {
    private FirebaseHelper mFirebaseHelper;
    private  ArrayList<Question> mQuestion;
    private ArrayList<Question> mResults;
    private static QuestionSingleTon ourInstance = new QuestionSingleTon();

    public static QuestionSingleTon getInstance() {
        return ourInstance;
    }

    private QuestionSingleTon() {
        mFirebaseHelper =new FirebaseHelper();
        mQuestion=new ArrayList<>();


    }

    public  ArrayList<Question> getQuestionList(){
        return mQuestion;
    }

    public void setmQuestion(ArrayList<Question> mQuestion) {
        this.mQuestion = mQuestion;
    }

    public ArrayList<Question> getmResultList(){
        return mResults;
    }

    public void setResultList(ArrayList<Question> mResults) {
        this.mResults = mResults;
    }

    /*  public void updateQuestionList(){
        mQuestion=mFirebaseHelper.getAllQuestion();
    }
*/


}
