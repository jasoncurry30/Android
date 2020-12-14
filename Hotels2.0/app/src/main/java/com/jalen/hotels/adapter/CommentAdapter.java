package com.jalen.hotels.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jalen.hotels.R;
import com.jalen.hotels.bean.Comment;
import com.jalen.hotels.bean.SearchBean;

import java.util.List;

/**
 */

public class CommentAdapter extends BaseAdapter {

    private List<Comment> list;
    private Context context;

    public CommentAdapter(List<Comment> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.list_item_hotel_comment, null);
            view.setTag(holder);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            holder.iv_head = (ImageView) view.findViewById(R.id.iv_head);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Comment vo = list.get(i);
        try {
            holder.tv_time.setText(vo.time);
            holder.tv_content.setText(vo.content);
            holder.tv_name.setText(vo.user);
            /**
             * TODO 设置头像
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    class ViewHolder {
        ImageView iv_head;
        TextView tv_time, tv_name, tv_content;
    }
}
