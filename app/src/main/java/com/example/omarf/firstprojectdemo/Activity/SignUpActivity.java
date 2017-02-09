package com.example.omarf.firstprojectdemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omarf.firstprojectdemo.Database.DatabaseHelper;
import com.example.omarf.firstprojectdemo.Model.User;
import com.example.omarf.firstprojectdemo.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText mUserNameEditText;
    private EditText mUserPassEditText;
    private DatabaseHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mUserNameEditText = (EditText) findViewById(R.id.user_name_edit_text);
        mUserPassEditText = (EditText) findViewById(R.id.password_edit_text);
        mDb = new DatabaseHelper(this);
    }

    public void onClickRegister(View view) {

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
            mDb.addUser(new User(userName, pass));
            Toast.makeText(this, "Succesfully added", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, userName + " isn't available", Toast.LENGTH_SHORT).show();
        }


    }
}
