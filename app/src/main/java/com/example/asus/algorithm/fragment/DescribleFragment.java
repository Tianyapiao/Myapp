package com.example.asus.algorithm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.algorithm.R;

/**
 * Created by Asus on 2018/1/7.
 */

public class DescribleFragment extends Fragment {
    private String context;

    private TextView tv_algorithm_name;


    public DescribleFragment(String context) {
        this.context = context;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.algorithm_describle, container, false);
        tv_algorithm_name = (TextView) view.findViewById(R.id.tv_algorithm_name);
        tv_algorithm_name.setText("冒泡排序算法");

        return view;
    }
}
