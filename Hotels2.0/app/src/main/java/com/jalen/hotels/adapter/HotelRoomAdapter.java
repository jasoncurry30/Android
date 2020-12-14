package com.jalen.hotels.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jalen.hotels.R;
import com.jalen.hotels.bean.HotelRoom;
import com.jalen.hotels.util.ImageUtils;
import com.jalen.hotels.util.StringUtil;

import java.util.List;

/**
 */

public class HotelRoomAdapter extends BaseAdapter {

    private List<HotelRoom> list;
    private Context context;

    public HotelRoomAdapter(List<HotelRoom> list, Context context) {
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
            view = View.inflate(context, R.layout.list_item_hotel_type, null);
            view.setTag(holder);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_tag = (TextView) view.findViewById(R.id.tv_tag);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            holder.iv = (ImageView) view.findViewById(R.id.iv);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        HotelRoom vo = list.get(i);
        try {
            if (StringUtil.isNotBlank(vo.typeImg)) {
                ImageUtils.loadImage(context, holder.iv, vo.typeImg, false);
            }
            holder.tv_name.setText(vo.name);
            holder.tv_tag.setText(vo.tag);
            holder.tv_price.setText(vo.price + "元");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    class ViewHolder {
        TextView tv_name, tv_tag, tv_price;
        ImageView iv;
    }
}
