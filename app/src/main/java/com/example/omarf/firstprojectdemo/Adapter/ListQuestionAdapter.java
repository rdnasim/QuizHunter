package com.example.omarf.firstprojectdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omarf on 1/11/2017.
 */

public class ListQuestionAdapter extends ArrayAdapter<Question> {
    private  ArrayList<Question> mQuestions;
    private Context mContext;

    public ListQuestionAdapter(Context context, List<Question> questions) {
        super(context, R.layout.activity_showing_list_of_question, questions);
        mContext=context;
        mQuestions = (ArrayList<Question>) questions;
    }

//    TODO: use viewHolder
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        RadioButton firstChoiceRadioButton;
        RadioButton secondChoiceRadioButton;
        RadioButton thirdChoiceRadioButton;
        RadioButton fourthChoiceRadioButton;

        ViewHolder viewHolder=null;
       /* if(convertView==null){*/
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.list_qustion_row,parent,false);

            viewHolder.questionTextView=(TextView) convertView.findViewById(R.id.question_text_view);
             viewHolder.firstChoiceRadioButton= (RadioButton) convertView.findViewById(R.id.first_choice_radio_button);
             viewHolder.secondChoiceRadioButton= (RadioButton) convertView.findViewById(R.id.second_choice_radio_button);
            viewHolder.thirdChoiceRadioButton= (RadioButton) convertView.findViewById(R.id.third_choice_radio_button);
             viewHolder.fourthChoiceRadioButton= (RadioButton) convertView.findViewById(R.id.fourth_choice_radio_button);
//            convertView.setTag(viewHolder);
//        }
        /*else {
            viewHolder= (ViewHolder) convertView.getTag();
        }*/
        Question question= mQuestions.get(position);

        viewHolder.questionTextView.setText(question.getmQuestion());
        viewHolder.firstChoiceRadioButton.setText(question.getmFirstChoice());
        viewHolder.secondChoiceRadioButton.setText(question.getmSecondChoice());
        viewHolder.thirdChoiceRadioButton.setText(question.getmThirdChoice());
        viewHolder.fourthChoiceRadioButton.setText(question.getmFourthChoice());

        if(question.getmCorrectAnswer().equals(question.getmFirstChoice())){
            viewHolder.firstChoiceRadioButton.setChecked(true);
        }
        else  if(question.getmCorrectAnswer().equals(question.getmSecondChoice())){
            viewHolder.secondChoiceRadioButton.setChecked(true);
        }
        else  if(question.getmCorrectAnswer().equals(question.getmThirdChoice())){
            viewHolder.thirdChoiceRadioButton.setChecked(true);
        }
        else  if(question.getmCorrectAnswer().equals(question.getmFourthChoice())){
            viewHolder.fourthChoiceRadioButton.setChecked(true);
        }






        return convertView;
    }

    static class ViewHolder{
        TextView questionTextView;
        RadioButton firstChoiceRadioButton;
        RadioButton secondChoiceRadioButton;
        RadioButton thirdChoiceRadioButton;
        RadioButton fourthChoiceRadioButton;

    }




}
