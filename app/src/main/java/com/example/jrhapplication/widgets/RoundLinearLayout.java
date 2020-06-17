package com.example.jrhapplication.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jrhapplication.R;

/**
 * **************************************
 * 项目名称:jrhApplication
 *
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/6/16     7:37 PM
 * 用途:
 * **************************************
 */
public class RoundLinearLayout extends LinearLayout {
    private Path mPath;
    private Paint mPaint;
    private RectF mRectF;
    private float mRadius;
    private boolean isClipBackground;

    public RoundLinearLayout(@NonNull Context context) {
        this(context, null);
    }
    public RoundLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public RoundLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundLinearLayout);
        mRadius = ta.getDimension(R.styleable.RoundLinearLayout_rlRadius, 0);
        isClipBackground = ta.getBoolean(R.styleable.RoundLinearLayout_rlClipBackground, true);
        ta.recycle();

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectF = new RectF();

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        setOrientation(HORIZONTAL);
        setGravity(Gravity.RIGHT);
    }
    public void setRadius(float radius) {
        mRadius = radius;
        postInvalidate();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(0, 0, w, h);
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 28) {
            draw28(canvas);
        } else {
            draw27(canvas);
        }
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 28) {
            dispatchDraw28(canvas);
        } else {
            dispatchDraw27(canvas);
        }
    }
    private void draw27(Canvas canvas) {
        if (isClipBackground) {
            canvas.saveLayer(mRectF, null, Canvas.ALL_SAVE_FLAG);
            super.draw(canvas);
            canvas.drawPath(genPath(), mPaint);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }
    private void draw28(Canvas canvas) {
        if (isClipBackground) {
            canvas.save();
            canvas.clipPath(genPath());
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }
    private void dispatchDraw27(Canvas canvas) {
        canvas.saveLayer(mRectF, null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        canvas.drawPath(genPath(), mPaint);
        canvas.restore();
    }
    private void dispatchDraw28(Canvas canvas) {
        canvas.save();
        canvas.clipPath(genPath());
        super.dispatchDraw(canvas);
        canvas.restore();
    }
    private Path genPath() {
        mPath.reset();
        mPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CW);
        return mPath;
    }


}
