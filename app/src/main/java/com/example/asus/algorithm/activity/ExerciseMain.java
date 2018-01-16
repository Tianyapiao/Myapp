package com.example.asus.algorithm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.asus.algorithm.R;

/**
 * Created by Administrator on 2018\1\11 0011.
 */

public class ExerciseMain extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private Button btn_pre;
    private Button btn_next;
    boolean isFirst = false;
    boolean isLast = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_main);
        initView();
        initViewPager();
    }

    private void initViewPager() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            //获取第position位置的Fragment
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        //fragment = new FirstFragment();
                        isFirst = true;
                        //System.out.println(flag);
                        fragment = new Exercise1();
                        //flag = false;
                        break;
                    case 1:
                        fragment = new Exercise2();
                        break;
                    case 2:
                        isLast = true;
                        fragment = new Exercise3();
                        break;
                }

                return fragment;
            }

            //该方法的返回值i表明该Adapter总共包括多少个Fragment
            @Override
            public int getCount() {
                return 3;
            }

        };
        //为ViewPager组件设置FragmentPagerAdapter
        viewPager.setAdapter(pagerAdapter);
        //为viewpager组件绑定时间监听器
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            //当ViewPager显示的Fragment发生改变时激发该方法
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    //如果是点击的第一个button，那么就让第一个button的字体变为蓝色
                    //其他的button的字体的颜色变为黑色
                    case 0:

                        if (isFirst == true){
                            Toast.makeText(getApplicationContext(), "这是第一个题目", Toast.LENGTH_SHORT).show();
                        }
                    case 1:

                        if (isFirst == true){
                            Toast.makeText(getApplicationContext(), "这是第2个题目", Toast.LENGTH_SHORT).show();
                        }
                    case 2:

                        if (isFirst == true){
                            Toast.makeText(getApplicationContext(), "这是第3个题目", Toast.LENGTH_SHORT).show();
                        }

                }
                super.onPageSelected(position);
            }
        });
    }


    private void initView() {
//        btn_pre = (Button)findViewById(R.id.previos);
//        btn_next = (Button)findViewById(R.id.next);
//        btn_pre.setOnClickListener(this);
//        btn_next.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.previos:
//                if (isFirst == true){
//                    Toast.makeText(getApplicationContext(), "已经是第一个题目", Toast.LENGTH_SHORT).show();
//                }else if (isLast == true){
//                    viewPager.setCurrentItem(1);
//                }else
//                    viewPager.setCurrentItem(0);
//                break;
//            case R.id.next:
//                if (isFirst == true){
//                    viewPager.setCurrentItem(1);
//                }else if (isLast == true){
//                    Toast.makeText(getApplicationContext(), "已经是最后一个题目", Toast.LENGTH_SHORT).show();
//                }else
//                    viewPager.setCurrentItem(2);
//                break;
//
//        }
    }
}
