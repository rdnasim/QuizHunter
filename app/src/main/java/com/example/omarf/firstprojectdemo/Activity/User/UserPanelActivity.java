package com.example.omarf.firstprojectdemo.Activity.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.omarf.firstprojectdemo.Activity.SignInActivity;
import com.example.omarf.firstprojectdemo.QuestionPreference;
import com.example.omarf.firstprojectdemo.R;
import com.example.omarf.firstprojectdemo.SyncService;

public class UserPanelActivity extends AppCompatActivity {

    private static final String TAG = "UserPanelTag";
    TextView userNameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);
        userNameTV= (TextView) findViewById(R.id.userNametextView);
        String userName="Hi! " +QuestionPreference.getSignInUserName(this);
        userNameTV.setText(userName);

    }

    public void onClickPlayButton(View view) {
        startActivity(PlayActivity.startIntent(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"on destroy called");
        if (!SyncService.isServiceAlarmOn(this))
        SyncService.setSyncAlarm(this,true);
    }

    public void onClickSignOut(View view) {
        startActivity(new Intent(this, SignInActivity.class));
        QuestionPreference.setSignINUserName(this,null);
        finish();

    }
}

