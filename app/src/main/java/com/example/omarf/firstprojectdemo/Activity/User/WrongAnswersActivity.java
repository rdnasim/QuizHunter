package com.example.omarf.firstprojectdemo.Activity.User;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.omarf.firstprojectdemo.Adapter.ResultAdapter;
import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.Model.QuestionSingleTon;
import com.example.omarf.firstprojectdemo.R;

import java.util.ArrayList;

public class WrongAnswersActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivityIntentTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        ArrayList<Question> questions= QuestionSingleTon.getInstance().getmResultList();

        ListView listView= (ListView) findViewById(R.id.result_list_view);
        ResultAdapter adapter=new ResultAdapter(this,questions);
        listView.setAdapter(adapter);

    }

    public static Intent startIntent(Context context) {
        return new Intent(context,WrongAnswersActivity.class);
    }
}
