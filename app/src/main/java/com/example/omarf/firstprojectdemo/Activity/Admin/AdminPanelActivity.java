package com.example.omarf.firstprojectdemo.Activity.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.omarf.firstprojectdemo.Activity.SignInActivity;
import com.example.omarf.firstprojectdemo.Activity.User.PlayActivity;
import com.example.omarf.firstprojectdemo.QuestionPreference;
import com.example.omarf.firstprojectdemo.R;
import com.example.omarf.firstprojectdemo.SyncService;

public class AdminPanelActivity extends AppCompatActivity {

    private Button mAdminButton;
    private Button mUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        if (SyncService.isServiceAlarmOn(this))
            SyncService.setSyncAlarm(this, false);

       // QuestionSingleTon.getInstance().updateQuestionList();
        mAdminButton= (Button) findViewById(R.id.adminPanelButton);
        mUserButton= (Button) findViewById(R.id.userButton);

        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= ListOfQuestionAdminActivity.startIntent(AdminPanelActivity.this);
                startActivity(intent);
            }
        });

        mUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= PlayActivity.startIntent(AdminPanelActivity.this);
                startActivity(intent);
            }
        });

    }

    public void onClickSignOut(View view) {
        finish();
        QuestionPreference.setSignINUserName(this,null);
        startActivity(new Intent(AdminPanelActivity.this, SignInActivity.class));

    }





}
