package com.jalen.hotels.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jalen.hotels.R;
import com.jalen.hotels.bean.Comment;
import com.jalen.hotels.bean.Hotel;
import com.jalen.hotels.bean.SearchBean;
import com.jalen.hotels.util.ImageUtils;
import com.jalen.hotels.util.StringUtil;

import java.util.List;

/**
 */

public class SearchAdapter extends BaseAdapter {

    private List<SearchBean> list;
    private Context context;

    public SearchAdapter(List<SearchBean> list, Context context) {
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
            view = View.inflate(context, R.layout.list_item_search, null);
            view.setTag(holder);
            holder.tv = (TextView) view.findViewById(R.id.tv);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SearchBean hotel = list.get(i);
        try {
            holder.tv.setText(hotel.name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    class ViewHolder {
        TextView tv;
    }
}
