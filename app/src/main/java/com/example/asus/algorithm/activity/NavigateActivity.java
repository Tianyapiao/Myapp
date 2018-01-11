package com.example.asus.algorithm.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.algorithm.R;

/**
 * Created by Asus on 2018/1/8.
 */

public class NavigateActivity  extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CoordinatorLayout right;
    private NavigationView left;
    private boolean isDrawer=false;


    /*private DescribleFragment fg1,fg2,fg3;*/
    private View describle;
    private TextView tv_bubble;
    private TextView tv_algorithm_name;
    private View bublle;
    private View bubble;
    private View headerLayout;
    private TextView tv_maopao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        right = (CoordinatorLayout) findViewById(R.id.right);
        //第一步：获取NavigationView
        left = (NavigationView) findViewById(R.id.nav_view);
        //第二步获取headerLayout
        headerLayout = left.inflateHeaderView(R.layout.nav_header_main);

        if (headerLayout!=null){
           // headerLayout.setVisibility(View.INVISIBLE);
            //第三步：获取其中的组件：
            tv_maopao = (TextView)headerLayout.findViewById(R.id.tv_maopao);
        }

        Intent intent = getIntent();
        if (intent!=null ){
            String title = intent.getStringExtra("title");
            String[] titleStr= title.toString().trim().split("\\s+");
            tv_maopao.setText(titleStr[1]);
        }



        //View viewById = left.findViewById(R);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(isDrawer){
                    return left.dispatchTouchEvent(motionEvent);

                }else{
                    return false;
                }
            }
        });
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer=true;
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                /**
                  *  设置右面的布局位置  根据左面菜单的right作为右面布局的left
                  *  左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                 */
                right.layout(left.getRight(), 0, left.getRight() + display.getWidth(), display.getHeight());
            }
            @Override
            public void onDrawerOpened(View drawerView) {}
            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawer=false;
            }
            @Override
            public void onDrawerStateChanged(int newState) {}
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        describle = right.findViewById(R.id.des);
        if (id == R.id.nav_camera) {
            //如果选中的是算法描述
            Toast.makeText(this,"您选择了算法描述", Toast.LENGTH_SHORT).show();
            tv_algorithm_name = (TextView) describle.findViewById(R.id.tv_algorithm_name);
            tv_algorithm_name.setText("冒泡排序算法");

            //describle.setVisibility(View.VISIBLE);
            tv_bubble = (TextView) describle.findViewById(R.id.tv_bubble);
            tv_bubble.setText("冒泡排序的基本思想就是：从无序序列头部开始，进行两两比较，根据大小交换位置，" +
                    "直到最后将最大（小）的数据元素交换到了无序队列的队尾，从而成为有序序列的一部分；" +
                    "下一次继续这个过程，直到所有数据元素都排好序" +
                    "算法的核心在于每次通过两两比较交换位置，选出剩余无序序列里最大（小）的数据元素放到队尾。");

        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this,"您选择了排序", Toast.LENGTH_SHORT).show();
            bubble = right.findViewById(R.id.bubble);
            describle.setVisibility(View.INVISIBLE);
            bubble.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        /*transaction.commit();*/
        return true;
    }
}
