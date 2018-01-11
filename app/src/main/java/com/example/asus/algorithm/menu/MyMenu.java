package com.example.asus.algorithm.menu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.asus.algorithm.R;

/**
 * Created by Asus on 2018/1/10.
 */

public class MyMenu {

    /**
     * 菜单栏显示方法
     * @param view
     */
    public static void showPopupMenu(final Activity activity, View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(activity, view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());


        //onCreateOptionsMenu(Menu menu,final Activity activity);
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                MenuItem login = item.setIcon(R.drawable.login);
                login.getIcon();

                //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                //判断是否是注册
                if ("注册".equals(item.getTitle())){

                    Toast.makeText(activity, item.getTitle(), Toast.LENGTH_SHORT).show();
                }else if("登录".equals(item.getTitle())){
                    Toast.makeText(activity, item.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent("com.example.asus.algorithm.login");
                    //intent.putExtra("","");
                    activity.startActivity(intent);

                }else{
                    Toast.makeText(activity, item.getTitle(), Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                // Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }


   /* private static void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean onCreateOptionsMenu(Menu menu,final Activity activity) {

        MenuInflater inflater = new MenuInflater(activity);
        inflater.inflate(R.menu.menu,menu);
        setIconEnable(menu,true);  //  就是这一句使图标能显示
        return onCreateOptionsMenu(menu,activity);
    }*/
}
