package com.example.omarf.firstprojectdemo.Activity.Admin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.omarf.firstprojectdemo.Adapter.ListQuestionAdapter;
import com.example.omarf.firstprojectdemo.Database.DatabaseHelper;
import com.example.omarf.firstprojectdemo.Database.FirebaseHelper;
import com.example.omarf.firstprojectdemo.FirebaseToSql;
import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.Model.QuestionSingleTon;
import com.example.omarf.firstprojectdemo.QuestionPreference;
import com.example.omarf.firstprojectdemo.R;

import java.util.ArrayList;

public class ListOfQuestionAdminActivity extends AppCompatActivity implements FirebaseHelper.QuestionListListener {

    public static final String IntentTAG = "ShowingListOfActivityIntentTag";
    private static final String TAG = "ShowingListOfActivity";
    private ListQuestionAdapter mAdapter;

    ListView mListView;
    private ArrayList<Question> mQuestions;
    private DatabaseHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_list_of_question);
        mListView = (ListView) findViewById(R.id.list_question);

        //   mQuestions= QuestionSingleTon.getInstance().getQuestionList();

        if (QuestionPreference.isUserLoginFirstTime(this)) {
            new FirebaseToSql(this).syncSqlite();

            FirebaseHelper firebaseHelper = new FirebaseHelper();
            firebaseHelper.setmQuestionListListener(this);

            firebaseHelper.getAllQuestion();
            QuestionPreference.setUserLoginFirst(this, false);

        } else {
            mDb = new DatabaseHelper(this);
            // mQuestions = QuestionSingleTon.getInstance().getQuestionList();
            //  QuestionSingleTon.getInstance().setmQuestion(mQuestions);
            mQuestions = mDb.getAllQuestion();
            mAdapter = new ListQuestionAdapter(this, mQuestions);
            mListView.setAdapter(mAdapter);
        }


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "list view item clicked");
                // QuestionSingleTon.getInstance().setmQuestion(mQuestions);
                Intent intent = AddEditQuestionAdminActivity.startIntent(ListOfQuestionAdminActivity.this);
                intent.putExtra(AddEditQuestionAdminActivity.INTENT_TAG, i);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_question_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu_item:
                Intent intent = AddEditQuestionAdminActivity.startIntent(this);
                startActivity(intent);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //  mQuestions=mDb.getAllQuestion();
        // mAdapter.notifyDataSetChanged();

        mQuestions.clear();
        mQuestions.addAll(mDb.getAllQuestion());
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static Intent startIntent(Context context) {
        return new Intent(context, ListOfQuestionAdminActivity.class);
    }

    @Override
    public void onReceiveQuestionList(ArrayList<Question> questions) {
        // mQuestions = questions;
        Log.i(TAG, "on recieve questionlist from firebase" + questions.size());
        mQuestions.clear();
        mQuestions.addAll(questions);

        //  QuestionSingleTon.getInstance().setmQuestion(questions);

        mAdapter = new ListQuestionAdapter(this, mQuestions);
        mListView.setAdapter(mAdapter);
        /*mAdapter.notifyDataSetChanged();*/
    }
}
