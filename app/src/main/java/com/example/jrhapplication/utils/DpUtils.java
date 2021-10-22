package com.example.jrhapplication.utils;
import android.util.TypedValue;

import com.example.jrhapplication.APP;

/**
 * @author yangjilai
 * @function dp->px px->dp sp->px ps->sp 像素转换工具类
 */
public class DpUtils {

    private DpUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     *
     * @param dpVal
     * @return
     */
    public static int dp2px( float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, APP.instance.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int sp2px( float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, APP.instance.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public static float px2dp( float pxVal) {
        final float scale = APP.instance.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp( float pxVal) {
        return (pxVal / APP.instance.getResources().getDisplayMetrics().scaledDensity);
    }

}
