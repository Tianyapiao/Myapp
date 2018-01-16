package com.example.asus.algorithm.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.algorithm.R;
import com.example.asus.algorithm.Utils.Util;
import com.example.asus.algorithm.adapter.AdapterComment;
import com.example.asus.algorithm.bean.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.asus.algorithm.R.id.des;


/**
 * Created by Asus on 2018/1/8.
 */

public class NavigateActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,TextToSpeech.OnInitListener {

    private CoordinatorLayout right;
    private NavigationView left;
    private boolean isDrawer=false;
    private View describle;
    private TextView tv_bubble;
    private TextView tv_algorithm_name;
    private View bublle;
    private View bubble;
    private View headerLayout;
    private TextView tv_maopao;
    private EditText et_value;

    private View exercises;
    private RadioButton rbA;
    private RadioButton rbB;
    private RadioButton rbC;
    private RadioButton rbD;
    private TextView tv_topic;
    private View comment;
    private Button send;
    private FloatingActionButton fdb;
    private TextView sort_before;

    private TextView sort_after;//需要播放的文字
    private TextToSpeech textToSpeech;//TTS对象 语音播放
    private String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fdb = (FloatingActionButton) findViewById(R.id.fab);
        fdb.setOnClickListener(new View.OnClickListener() {
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
    private void hideAll(){
        if (describle!=null){
            describle.setVisibility(View.INVISIBLE);
        }
        if (bubble!=null){
            bubble.setVisibility(View.INVISIBLE);
        }
        if (exercises!=null){
            exercises.setVisibility(View.INVISIBLE);
        }
        if (comment!=null){
            comment.setVisibility(View.INVISIBLE);
        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //找到所有的布局
        describle = right.findViewById(des);
        //找到activity_sort
        bubble = right.findViewById(R.id.bubble);
        //如果选择的是相关习题
        exercises = right.findViewById(R.id.exercise);
        //如果选择的是网友评论
        comment = right.findViewById(R.id.comment);


        //隐藏所有的页面
        hideAll();
        if (id == R.id.nav_camera) {
            //如果选中的是算法描述
            Toast.makeText(this,"您选择了算法描述", Toast.LENGTH_SHORT).show();

            if (describle!=null){
                describle.setVisibility(View.VISIBLE);
                tv_algorithm_name = (TextView) describle.findViewById(R.id.tv_algorithm_name);
                tv_algorithm_name.setText("冒泡排序算法");
                //
                tv_bubble = (TextView) describle.findViewById(R.id.tv_bubble);
                tv_bubble.setText("冒泡排序的基本思想就是：从无序序列头部开始，进行两两比较，根据大小交换位置，" +
                        "直到最后将最大（小）的数据元素交换到了无序队列的队尾，从而成为有序序列的一部分；" +
                        "下一次继续这个过程，直到所有数据元素都排好序" +
                        "算法的核心在于每次通过两两比较交换位置，选出剩余无序序列里最大（小）的数据元素放到队尾。");
            }



        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this,"您选择了排序", Toast.LENGTH_SHORT).show();
            if (bubble!=null){
                //初始化页面
                initView();
                bubble.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == R.id.nav_slideshow) {
                if (exercises != null) {
                    exercises.setVisibility(View.VISIBLE);
                    rbA = (RadioButton) exercises.findViewById(R.id.cb1);
                    rbA.setText("A.直接插入排序");
                    rbB = (RadioButton) exercises.findViewById(R.id.cb2);
                    rbB.setText("B.快速排序");
                    rbC = (RadioButton) exercises.findViewById(R.id.cb3);
                    rbC.setText("C.直接选择排序");
                    rbD = (RadioButton) exercises.findViewById(R.id.cb4);
                    rbD.setText("D.堆排序");
                    tv_topic = (TextView) exercises.findViewById(R.id.topic1);
                    tv_topic.setText("1.下列排序算法中，在每一趟都能选出一个元素放到其最终位置上，并且其时间性能受数据初始特性影响的是()");

                }
            } else if (id == R.id.nav_manage) {
                fdb.setVisibility(View.INVISIBLE);
                //评论
                Toast.makeText(this,"您选择了网友评论", Toast.LENGTH_SHORT).show();
                if (comment!=null){

                    showView();
                    comment.setVisibility(View.VISIBLE);
                    send = (Button) comment.findViewById(R.id.comment_send);
                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()) {
                                case R.id.comment_content:
                                    // 弹出输入法
                                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                    // 显示评论框
                                    rl_enroll.setVisibility(View.GONE);
                                    rl_comment.setVisibility(View.VISIBLE);
                                    break;
                                case R.id.comment_send:
                                    sendComment();
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        /*transaction.commit();*/
        return true;
    }

    private EditText comment_content;
    private Button comment_send;
    private LinearLayout rl_enroll;
    private RelativeLayout rl_comment;

    private ListView comment_list;
    private AdapterComment adapterComment;
    private List<Comment> data;

    private void showView() {
        // Initialize the comment list
        comment_list = (ListView) findViewById(R.id.comment_list);
        // Initialize comment data
        data = new ArrayList<>();
        // Initialize adapter
        adapterComment = new AdapterComment(getApplicationContext(), data);
        //Setting up an adapter for the comment list
        comment_list.setAdapter(adapterComment);
        comment_content = (EditText) findViewById(R.id.comment_content);
        comment_send = (Button) findViewById(R.id.comment_send);
        rl_enroll = (LinearLayout) findViewById(R.id.rl_enroll);
        rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);
    }

    private void sendComment() {
        if(comment_content.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Comments must not be empty! ", Toast.LENGTH_SHORT).show();
        }else{
            // Generating comment data
            Comment comment = new Comment();
            comment.setName("victory"+(data.size()+1)+"：");
            comment.setContent(comment_content.getText().toString());
            adapterComment.addComment(comment);
            // Send out, clear the input box
            comment_content.setText("");
            Toast.makeText(getApplicationContext(), "Comment  successful!", Toast.LENGTH_SHORT).show();
        }

    }


    class CodeAdapter extends BaseAdapter {
        private String[] codes;
        private Context context;

        public CodeAdapter(Context context, String[] codes){
            this.context = context;
            this.codes = codes;
        }

        @Override
        public int getCount() {
            return codes.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder vh = null;

            if(convertView == null){
                convertView = getLayoutInflater().from(context).inflate(R.layout.layout_mycode, null);
                vh = new ViewHolder();

                vh.tv_code = (TextView) convertView.findViewById(R.id.tv_code);

                if(position==0){
                    vh.tv_code.setBackgroundColor(getResources().getColor(R.color.gray));
                }

                convertView.setTag(vh);

            }else{
                vh = (ViewHolder) convertView.getTag();
            }

            String content = codes[position];

            //赋值
            vh.tv_code.setText(content);
            if(position == currentRow){
                vh.tv_code.setBackgroundColor(getResources().getColor(R.color.orange));
            }else{
                vh.tv_code.setBackgroundColor(getResources().getColor(R.color.white));
            }
            return convertView;
        }
    }

    class ViewHolder {
        public TextView tv_code;
    }

    /**
     * 用来初始化TextToSpeech引擎
     * status:SUCCESS或ERROR这2个值
     * setLanguage设置语言，帮助文档里面写了有22种
     * TextToSpeech.LANG_MISSING_DATA：表示语言的数据丢失。
     * TextToSpeech.LANG_NOT_SUPPORTED:不支持
     */
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.stop(); // 不管是否正在朗读TTS都被打断
        textToSpeech.shutdown(); // 关闭，释放资源
    }

    private void initView(){

        lv_code = (ListView) this.findViewById(R.id.lv_code);
        codes = getResources().getStringArray(R.array.next_insert_codes);
        codeAdapter= new CodeAdapter(this, codes);
        lv_code.setAdapter(codeAdapter);

        lv_code.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                currentRow = postion;
                codeAdapter.notifyDataSetChanged();
                //isPlay = false;
            }
        });
        textToSpeech = new TextToSpeech(this, this); // 参数Context,TextToSpeech.OnInitListener
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_back=(Button) findViewById(R.id.btn_back);
        btn_play = (Button) findViewById(R.id.btn_play);
        final String items[] = new String[] { "男声播放", "女声播放", "图形播放" };
        //play（include male sound play ，female sound play and graphic playback）

        btn_play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(NavigateActivity.this).
                        setTitle("选择播放类型").
                        setIcon(R.drawable.voice).
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*Toast.makeText(NavigateActivity.this, items[which], Toast.LENGTH_SHORT).show();*/
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //btn_play.setText(items[which]);//按钮文字改变
                                if (sort_after!=null && item.equals("男声播放")){//如果sort_after不为空
                                    if (textToSpeech != null && !textToSpeech.isSpeaking()) {
                                        textToSpeech.setPitch(0.5f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                                        textToSpeech.speak(sort_after.getText().toString(),
                                                TextToSpeech.QUEUE_FLUSH, null);
                                    }
                                }else if(sort_after!=null && item.equals("女声播放")){//如果sort_after不为空
                                    if (textToSpeech != null && !textToSpeech.isSpeaking()) {
                                        textToSpeech.setPitch(6.5f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                                        textToSpeech.speak(sort_after.getText().toString(),
                                                TextToSpeech.QUEUE_FLUSH, null);
                                    }
                                }else{
                                    Toast.makeText(NavigateActivity.this,"你选择了图形播放，but图形播放功能还未实现", Toast.LENGTH_SHORT).show();
                                }

                                dialog.dismiss();//关闭对话框
                            }
                        })
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                item = items[which];
                            }
                        }).create();
                dialog.show();
            }
        });



        //下一步
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果不是最后一行
                if (currentRow<codes.length){
                    currentRow++;
                    codeAdapter.notifyDataSetChanged();
                }else{
                    currentRow=0;
                }

                //isPlay = false;
            }
        });
        //上一步
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentRow>0){
                    currentRow--;
                    codeAdapter.notifyDataSetChanged();
                }else{
                    currentRow=codes.length;
                }
            }
        });
        //添加数据
        btn_insert = (Button) findViewById(R.id.btn_insert);
        //获取输入框
        et_value = (EditText) this.findViewById(R.id.et_value);
        //获取排序前的textView
        sort_before = (TextView) findViewById(R.id.tv_sort_before);
        sort_after = (TextView) findViewById(R.id.tv_sort_after);
        final StringBuffer sb=new StringBuffer();
        //在排序前的后面显示数据
        btn_insert.setOnClickListener(new View.OnClickListener() {

            String dataStr= "";
            @Override
            public void onClick(View v) {
                //排序前中添加数字
                dataStr= et_value.getText().toString().trim();
                sb.append(dataStr+"\t\t");
                sort_before.setText(sb);

                String[] dataInt = sb.toString().split("\\s+");
                int[] arr=new int[dataInt.length];

                for (int i = 0; i < dataInt.length; i++) {

                    int a= Integer.parseInt(dataInt[i]);
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
                sort_after.setText(Util.convertListToStr(stringList));
            }
        });

        //清空数据
        bt_clear = (Button) findViewById(R.id.btn_clear);
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sort_after!=null)
                    sort_before.setText("");
                if (sort_after!=null)
                    sort_after.setText("");
                if (sb.toString() != "") {
                    sb.setLength(0);
                }
            }
        });
    }

    //添加数据

    private ListView lv_code;
    private String[] codes;
    private int currentRow = 0;
    private CodeAdapter  codeAdapter;
    private Button btn_next, btn_back, btn_play, btn_pause, btn_insert,bt_clear;
}


