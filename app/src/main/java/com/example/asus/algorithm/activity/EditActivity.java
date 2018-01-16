package com.example.asus.algorithm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.algorithm.MainActivity;
import com.example.asus.algorithm.R;
import com.example.asus.algorithm.db.UserDao;
import com.example.asus.algorithm.db.entity.User;

/**
 * Created by Asus on 2018/1/8.
 * 登录页面的处理
 */

public class EditActivity extends AppCompatActivity {


    private Button btn_login;
    private Button btn_register;
    private EditText et_phone;
    private EditText et_password;
    private User user=new User();
    private UserDao userDao;
    private EditText et_username;
    private Button bt_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nickname);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        initView();

        Intent intent = getIntent();
        String nick_name = intent.getStringExtra("nick_name");
        et_username.setText(nick_name);
        et_username.setSelection(nick_name.length());

    }

    private void initView() {
        //获取用户输入控件
        et_username = (EditText) findViewById(R.id.et_username);
        bt_save = (Button) findViewById(R.id.bt_save);

    }

    public void save(View view) {
        //使用广播通信
        Intent intent = new Intent("showPro");
        String username =  et_username.getText().toString().trim();
        intent.putExtra("username", username);
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intent);
        finish();

      /*  */
       /*Intent data=new Intent(EditActivity.this, MainActivity.class);
        data.putExtra("id",3);
        startActivity(data);*/

       //像fragment中传递数据
        /*MyInfoFragment infoFragment=new MyInfoFragment();
        Bundle bundle = new Bundle();
        String username =  et_username.getText().toString().trim();
        bundle.putString("username", username);
        infoFragment.setArguments(bundle);*/
       /* Intent data=new Intent(EditActivity.this, MainActivity.class);
        String username =  et_username.getText().toString().trim();
        data.putExtra("username",username);
        data.putExtra("id",3);
        startActivity(data);*/

       /* FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, infoFragment);
        transaction.show(infoFragment);
        transaction.commit();*/

    }

    public void back2(View view) {
        Intent intent =new Intent(EditActivity.this, MainActivity.class);
        intent.putExtra("id",3);
        startActivity(intent);
    }
}
