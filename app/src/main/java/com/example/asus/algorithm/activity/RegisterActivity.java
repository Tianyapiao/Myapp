package com.example.asus.algorithm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.algorithm.MainActivity;
import com.example.asus.algorithm.R;

/**
 * Created by Asus on 2018/1/8.
 * registerActivity
 * @author Asus
 */

public class RegisterActivity extends AppCompatActivity {


    private EditText phone;
    private EditText password;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        initView();
    }


    /**
     * initialize view by id
     */
    private void initView() {
        phone = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        iv_back = (ImageView) findViewById(R.id.iv_back);
    }

    /**
     * register click listener
     * @param view
     */
    public void register(View view) {

        Toast.makeText(this,"你点击了注册", Toast.LENGTH_SHORT).show();

        //Get the user's input
        String telphone = phone.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        //judge whether the user's input and input data are legitimate or not
        if (TextUtils.isEmpty(telphone)||TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"phone number or password can't be empty", Toast.LENGTH_SHORT).show();
        }else {
            //Compare the data entered by the user to the data in the database


        }

    }

    public void back(View view) {
        Intent intent =new Intent(RegisterActivity.this, MainActivity.class);
        intent.putExtra("id",3);
        startActivity(intent);
    }
}
