package com.example.asus.algorithm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.algorithm.R;
import com.example.asus.algorithm.bean.Comment;

import java.util.List;

/**
 * Created by Asus on 2018/1/7.
 */

public class AdapterComment extends BaseAdapter {

    private List<Comment> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterComment(Context context, List<Comment> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Asus
     */
    public final class Zujian{

        public TextView comment_name;
        public TextView comment_content;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {

        return data.get(i);
    }

    @Override
    public long getItemId(int i ) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.item_comment, null);

            zujian.comment_name=(TextView)convertView.findViewById(R.id.comment_name);
            zujian.comment_content=(TextView)convertView.findViewById(R.id.comment_content);

            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.comment_name.setText(data.get(i).getName());
        zujian.comment_content.setText(data.get(i).getContent());
        return convertView;
    }
    /**
     * 添加一条评论,刷新列表
     * @param comment
     */
    public void addComment(Comment comment){
        data.add(comment);
        notifyDataSetChanged();
    }
    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder{
        TextView comment_name;
        TextView comment_content;
    }
}
