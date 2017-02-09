package com.example.omarf.firstprojectdemo.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarf.firstprojectdemo.Activity.User.PlayActivity;
import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePlayFragment extends Fragment {


    private Question mQuestion;
    private TextView mQuestionTextView;
    private UserChooseAnswerListener mUserChooseListener;

    public ScreenSlidePlayFragment() {
        // Required empty public constructor
    }

    public void setmUserChooseListener(UserChooseAnswerListener mUserChooseListener) {
        this.mUserChooseListener = mUserChooseListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_question, container, false);
        mQuestionTextView = (TextView) view.findViewById(R.id.question_text_view);
        RadioButton firstChoiceRadioButton = (RadioButton) view.findViewById(R.id.first_choice_radio_button);
        RadioButton secondChoiceRadioButton = (RadioButton) view.findViewById(R.id.second_choice_radio_button);
        RadioButton thirdChoiceRadioButton = (RadioButton) view.findViewById(R.id.third_choice_radio_button);
        RadioButton fourthChoiceRadioButton = (RadioButton) view.findViewById(R.id.fourth_choice_radio_button);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.answer_radio_group);


        mQuestionTextView.setText(mQuestion.getmQuestion());
        firstChoiceRadioButton.setText(mQuestion.getmFirstChoice());
        secondChoiceRadioButton.setText(mQuestion.getmSecondChoice());
        thirdChoiceRadioButton.setText(mQuestion.getmThirdChoice());
        fourthChoiceRadioButton.setText(mQuestion.getmFourthChoice());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.first_choice_radio_button:
                        mUserChooseListener.getClickAnswer(mQuestion.getmFirstChoice());
                        break;
                    case R.id.second_choice_radio_button:
                        mUserChooseListener.getClickAnswer(mQuestion.getmSecondChoice());
                        break;
                    case R.id.third_choice_radio_button:
                        mUserChooseListener.getClickAnswer(mQuestion.getmThirdChoice());
                        break;
                    case R.id.fourth_choice_radio_button:
                        mUserChooseListener.getClickAnswer(mQuestion.getmFourthChoice());
                        break;
                    default:
                        mUserChooseListener.getClickAnswer(PlayActivity.NOT_CHOOSEN);
                }
            }
        });

        return view;
    }

    public void setQuestion(Question question) {
        mQuestion = question;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mUserChooseListener= (UserChooseAnswerListener) context;
    }

    public interface UserChooseAnswerListener {
        public void getClickAnswer(String userAnswer);
    }
}
