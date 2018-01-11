package com.example.asus.algorithm.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.algorithm.R;
import com.example.asus.algorithm.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 2018/1/7.
 */

public class PaixuFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.paixufragment, container, false);
        ListView listView=(ListView)view.findViewById(R.id.list_view);
        final List<Map<String, Object>> list=getData();
        listView.setAdapter(new ListViewAdapter(getActivity(), list));

        //获取list集合中存储的标题
        final List<Map<String, Object>> list_algorithm=getData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent("com.example.asus.algorithm.open");
                Map<String, Object> map;
                String title;
                switch (position) {
                    case 0:
                        map = list_algorithm.get(0);
                        title = map.get("title").toString();
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                    case 1:
                        map = list_algorithm.get(1);
                        title = map.get("title").toString();
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                    case 2:
                        map = list_algorithm.get(2);
                        title = map.get("title").toString();
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                    case 3:
                        map = list_algorithm.get(3);
                        title = map.get("title").toString();
                        intent.putExtra("title",title);
                        startActivity(intent);
                        
                        break;
                    case 4:
                        map = list_algorithm.get(4);
                        title = map.get("title").toString();
                        intent.putExtra("title",title);
                        startActivity(intent);
                        
                        break;
                    case 5:
                        map = list_algorithm.get(5);
                        title = map.get("title").toString();
                        intent.putExtra("title",title);
                        startActivity(intent);
                        
                        break;
                    case 6:
                        map = list_algorithm.get(6);
                        title = map.get("title").toString();
                        intent.putExtra("title",title);
                        startActivity(intent);
                        
                        break;
                    case 7:
                        map = list_algorithm.get(7);
                        title = map.get("title").toString();
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                }
            }
        });
        return view;

    }


    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<>();
        Map<String, Object> map1=new HashMap<>();
        map1.put("image", R.drawable.next);
        map1.put("title", "第一章 冒泡排序算法");
        list.add(map1);

        Map<String, Object> map2=new HashMap<>();
        map2.put("image", R.drawable.next);
        map2.put("title", "第二章 插入排序算法");
        list.add(map2);

        Map<String, Object> map3=new HashMap<>();
        map3.put("image", R.drawable.next);
        map3.put("title", "第三章 折半插入排序算法");
        list.add(map3);

        Map<String, Object> map4=new HashMap<>();
        map4.put("image", R.drawable.next);
        map4.put("title", "第四章 基数排序算法");
        list.add(map4);

        Map<String, Object> map5=new HashMap<>();
        map5.put("image", R.drawable.next);
        map5.put("title", "第五章 希尔排序算法");
        list.add(map5);

        Map<String, Object> map6=new HashMap<>();
        map6.put("image", R.drawable.next);
        map6.put("title", "第六章 归并排序算法");
        list.add(map6);

        Map<String, Object> map7=new HashMap<>();
        map7.put("image", R.drawable.next);
        map7.put("title", "第七章 直接选择排序算法");
        list.add(map7);

        Map<String, Object> map8=new HashMap<>();
        map8.put("image", R.drawable.next);
        map8.put("title", "第八章 堆排序算法");
        list.add(map8);

        return list;
    }

}
