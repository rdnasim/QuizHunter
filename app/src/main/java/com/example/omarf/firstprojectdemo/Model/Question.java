package com.example.omarf.firstprojectdemo.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by omarf on 1/11/2017.
 */

public class Question {

    private String mQuestion;
    private String mFirstChoice;
    private String mSecondChoice;
    private String mThirdChoice;
    private String mFourthChoice;
    private String mCorrectAnswer;
    private String mFireId;
    private int mQuestionNumber;

    public Question() {

    }

    public Question(String mQuestion,
                    String mFirstChoice,
                    String mSecondChoice,
                    String mThirdChoice,
                    String mFourthChoice,
                    String mCorrectAnswer) {

        this.mQuestion = mQuestion;
        this.mFirstChoice = mFirstChoice;
        this.mSecondChoice = mSecondChoice;
        this.mThirdChoice = mThirdChoice;
        this.mFourthChoice = mFourthChoice;
        this.mCorrectAnswer = mCorrectAnswer;
    }

    public int  getmQuestionNumber() {
        return mQuestionNumber;
    }

    public void setmQuestionNumber(int mQuestionNumber) {
        this.mQuestionNumber = mQuestionNumber;
    }

    public Question(String[] questionDatas) {
        this.mQuestion = questionDatas[0];
        this.mFirstChoice = questionDatas[1];
        this.mSecondChoice = questionDatas[2];
        this.mThirdChoice = questionDatas[3];
        this.mFourthChoice = questionDatas[4];
        this.mCorrectAnswer = questionDatas[5];
    }


    public String getmQuestion() {
        return mQuestion;
    }

    public String getmFirstChoice() {
        return mFirstChoice;
    }

    public String getmSecondChoice() {
        return mSecondChoice;
    }

    public String getmThirdChoice() {
        return mThirdChoice;
    }

    public String getmFourthChoice() {
        return mFourthChoice;
    }

    public String getmCorrectAnswer() {
        return mCorrectAnswer;
    }



    public void setmFireId(String mFireId) {
        this.mFireId = mFireId;
    }

    public String getmFireId() {
        return mFireId;
    }

    public Map<String,Object> toMap() {
        HashMap<String,Object> result=new HashMap<>();
        result.put("mQuestion",mQuestion);
        result.put("mFirstChoice",mFirstChoice);
        result.put("mSecondChoice",mSecondChoice);
        result.put("mThirdChoice",mThirdChoice);
        result.put("mFourthChoice",mFourthChoice);
        result.put("mCorrectAnswer",mCorrectAnswer);

        return result;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setmFirstChoice(String mFirstChoice) {
        this.mFirstChoice = mFirstChoice;
    }

    public void setmSecondChoice(String mSecondChoice) {
        this.mSecondChoice = mSecondChoice;
    }

    public void setmThirdChoice(String mThirdChoice) {
        this.mThirdChoice = mThirdChoice;
    }

    public void setmFourthChoice(String mFourthChoice) {
        this.mFourthChoice = mFourthChoice;
    }

    public void setmCorrectAnswer(String mCorrectAnswer) {
        this.mCorrectAnswer = mCorrectAnswer;
    }
}
