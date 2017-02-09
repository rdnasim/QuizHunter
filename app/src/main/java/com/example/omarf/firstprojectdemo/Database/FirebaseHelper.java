package com.example.omarf.firstprojectdemo.Database;

import android.content.Context;
import android.util.Log;

import com.example.omarf.firstprojectdemo.Model.Question;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by omarf on 1/14/2017.
 */

public class FirebaseHelper{
    public static final String QUESTION_COLUMN = "question";
    private static final String TAG = "FIREBASE_HELPER_TAG";
    private static final String QUESTION_NUMBER = "questionNumber";
    private FirebaseDatabase mDatabase=FirebaseDatabase.getInstance();
    private Context mContext;
    private QuestionListListener mQuestionListListener;

    public void setmQuestionListListener(QuestionListListener mQuestionListListener) {
        this.mQuestionListListener = mQuestionListListener;
    }

    public void writeNewQuestion(Question question){

        String key = mDatabase.getReference().child(QUESTION_COLUMN).push().getKey();

        mDatabase.getReference().child(QUESTION_COLUMN).child(key).setValue(question);
        question.setmFireId(key);
    }

    public void updateQuestion(Question question){
        Map<String,Object> questionMap=question.toMap();
        Map<String,Object> updateMap=new HashMap<>();
        String key=question.getmFireId();
        updateMap.put("/"+QUESTION_COLUMN+"/"+key,questionMap);
        mDatabase.getReference().updateChildren(updateMap);

    }
    public void getAllQuestion()
    {
        final ArrayList<Question> questionList=new ArrayList<>();


        Query questionReference=FirebaseDatabase.getInstance().getReference().child(QUESTION_COLUMN);
        questionReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Question question=snapshot.getValue(Question.class);

                    question.setmFireId(snapshot.getKey());
                    questionList.add(question);
                }
                mQuestionListListener.onReceiveQuestionList(questionList);


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG,"fetching all datas error");
            }
        });

  //      questionReference.addChildEventListener(myChildEventListener );







    }


    public void deleteQuestion(String fireid){

        mDatabase.getReference().child(QUESTION_COLUMN).child(fireid).setValue(null);
    }

    public interface QuestionListListener{
        public void onReceiveQuestionList(ArrayList<Question> questions);
    }


    /*public void addQuestionNumber(int number){
            mDatabase.getReference().child(QUESTION_NUMBER).setValue(number);
        mDatabase.getReference().child(QUESTION_COLUMN).child(QUESTION_NUMBER).setValue(null);
    }*/


    public void removeChildListener(){
        Query questionReference=FirebaseDatabase.getInstance().getReference().child(QUESTION_COLUMN);
        questionReference.removeEventListener(myChildEventListener);
    }


 private ChildEventListener myChildEventListener=new ChildEventListener() {
     @Override
     public void onChildAdded(DataSnapshot dataSnapshot, String s) {

     }

     @Override
     public void onChildChanged(DataSnapshot dataSnapshot, String s) {

     }

     @Override
     public void onChildRemoved(DataSnapshot dataSnapshot) {

     }

     @Override
     public void onChildMoved(DataSnapshot dataSnapshot, String s) {

     }

     @Override
     public void onCancelled(DatabaseError databaseError) {

     }
 };
    
}
