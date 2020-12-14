package com.jalen.hotels.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 图片加载工具类
 */
public class ImageUtils {

    /****
     * 加载图片-activity
     *
     * @param activity
     * @param iv
     * @param path
     */
    public static void loadImage(Activity activity, ImageView iv, String path) {
        loadImage(activity, iv, path, true);
    }

    /****
     * 加载图片-FragmentActivity
     *
     * @param fragmentActivity
     * @param iv
     * @param path
     */
    public static void loadImage(FragmentActivity fragmentActivity, ImageView iv, String path) {
        loadImage(fragmentActivity, iv, path, true);
    }

    /****
     * 加载图片-FragmentActivity
     *
     * @param fragmentActivity
     * @param iv
     * @param path
     */
    public static void loadImage(FragmentActivity fragmentActivity, ImageView iv, String path, int widthDp, int heithtDp) {
        if (TextUtils.isEmpty(path)
                || null == iv) {
            return;
        }
        Glide.with(fragmentActivity)
                .load(path)
                .override(widthDp, heithtDp)
                .fitCenter()
                .into(iv);
    }

    /****
     * 加载图片-AppCompatActivity
     *
     * @param appCompatActivity
     * @param iv
     * @param path
     */
    public static void loadImage(AppCompatActivity appCompatActivity, ImageView iv, String path) {
        loadImage(appCompatActivity, iv, path, true);
    }

    public static void loadImage(Context context, ImageView iv, String path) {
        loadImage(context, iv, path, true);
    }

    /****
     * 加载图片-activity
     *
     * @param fragment
     * @param iv
     * @param path
     */
    public static void loadImage(Fragment fragment, ImageView iv, String path) {
        loadImage(fragment, iv, path, true);
    }

    /****
     * 加载图片-activity
     *
     * @param fragment
     * @param iv
     * @param path
     */
    public static void loadImage(Fragment fragment, ImageView iv, String path, boolean isCenterCrop) {
        if (TextUtils.isEmpty(path)
                || null == iv) {
            return;
        }
        if (isCenterCrop) {
            Glide.with(fragment)
                    .load(path)
                    .centerCrop()
                    .into(iv);
        } else {
            Glide.with(fragment)
                    .load(path)
                    .into(iv);
        }
    }

    /****
     * 加载图片-Context
     *
     * @param context
     * @param iv
     * @param path
     */
    public static void loadImage(Context context, ImageView iv, String path, boolean isCenterCrop) {
        if (TextUtils.isEmpty(path)
                || null == iv) {
            return;
        }
        if (isCenterCrop) {
            Glide.with(context)
                    .load(path)
                    .centerCrop()
                    .into(iv);
        } else {
            Glide.with(context)
                    .load(path)
                    .into(iv);
        }
    }

    /****
     * 加载图片-Context
     *
     * @param context
     * @param iv
     * @param path
     */
    public static void loadImageOfSource(Context context, ImageView iv, String path, boolean isCenterCrop) {
        if (TextUtils.isEmpty(path)
                || null == iv) {
            return;
        }
        if (isCenterCrop) {
            Glide.with(context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(iv);
        } else {
            Glide.with(context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        }
    }

    /****
     * 删除磁盘缓存（Blocking ）
     *
     * @param context
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }


    /**
     * 根据图片 的款高比动态设置
     *
     * @param context
     * @param iv
     * @param rates
     */
    public static void setImageSize(Context context, ImageView iv, int rates) {
        try {
            int screenWidth = ScreenUtils.getScreenWith(context);
            ViewGroup.LayoutParams para = iv.getLayoutParams();
            int height = screenWidth / rates;
            para.height = height;
            para.width = (screenWidth - 24);
            iv.setLayoutParams(para);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /****
     * 加载图片-activity
     *
     * @param activity
     * @param iv
     * @param path
     */
    public static void loadImage(Activity activity, final ImageView iv, String path, RequestListener<String, Drawable> listener) {
        Glide.with(activity)
                .load(path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        iv.setImageDrawable(resource);
                        return false;
                    }
                });
    }

    /**
     * 下载图片到磁盘
     *
     * @param activity
     * @param path
     * @return
     */
    public static Observable<String> downloadOnly(final Activity activity, final String path) {
        return Observable.just(path)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String url) {
                        FutureTarget<File> target = Glide.with(activity)
                                .load(url)
                                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                        File imageFile = null;
                        try {
                            imageFile = target.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        return imageFile.getAbsolutePath();
                    }
                });
    }

    /****
     * 增加图片压缩参数
     * @param isResize
     * @param uri
     * @param width
     * @param height
     * @return
     */
    public static String addResizeParamsOnUri(boolean isResize, String uri, int width, int height) {
        if (!isResize) {
            return uri;
        }

        if (StringUtil.isBlank(uri)) {
            return uri;
        }
        StringBuffer result = new StringBuffer();
        result.append(uri);
        if (StringUtil.indexOf(uri, "?") < 0) {
            result.append("?");
        }
        String params = StringUtil.substringAfterLast(uri, "?");
        if (StringUtil.indexOf(params, "&") > -1) {
            result.append("&");
        }
        result.append("x-oss-process=");

        result.append("image/resize");
        result.append(",");

        result.append("m_fill");
        result.append(",");

        result.append("w_");
        result.append(width);
        result.append(",");

        result.append("h_");
        result.append(height);

        result.append(",");
        result.append("limit_0");

        return result.toString();
    }

    /****
     * 加载图片-Context
     *
     * @param context
     * @param iv
     * @param path
     */
    public static void loadImageWithResize(Context context, ImageView iv, String path, boolean isCenterCrop) {
        if (TextUtils.isEmpty(path)
                || null == iv) {
            return;
        }

        if (isCenterCrop) {
            Glide.with(context)
                    .load(path)
                    .centerCrop()
                    .into(iv);
        } else {
            Glide.with(context)
                    .load(path)
                    .into(iv);
        }
    }

}
