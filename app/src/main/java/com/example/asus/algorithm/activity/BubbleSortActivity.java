package com.example.asus.algorithm.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asus.algorithm.R;
import com.example.asus.algorithm.Utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2018/1/8.
 */

public class BubbleSortActivity extends AppCompatActivity {

    private EditText et_input;
    private Button result;
    private TextView tv_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bubblesort);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        initView();
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataStr = et_input.getText().toString().trim();
                String[] dataInt = dataStr.split("\\s+");
                int[] arr=new int[dataInt.length];

                for (int i = 0; i < dataInt.length; i++) {
                    int a= Integer.parseInt(dataInt[i]);
                    //System.out.println(a);
                    arr[i]=a;
                }

                List<String> stringList=new ArrayList<>();
                //排序
                int temp = 0;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length- 1; j++) {
                        if (arr[j + 1] < arr[j]) {
                            temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                }
                //显示结果
                for(int i=0;i<arr.length;i++){
                    stringList.add(arr[i]+" ");
                    System.out.println(arr[i]+" ");
                }
                tv_res.setText(Util.convertListToStr(stringList));
            }
        });

    }

    private void initView() {
        et_input = (EditText) findViewById(R.id.et_input);
        result = (Button) findViewById(R.id.bt_bs);
        tv_res = (TextView) findViewById(R.id.tv_res);
    }
}
