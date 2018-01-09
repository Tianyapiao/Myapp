package com.example.asus.algorithm;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.asus.algorithm.fragment.IndexFragment;
import com.example.asus.algorithm.fragment.PaixuFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rd_group;
    private RadioButton rd_index;
    private RadioButton rd_paixu;
    private RadioButton rd_profile;

    private IndexFragment fg1,fg3;
    private PaixuFragment fg2;

    private TextView topbar;

    //构造适配器
    //List<Fragment> fragments=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        // UI组件初始化与事件绑定 
        initView();

        //fragments.add(new PaixuFragment());

        //FPAdapter adapter = new FPAdapter(getSupportFragmentManager(), fragments);
    }

    private void initView() {

        //找控件
        topbar = (TextView) findViewById(R.id.txt_topbar);


        rd_group = (RadioGroup) findViewById(R.id.rd_group);
        rd_group.setOnCheckedChangeListener(this);
        
        rd_index = (RadioButton) findViewById(R.id.rd_index);
        rd_paixu = (RadioButton) findViewById(R.id.rd_paixu);
        rd_profile = (RadioButton) findViewById(R.id.rd_profile);

        rd_index.setChecked(true);

    }

    //隐藏所有的Fragment
    public void hideAllFragment(FragmentTransaction transaction){

        if(fg1!=null){
            transaction.hide(fg1);
        }
        if(fg2!=null){
            transaction.hide(fg2);
        }
        if(fg3!=null){
            transaction.hide(fg3);
        }
        
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId){
            case R.id.rd_index:
                if (topbar!=null){
                    topbar.setText("首页");
                }

                if(fg1==null){
                    fg1 = new IndexFragment("第一个Fragment");

                    transaction.add(R.id.fragment_container,fg1);
                }else{
                    transaction.show(fg1);
                }
                break;
            case R.id.rd_paixu:
                if (topbar!=null){
                    topbar.setText("排序算法");
                }
                if(fg2==null){
                    fg2 = new PaixuFragment();

                    transaction.add(R.id.fragment_container,fg2);
                }else{
                    transaction.show(fg2);
                }
                break;
            case R.id.rd_profile:
                if (topbar!=null){
                    topbar.setText("我的");
                }
                if(fg3==null){
                    fg3 = new IndexFragment("第三个Fragment");

                    transaction.add(R.id.fragment_container,fg3);
                }else{
                    transaction.show(fg3);
                }
                break;

        }
        transaction.commit();
    }

}
