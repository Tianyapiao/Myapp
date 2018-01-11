package com.example.asus.algorithm.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.algorithm.R;
import com.example.asus.algorithm.db.UserDao;
import com.example.asus.algorithm.db.entity.User;

/**
 * Created by Asus on 2018/1/8.
 * 登录页面的处理
 */

public class LoginActivity extends AppCompatActivity {


    private Button btn_login;
    private Button btn_register;
    private EditText et_phone;
    private EditText et_password;
    private User user=new User();
    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        initView();
        userDao=new UserDao(this);

    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);

        et_phone = (EditText) findViewById(R.id.et_usertel);
        et_password = (EditText) findViewById(R.id.et_password);
    }

    public void click(View view) {
        String phone =  et_phone.getText().toString().trim();
        String password =  et_password.getText().toString().trim();

        switch (view.getId()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
                    Toast.makeText(this,"手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                }else if(phone.equals(user.getPhone())&&phone.equals(user.getPassword())){
                    Toast.makeText(this,"登录成功", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_register:
                if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
                    Toast.makeText(this,"手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    // 保存数据到数据库，并且同步显示到界面
                    boolean flag = userDao.register(phone);
                    if (flag){
                        Toast.makeText(this,"注册失败", Toast.LENGTH_SHORT).show();
                    }else{
                        user.setPhone(phone);
                        user.setPassword(password);
                        boolean result = userDao.insert(user);
                        if (result) {
                            Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show();
                            throw new RuntimeException();
                        }
                    }
                }

                break;

        }
    }
}
