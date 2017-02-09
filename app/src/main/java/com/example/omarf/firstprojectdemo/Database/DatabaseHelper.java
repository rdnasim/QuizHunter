package com.example.omarf.firstprojectdemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.omarf.firstprojectdemo.FirebaseToSql;
import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.Model.User;

import java.util.ArrayList;

/**
 * Created by omarf on 1/14/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "questionManager";


    public static final String QUESTION_TABLE = "tableManager";
    public static final String QUESTION_ID = "id";
    public static final String QUESTION_COLUMN = "question";
    public static final String FIRST_CH_COLM = "firstChoice";
    public static final String SECOND_CH_COLM = "secondChoice";
    public static final String THIRD_CH_COLM = "thirdChoice";
    public static final String FOURTH_CH_COLM = "fourthChoice";
    public static final String ANS_COLM = "answer";
    public static final String QUES_NUMB="question_number";
    public static final String FIRE_ID_COLM = "firebaseId";


    private static final String TAG = "DatabasehelperTag";


//          user table

    public static final String USER_TABLE = "userTable";
    public static final String USER_NAME_COLM = "userName";
    public static final String USER_PASS_COLM = "password";
    public static final String USER_TABLE_ID = "id";
    public static final String USER_BEST_SCORE = "bestScore";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_QUESTION_TABLE = "CREATE TABLE " + QUESTION_TABLE + "("
                + QUESTION_ID + " INTEGER PRIMARY KEY," + QUESTION_COLUMN + " TEXT,"
                + FIRST_CH_COLM + " TEXT," + SECOND_CH_COLM + " TEXT," + THIRD_CH_COLM + " TEXT," +
                FOURTH_CH_COLM + " TEXT," + ANS_COLM + " TEXT," + FIRE_ID_COLM + " TEXT," +QUES_NUMB+" INTEGER"+ ")";
        sqLiteDatabase.execSQL(CREATE_QUESTION_TABLE);

        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
                + USER_TABLE_ID + " INTEGER PRIMARY KEY," + USER_NAME_COLM + " TEXT,"
                + USER_PASS_COLM + " TEXT," + USER_BEST_SCORE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(sqLiteDatabase);
    }


    public void addQuestion(Question question) {
        ContentValues values = new ContentValues();
        values.put(QUESTION_COLUMN, question.getmQuestion());
        values.put(FIRST_CH_COLM, question.getmFirstChoice());
        values.put(SECOND_CH_COLM, question.getmSecondChoice());
        values.put(THIRD_CH_COLM, question.getmThirdChoice());
        values.put(FOURTH_CH_COLM, question.getmFourthChoice());
        values.put(ANS_COLM, question.getmCorrectAnswer());
        values.put(FIRE_ID_COLM, question.getmFireId());
        values.put(QUES_NUMB,question.getmQuestionNumber());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(QUESTION_TABLE, null, values);
        Log.i(TAG, "add question");

        db.close();
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(USER_NAME_COLM,user.getmUserName());
        values.put(USER_PASS_COLM,user.getmUserPass());
        values.put(USER_BEST_SCORE,user.getmUserBestScore());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USER_TABLE, null, values);
        Log.i(TAG, "add User");

        db.close();
    }

    public User getUser(String userName){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(USER_TABLE,
                new String[]{USER_NAME_COLM,USER_PASS_COLM,USER_BEST_SCORE},
                USER_NAME_COLM+"=?",
                new String[]{userName},
                null,null,null);
        User user=new User();
        if (cursor.moveToNext()){
            user.setmUserName(cursor.getString(cursor.getColumnIndex(USER_NAME_COLM)));
            user.setmUserPass(cursor.getString(cursor.getColumnIndex(USER_PASS_COLM)));

        }
        db.close();
        cursor.close();
        return user;
    }


    public Question getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(QUESTION_TABLE,
                new String[]{QUESTION_ID, QUESTION_COLUMN, FIRST_CH_COLM, SECOND_CH_COLM,
                        THIRD_CH_COLM, FOURTH_CH_COLM, ANS_COLM, FIRE_ID_COLM,QUES_NUMB},
                QUESTION_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Question question = new Question();
        if (cursor.moveToNext()) {
            question.setmQuestion(cursor.getString(cursor.getColumnIndex(QUESTION_COLUMN)));
            question.setmFirstChoice(cursor.getString(cursor.getColumnIndex(FIRST_CH_COLM)));
            question.setmSecondChoice(cursor.getString(cursor.getColumnIndex(SECOND_CH_COLM)));
            question.setmThirdChoice(cursor.getString(cursor.getColumnIndex(THIRD_CH_COLM)));
            question.setmFourthChoice(cursor.getString(cursor.getColumnIndex(FOURTH_CH_COLM)));
            question.setmCorrectAnswer(cursor.getString(cursor.getColumnIndex(ANS_COLM)));
            question.setmFireId(cursor.getString(cursor.getColumnIndex(FIRE_ID_COLM)));
            question.setmQuestionNumber(cursor.getInt(cursor.getColumnIndex(QUES_NUMB)));
        }

        db.close();

        return question;
    }

    public Question getQuestion(String fire_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(QUESTION_TABLE,
                new String[]{QUESTION_ID, QUESTION_COLUMN, FIRST_CH_COLM, SECOND_CH_COLM,
                        THIRD_CH_COLM, FOURTH_CH_COLM, ANS_COLM, FIRE_ID_COLM,QUES_NUMB},
                FIRE_ID_COLM + "=?", new String[]{fire_id}, null, null, null);
        Question question = new Question();
        if (cursor.moveToNext()) {
            question.setmQuestion(cursor.getString(cursor.getColumnIndex(QUESTION_COLUMN)));
            question.setmFirstChoice(cursor.getString(cursor.getColumnIndex(FIRST_CH_COLM)));
            question.setmSecondChoice(cursor.getString(cursor.getColumnIndex(SECOND_CH_COLM)));
            question.setmThirdChoice(cursor.getString(cursor.getColumnIndex(THIRD_CH_COLM)));
            question.setmFourthChoice(cursor.getString(cursor.getColumnIndex(FOURTH_CH_COLM)));
            question.setmCorrectAnswer(cursor.getString(cursor.getColumnIndex(ANS_COLM)));
            question.setmFireId(cursor.getString(cursor.getColumnIndex(FIRE_ID_COLM)));
            question.setmQuestionNumber(cursor.getInt(cursor.getColumnIndex(QUES_NUMB)));
        }

        db.close();

        return question;
    }

    public ArrayList<Question> getAllQuestion() {
        ArrayList<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(QUESTION_TABLE,
                new String[]{QUESTION_ID, QUESTION_COLUMN, FIRST_CH_COLM, SECOND_CH_COLM,
                        THIRD_CH_COLM, FOURTH_CH_COLM, ANS_COLM, FIRE_ID_COLM,QUES_NUMB},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {

            do {
                Question question = new Question();
                question.setmQuestion(cursor.getString(cursor.getColumnIndex(QUESTION_COLUMN)));
                question.setmFirstChoice(cursor.getString(cursor.getColumnIndex(FIRST_CH_COLM)));
                question.setmSecondChoice(cursor.getString(cursor.getColumnIndex(SECOND_CH_COLM)));
                question.setmThirdChoice(cursor.getString(cursor.getColumnIndex(THIRD_CH_COLM)));
                question.setmFourthChoice(cursor.getString(cursor.getColumnIndex(FOURTH_CH_COLM)));
                question.setmCorrectAnswer(cursor.getString(cursor.getColumnIndex(ANS_COLM)));
                question.setmFireId(cursor.getString(cursor.getColumnIndex(FIRE_ID_COLM)));
                question.setmQuestionNumber(cursor.getInt(cursor.getColumnIndex(QUES_NUMB)));

                questionList.add(question);
            }
            while (cursor.moveToNext());
        }

        Log.i(TAG, "get all Question");

        db.close();

        return questionList;
    }

    public void updateQuestion(Question question, String fire_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION_COLUMN, question.getmQuestion());
        values.put(FIRST_CH_COLM, question.getmFirstChoice());
        values.put(SECOND_CH_COLM, question.getmSecondChoice());
        values.put(THIRD_CH_COLM, question.getmThirdChoice());
        values.put(FOURTH_CH_COLM, question.getmFourthChoice());
        values.put(ANS_COLM, question.getmCorrectAnswer());
        values.put(FIRE_ID_COLM, question.getmFireId());
        values.put(QUES_NUMB,question.getmQuestionNumber());

        db.update(QUESTION_TABLE, values, FIRE_ID_COLM + "=?", new String[]{fire_id});
        Log.i(TAG, "update question");
        db.close();
    }

    public void deleteQuestion(String fire_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(QUESTION_TABLE, FIRE_ID_COLM + "=?", new String[]{fire_id});
        Log.i(TAG, "delete question");
        db.close();
    }

    public void deleteAllRow() {
        SQLiteDatabase db = getWritableDatabase();
       /* db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE);

        String CREATE_QUESTION_TABLE = "CREATE TABLE " + QUESTION_TABLE + "("
                + QUESTION_ID + " INTEGER PRIMARY KEY," + QUESTION_COLUMN + " TEXT,"
                + FIRST_CH_COLM + " TEXT," + SECOND_CH_COLM + " TEXT," + THIRD_CH_COLM + " TEXT," +
                FOURTH_CH_COLM + " TEXT," + ANS_COLM + " TEXT," + FIRE_ID_COLM + " TEXT" + ")";
        db.execSQL(CREATE_QUESTION_TABLE);*/
        db.delete(QUESTION_TABLE, null, null);


    }


}
