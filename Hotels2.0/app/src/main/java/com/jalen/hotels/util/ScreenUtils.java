package com.jalen.hotels.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 */

public class ScreenUtils {

    private static int screenWidth = -1;
    private static int screenHeight = -1;

    public static int dp2px(Context context, int dipValue) {
        float reSize = context.getResources()
                .getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    public static int px2dp(Context context, int pxValue) {
        float reSize = context.getResources()
                .getDisplayMetrics().density;
        return (int) ((pxValue / reSize) + 0.5);
    }

    public static float sp2px(Context context, int spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
                context.getResources()
                        .getDisplayMetrics());
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 获取屏幕的宽度
     */
    public static int getScreenWith(Context context) {
        if (screenWidth <= 0) {
            DisplayMetrics dm = context.getResources
                    ().getDisplayMetrics();
            screenWidth = dm.widthPixels;
        }

        return screenWidth;
    }

    /****
     * 获取屏幕的高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (screenHeight <= 0) {
            DisplayMetrics dm = context.getResources
                    ().getDisplayMetrics();
            screenHeight = dm.heightPixels;
        }
        return screenHeight;
    }

    /**
     * 隐藏输入法
     *
     * @param editText
     */
    public static void hideKeyboard(View editText) {
        InputMethodManager imManager = (InputMethodManager) editText
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imManager.isActive()) {
            imManager.hideSoftInputFromWindow(
                    editText.getApplicationWindowToken(), 0);
        }
    }

    public static void hideKeyboardDelay(final View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideKeyboard(view);
            }
        }, 150);
    }

    /**
     * 显示输入法
     *
     * @param editText
     */
    public static void showKeyboard(View editText) {
        InputMethodManager imManager = (InputMethodManager) editText
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

}
