package com.example.omarf.firstprojectdemo.Model;

/**
 * Created by omarf on 1/18/2017.
 */

public class User {
    private String mUserName;
    private String mUserPass;
    private String mUserBestScore;

    public User(String mUserName, String mUserPass) {
        this.mUserName = mUserName;
        this.mUserPass = mUserPass;
        this.mUserBestScore = mUserBestScore;
    }

    public User() {

    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserPass() {
        return mUserPass;
    }

    public void setmUserPass(String mUserPass) {
        this.mUserPass = mUserPass;
    }

    public String getmUserBestScore() {
        return mUserBestScore;
    }

    public void setmUserBestScore(String mUserBestScore) {
        this.mUserBestScore = mUserBestScore;
    }
}
