package com.example.omarf.firstprojectdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omarf.firstprojectdemo.Activity.Admin.AdminPanelActivity;
import com.example.omarf.firstprojectdemo.Activity.User.UserPanelActivity;
import com.example.omarf.firstprojectdemo.Database.DatabaseHelper;
import com.example.omarf.firstprojectdemo.FirebaseToSql;
import com.example.omarf.firstprojectdemo.Model.User;
import com.example.omarf.firstprojectdemo.QuestionPreference;
import com.example.omarf.firstprojectdemo.R;
import com.example.omarf.firstprojectdemo.SyncService;

public class SignInActivity extends AppCompatActivity {
    private static final String ADMIN_PASS = "12345";
    private static final String ADMIN_NAME = "npro";
    private static final String TAG = "Sign In";
    private EditText mUserNameEditText;
    private EditText mUserPassEditText;
    private DatabaseHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        if (SyncService.isServiceAlarmOn(this))
            SyncService.setSyncAlarm(this, false);
        mUserNameEditText = (EditText) findViewById(R.id.user_name_edit_text);
        mUserPassEditText = (EditText) findViewById(R.id.password_edit_text);

        mDb = new DatabaseHelper(this);

        //  QuestionPreference.setUserLoginFirst(this,true);

        if (QuestionPreference.isUserLoginFirstTime(this)) {
            Log.i(TAG, "run first time");
            mDb.addUser(new User(ADMIN_NAME, ADMIN_PASS));
            // startService(SyncService.startIntent(this));
            new FirebaseToSql(this).syncSqlite();
            QuestionPreference.setUserLoginFirst(this, false);
        }

        String userName=QuestionPreference.getSignInUserName(this);

        if (userName!=null){
            if (userName.equals(ADMIN_NAME)){
                startActivity(new Intent(this,AdminPanelActivity.class));
                finish();
            }
            else {
                startActivity(new Intent(this,UserPanelActivity.class));
                finish();
            }
        }


    }

    public void onClickSignUp(View view) {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }

    public void onClickSignIn(View view) {
        String userName = mUserNameEditText.getText().toString();
        String pass = mUserPassEditText.getText().toString();

        if (userName.isEmpty()) {

            mUserNameEditText.setError("Enter name");
            return;
        }
        if (pass.isEmpty()) {
            mUserPassEditText.setError("Enter Password");
            return;
        }


        User user = mDb.getUser(userName);

        if (user.getmUserName() == null) {
            Toast.makeText(this, "Wrong password or Username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (user.getmUserName().equals(ADMIN_NAME) && user.getmUserPass().equals(ADMIN_PASS)) {

            startActivity(new Intent(SignInActivity.this, AdminPanelActivity.class));
            QuestionPreference.setSignINUserName(this,ADMIN_NAME);
            finish();
            return;

        }


        if (user.getmUserName().equals(userName) && user.getmUserPass().equals(pass)) {

            startActivity(new Intent(SignInActivity.this, UserPanelActivity.class));
            QuestionPreference.setSignINUserName(this,userName);
            finish();

        } else {
            Toast.makeText(this, "Wrong password or Username", Toast.LENGTH_SHORT).show();
        }
    }
}
