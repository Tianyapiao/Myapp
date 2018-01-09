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
import android.widget.Toast;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "haha0", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent("com.example.asus.algorithm.open");
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "haha1", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "haha2", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(), "haha3", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getActivity(), "haha4", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity(), "haha5", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(getActivity(), "haha6", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(getActivity(), "haha7", Toast.LENGTH_SHORT).show();
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
        map1.put("title", "第一章  冒泡排序");
        list.add(map1);

        Map<String, Object> map2=new HashMap<>();
        map2.put("image", R.drawable.next);
        map2.put("title", "第二章  插入排序");
        list.add(map2);

        Map<String, Object> map3=new HashMap<>();
        map3.put("image", R.drawable.next);
        map3.put("title", "第三章  折半插入排序");
        list.add(map3);

        Map<String, Object> map4=new HashMap<>();
        map4.put("image", R.drawable.next);
        map4.put("title", "第四章  基数排序");
        list.add(map4);

        Map<String, Object> map5=new HashMap<>();
        map5.put("image", R.drawable.next);
        map5.put("title", "第五章  希尔排序");
        list.add(map5);

        Map<String, Object> map6=new HashMap<>();
        map6.put("image", R.drawable.next);
        map6.put("title", "第六章  归并排序");
        list.add(map6);

        Map<String, Object> map7=new HashMap<>();
        map7.put("image", R.drawable.next);
        map7.put("title", "第七章  直接选择排序");
        list.add(map7);

        Map<String, Object> map8=new HashMap<>();
        map8.put("image", R.drawable.next);
        map8.put("title", "第八章  堆排序");
        list.add(map8);

        return list;
    }

}
