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
import com.jalen.hotels.util.ImageUtils;
import com.jalen.hotels.util.StringUtil;

import java.util.List;


public class HotelAdapter extends BaseAdapter {

    private List<Hotel> list;
    private Context context;

    public HotelAdapter(List<Hotel> list, Context context) {
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
            view = View.inflate(context, R.layout.list_item_hotel, null);
            view.setTag(holder);
            holder.iv = (ImageView) view.findViewById(R.id.iv);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            holder.tv_tag = (TextView) view.findViewById(R.id.tv_tag);
            holder.tv_comments = (TextView) view.findViewById(R.id.tv_comments);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Hotel hotel = list.get(i);

        try {
            if (StringUtil.isNotBlank(hotel.pic)) {
                ImageUtils.loadImage(context, holder.iv, hotel.pic, false);
            }
            holder.tv_comments.setText("5条评论");
            holder.tv_name.setText(hotel.name);
            holder.tv_price.setText(hotel.price + "元");
            holder.tv_tag.setText(hotel.tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    class ViewHolder {
        ImageView iv;
        TextView tv_name, tv_price, tv_tag, tv_comments;
    }
}
