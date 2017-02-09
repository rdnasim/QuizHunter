package com.example.omarf.firstprojectdemo;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by omarf on 1/18/2017.
 */

public class QuestionPreference {

    private static final String LOGIN_TAG = "login_tag";
    private static final String GET_NUMBER_TAG = "getNumberTag";
    private static final String GET_USER_LAST_NUMBER_TAG ="lastNumber" ;
    private static final String GET_SIGN_IN_USER_NAME = "getSignInUSerNameTag";

    public static boolean isUserLoginFirstTime(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(LOGIN_TAG,true);
    }

    public static void setUserLoginFirst(Context context,boolean isLoginFirst ){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(LOGIN_TAG,isLoginFirst)
                .apply();
    }

    public static int getQuestionNumber(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(GET_NUMBER_TAG,0);
    }

    public static void setQuestionNumber(Context context,int number ){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(GET_NUMBER_TAG,number)
                .apply();
    }

    public static int getUserLastQuestionNumber(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(GET_USER_LAST_NUMBER_TAG,0);
    }

    public static void setUserLastQuestionNumber(Context context,int number ){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(GET_USER_LAST_NUMBER_TAG,number)
                .apply();
    }


    public static String getSignInUserName(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(GET_SIGN_IN_USER_NAME,null);
    }

    public static void setSignINUserName(Context context,String name ){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(GET_SIGN_IN_USER_NAME,name)
                .apply();
    }



}
