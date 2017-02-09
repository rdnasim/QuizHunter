package com.example.omarf.firstprojectdemo.Activity.Admin;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.omarf.firstprojectdemo.Database.DatabaseHelper;
import com.example.omarf.firstprojectdemo.Database.FirebaseHelper;
import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.Model.QuestionSingleTon;
import com.example.omarf.firstprojectdemo.QuestionPreference;
import com.example.omarf.firstprojectdemo.R;

import java.util.ArrayList;

public class AddEditQuestionAdminActivity extends AppCompatActivity {

    public static final String INTENT_TAG = "AddOrEditQuestionActivityIntentTAG";
    EditText mQuestionEditText;
    EditText mFirstChoiceEditText;
    EditText mSecondChoiceEditText;
    EditText mThirdChoiceEditText;
    EditText mFourthChoiceEditText;
    Button mEditOrSaveButton;
    RadioButton mPreviousRadioButton;
    RadioButton firstChoiceRadioButton;
    RadioButton secondChoiceRadioButton;
    RadioButton thirdChoiceRadioButton;
    RadioButton fourthChoiceRadioButton;

    private String mResult;
    private ArrayList<Question> mQuestionList;
    private boolean isEditMode;
    private int mPostion;
    private FirebaseHelper mFirebaseHelper;
    private Button mDeleteButton;
    private DatabaseHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_question);
        mDb=new DatabaseHelper(this);
        mFirebaseHelper=new FirebaseHelper();
         mEditOrSaveButton= (Button) findViewById(R.id.save_edit_button);
        mFirstChoiceEditText = (EditText) findViewById(R.id.first_choice_edit_text);
        mSecondChoiceEditText = (EditText) findViewById(R.id.second_choice_edit_text);
        mThirdChoiceEditText = (EditText) findViewById(R.id.third_choice_edit_text);
        mFourthChoiceEditText = (EditText) findViewById(R.id.fourth_choice_edit_text);
        mDeleteButton= (Button) findViewById(R.id.deleteButton);
        mQuestionEditText = (EditText) findViewById(R.id.question_edit_text);
        firstChoiceRadioButton= (RadioButton) findViewById(R.id.first_choice_radio_button);
        secondChoiceRadioButton= (RadioButton) findViewById(R.id.second_choice_radio_button);
        thirdChoiceRadioButton= (RadioButton) findViewById(R.id.third_choice_radio_button);
        fourthChoiceRadioButton= (RadioButton) findViewById(R.id.fourth_choice_radio_button);

      // mQuestionList = QuestionSingleTon.getInstance().getQuestionList();
        mQuestionList=mDb.getAllQuestion();
        mDeleteButton.setVisibility(View.GONE);

    mPostion = getIntent().getIntExtra(INTENT_TAG, -1);
        if (mPostion >= 0) {
            isEditMode = true;
            Question question = mQuestionList.get(mPostion);
            mQuestionEditText.setText(question.getmQuestion());
            mFirstChoiceEditText.setText(question.getmFirstChoice());
            mSecondChoiceEditText.setText(question.getmSecondChoice());
            mThirdChoiceEditText.setText(question.getmThirdChoice());
            mFourthChoiceEditText.setText(question.getmFourthChoice());
            mEditOrSaveButton.setText("Edit");
            mDeleteButton.setVisibility(View.VISIBLE);
        }


    }

    public void onClickEditOrSaveButton(View view) {
        String questionText = mQuestionEditText.getText().toString();
        String firstChoice = mFirstChoiceEditText.getText().toString();
        String secondChoice = mSecondChoiceEditText.getText().toString();
        String thirdChoice = mThirdChoiceEditText.getText().toString();
        String fourthChoice = mFourthChoiceEditText.getText().toString();

        boolean isAnyoneEmpty = false;
        if (questionText.isEmpty()) {
            mQuestionEditText.setError("write question");
            isAnyoneEmpty = true;

        }
        if (firstChoice.isEmpty()) {
            mFirstChoiceEditText.setError("write ");
            isAnyoneEmpty = true;

        }
        if (secondChoice.isEmpty()) {
            mSecondChoiceEditText.setError("write ");
            isAnyoneEmpty = true;

        }
        if (thirdChoice.isEmpty()) {
            mThirdChoiceEditText.setError("write ");
            isAnyoneEmpty = true;

        }
        if (fourthChoice.isEmpty()) {
            mFourthChoiceEditText.setError("write ");
            isAnyoneEmpty = true;

        }


        if (isAnyoneEmpty)
            return;

        if(!firstChoiceRadioButton.isChecked() && !secondChoiceRadioButton.isChecked() &&
                !thirdChoiceRadioButton.isChecked() && !fourthChoiceRadioButton.isChecked())
        {
            Toast.makeText(this, "please select a question", Toast.LENGTH_SHORT).show();
            return;
        }

        Question question = new Question(questionText,
                firstChoice,
                secondChoice,
                thirdChoice,
                fourthChoice,
                mResult);



        if (!isEditMode) {
           // QuestionSingleTon.getInstance().getQuestionList().add(question);

            int number= QuestionPreference.getQuestionNumber(this)+1;
            QuestionPreference.setQuestionNumber(this,number);
            question.setmQuestionNumber(number);

            mFirebaseHelper.writeNewQuestion(question);
            mDb.addQuestion(question);
            mDeleteButton.setVisibility(View.GONE);

            finish();

        } else {

            String id= mQuestionList.get(mPostion).getmFireId();
            int questionNumber=mQuestionList.get(mPostion).getmQuestionNumber();
            question.setmFireId(id);
            question.setmQuestionNumber(questionNumber);
           // QuestionSingleTon.getInstance().getQuestionList().set(mPostion,question);
            mFirebaseHelper.updateQuestion(question);
            mDb.updateQuestion(question,id);

            finish();


        }

       /* Intent intent = ListOfQuestionAdminActivity.startIntent(this);
        startActivity(intent);*/

    }

    public void onClickRadioButton(View view) {

        RadioButton radioButton = (RadioButton) view;

        if (mPreviousRadioButton == null) {
            mPreviousRadioButton = radioButton;

        } else {
            mPreviousRadioButton.setChecked(false);
            mPreviousRadioButton = radioButton;
        }

        switch (view.getId()) {
            case R.id.first_choice_radio_button:
                mResult = mFirstChoiceEditText.getText().toString();
                if (mResult.isEmpty()){

                   radioButton.setChecked(false);
                }
                else {
                    radioButton.setChecked(true);
                }
                break;
            case R.id.second_choice_radio_button:
                mResult = mSecondChoiceEditText.getText().toString();
                if (mResult.isEmpty()){
                    radioButton.setChecked(false);
                }
                else {
                    radioButton.setChecked(true);
                }
                break;
            case R.id.third_choice_radio_button:
                mResult = mThirdChoiceEditText.getText().toString();
                if (mResult.isEmpty()){
                    radioButton.setChecked(false);
                }
                else {
                    radioButton.setChecked(true);
                }
                break;
            case R.id.fourth_choice_radio_button:
                mResult = mFourthChoiceEditText.getText().toString();
                if (mResult.isEmpty()){
                    radioButton.setChecked(false);
                }
                else {
                    radioButton.setChecked(true);
                }
                break;

        }



    }

    public static Intent startIntent(Context context) {
        return new Intent(context, AddEditQuestionAdminActivity.class);
    }

    public void onClickDeleteButton(View view) {
        mFirebaseHelper.deleteQuestion(mQuestionList.get(mPostion).getmFireId());
        mDb.deleteQuestion(mQuestionList.get(mPostion).getmFireId());
        //QuestionSingleTon.getInstance().getQuestionList().remove(mPostion);
//        mQuestionList.remove(mPostion);
        this.finish();
    }
}
