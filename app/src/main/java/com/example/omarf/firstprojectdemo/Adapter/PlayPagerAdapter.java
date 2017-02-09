package com.example.omarf.firstprojectdemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.omarf.firstprojectdemo.Fragment.ScreenSlidePlayFragment;
import com.example.omarf.firstprojectdemo.Model.Question;

import java.util.ArrayList;

/**
 * Created by omarf on 1/11/2017.
 */

public class PlayPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Question> mQuestions;
    public PlayPagerAdapter(FragmentManager fm, ArrayList<Question> questions) {
        super(fm);
        mQuestions=questions;
    }



    @Override
    public Fragment getItem(int position) {
       Question question=mQuestions.get(position);
        ScreenSlidePlayFragment fragment=new ScreenSlidePlayFragment();
        fragment.setQuestion(question);
        return fragment;
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }
}
