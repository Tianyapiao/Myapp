package com.example.asus.algorithm.fragment;


import android.Manifest;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.algorithm.R;

import static android.app.Activity.RESULT_OK;
import static com.example.asus.algorithm.R.id.re_sex;

/**
 * Created by Asus on 2018/1/7.
 */

public class MyInfoFragment extends Fragment {
    private String sexid = "";
    private View re_name;
    private TextView tv_name;
    private View sex;
    private TextView tv_sex;
    private View mAvatar;
    private ImageView iv_heagImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_myinfo, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        re_name = view.findViewById(R.id.re_name);
        tv_name = (TextView) view.findViewById(R.id.tv_name);

        //接受广播
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager
                .getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("showPro");
        BroadcastReceiver br = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String username = intent.getStringExtra("username");
                tv_name.setText(username);
            }

        };
        localBroadcastManager.registerReceiver(br, intentFilter);

        re_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick_name = tv_name.getText().toString().trim();
                Intent intent=new Intent("com.example.asus.algorithm.save");
                intent.putExtra("nick_name",nick_name);
                startActivity(intent);
            }

        });

        sex = view.findViewById(re_sex);
        tv_sex = (TextView) view.findViewById(R.id.tv_sex);
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请选择您的性别：");
                final String[] items = {"男", "女"};
                //设置对话框的图标
                builder.setIcon(R.drawable.header);
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sexid = items[which];
                        //Toast.makeText(getActivity(), "您的性别为：" + items[which], Toast.LENGTH_SHORT).show();
                    }
                });

                //添加一个确定按钮
                builder.setPositiveButton(" 确 定 ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        tv_sex.setText(sexid);
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        //找头像控件
        mAvatar = view.findViewById(R.id.re_avatar);
        iv_heagImg = (ImageView)view.findViewById(R.id.iv_avatar);

        //头像点击事件
        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用相册
               /* Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);*/
                checkPerm();
            }
        });




    }


  /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       ActivityCompat.requestPermissions(getActivity(),
               new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
               resultCode);

        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = this.getActivity().getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        iv_heagImg.setImageBitmap(bm);
    }


    //调用系统相册-选择图片
    private static final int IMAGE = 1;*/

    private void checkPerm() {
        /**1.在AndroidManifest文件中添加需要的权限。
         *
         * 2.检查权限
         *这里涉及到一个API，ContextCompat.checkSelfPermission，
         * 主要用于检测某个权限是否已经被授予，方法返回值为PackageManager.PERMISSION_DENIED
         * 或者PackageManager.PERMISSION_GRANTED。当返回DENIED就需要进行申请授权了。
         * */
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){ //权限没有被授予

            /**3.申请授权
             * @param
             *  @param activity The target activity.（Activity|Fragment、）
             * @param permissions The requested permissions.（权限字符串数组）
             * @param requestCode Application specific request code to match with a result（int型申请码）
             *    reported to {@link OnRequestPermissionsResultCallback#onRequestPermissionsResult(
             *    int, String[], int[])}.
             * */
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);


        }else{//权限被授予
            choosePhoto();

            //直接操作
        }

    }

    void choosePhoto(){
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);

    }

    /***
     *
     * 4.处理权限申请回调
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                //permission has been granted
                choosePhoto();

            } else
            {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        /**
                         * 该uri是上一个Activity返回的
                         */
                        Uri uri = data.getData();
                        Bitmap bit = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                        iv_heagImg.setImageBitmap(bit);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("tag", e.getMessage());
                        Toast.makeText(getActivity(),"程序崩溃",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }

    public static final int REQUEST_CODE=200;
    public static final int REQUEST_CODE_PICK_IMAGE=300;
}
