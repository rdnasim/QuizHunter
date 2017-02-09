package com.example.omarf.firstprojectdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.omarf.firstprojectdemo.Model.Question;
import com.example.omarf.firstprojectdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omarf on 1/17/2017.
 */

public class ResultAdapter extends ArrayAdapter {
    private final ArrayList<Question> mQuestions;
    private final Context mContext;

    public ResultAdapter(Context context, ArrayList<Question> questions) {
        super(context, R.layout.result_row, questions);
        mQuestions=questions;
        mContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.result_row,parent,false);
            viewHolder.mQuestionTextView= (TextView) convertView.findViewById(R.id.question_text_view);
            viewHolder.mResultTextView= (TextView) convertView.findViewById(R.id.answer_text_view);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        Question question=mQuestions.get(position);

        viewHolder.mQuestionTextView.setText(question.getmQuestion());
        String answer="Answer: "+question.getmCorrectAnswer();
        viewHolder.mResultTextView.setText(answer);


        return convertView;
    }

    static class ViewHolder{
        private TextView mQuestionTextView;
        private TextView mResultTextView;
    }
}
