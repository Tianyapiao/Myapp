package com.example.asus.algorithm;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.asus.algorithm.fragment.IndexFragment;
import com.example.asus.algorithm.fragment.MyInfoFragment;
import com.example.asus.algorithm.fragment.PaixuFragment;
import com.example.asus.algorithm.menu.MyMenu;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rd_group;
    private RadioButton rd_index;
    private RadioButton rd_paixu;
    private RadioButton rd_profile;

    private IndexFragment fg1;
    private PaixuFragment fg2;
    private MyInfoFragment fg3;

    private TextView topbar;
    private ImageView iv_more;

    private String username;
    private TextView tv_name;

    //构造适配器
    //List<Fragment> fragments=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       iv_more = (ImageView) findViewById(R.id.iv_more);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        // UI组件初始化与事件绑定 
        initView();

    }

    private void initView() {

        //找控件
        topbar = (TextView) findViewById(R.id.txt_topbar);


        rd_group = (RadioGroup) findViewById(R.id.rd_group);
        rd_group.setOnCheckedChangeListener(this);
        
        rd_index = (RadioButton) findViewById(R.id.rd_index);
        rd_paixu = (RadioButton) findViewById(R.id.rd_paixu);
        rd_profile = (RadioButton) findViewById(R.id.rd_profile);

        Intent data = getIntent();
        int id = data.getIntExtra("id", 0);
        username=data.getStringExtra("username");

        if (id==3){
            rd_profile.setChecked(true);
            /*if (username!=null){
                tv_name = (TextView) getSupportFragmentManager().
                        findFragmentById(R.id.fragment_container)
                        .getView().findViewById(R.id.tv_name);
                if (tv_name!=null){
                    tv_name.setText(username);
                }
            }*/

        }else{
            rd_index.setChecked(true);
        }


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
                    if (iv_more!=null){
                        iv_more.setVisibility(View.INVISIBLE);
                    }
                }
                if(fg1==null){
                    fg1 = new IndexFragment();
                    transaction.add(R.id.fragment_container,fg1);
                }else{
                    transaction.show(fg1);
                }
                break;
            case R.id.rd_paixu:
                if (topbar!=null){
                   topbar.setText("排序算法");
                    if (iv_more!=null){
                        iv_more.setVisibility(View.INVISIBLE);
                    }

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
                    iv_more.setVisibility(View.VISIBLE);
                    iv_more.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //显示菜单
                            MyMenu.showPopupMenu(MainActivity.this,iv_more);
                        }
                    });
                }
                if(fg3==null){
                    fg3 = new MyInfoFragment();
                    transaction.add(R.id.fragment_container,fg3);
                }else{
                    transaction.show(fg3);
                }
                break;

        }
        transaction.commit();
    }

}
