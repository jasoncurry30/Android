package com.jalen.hotels.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jalen.hotels.R;
import com.jalen.hotels.bean.BannerBean;
import com.jalen.hotels.util.ImageUtils;
import com.jalen.hotels.util.OnBannerItemClickListener;
import com.jalen.hotels.util.StringUtil;

import java.util.List;

/**
 */

public class BannerAdapter extends PagerAdapter {

    private List<BannerBean> datas;
    private int size = 0;
    private Context mContext;
    private OnBannerItemClickListener onBannerItemClickListener;

    public BannerAdapter(Context mContext, List<BannerBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
        this.size = datas.size();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = (ImageView) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_draweeview, null);
        if (null != datas || position < datas.size()) {
            BannerBean entity = datas.get(position);
            if (null != entity && StringUtil.isNotBlank(entity.imageUrl)) {
                ImageUtils.loadImage(mContext, imageView, entity.imageUrl, false);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onBannerItemClickListener) {
                        onBannerItemClickListener.onBannerItemClick(position);
                    }
                }
            });
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView view = (ImageView) object;
        container.removeView(view);
    }

    public void setOnBannerItemClickListener(OnBannerItemClickListener listener) {
        this.onBannerItemClickListener = listener;
    }
}
