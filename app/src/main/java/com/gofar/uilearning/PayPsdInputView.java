package com.gofar.uilearning;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/24 11:35
 */
public class PayPsdInputView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mWidth;
    private int mHeight;
    private int mConner = 15;
    private int mStrokeWidth = 1;
    private int mDividerWidth = 1;
    private int mRadius = 20;
    private int mStrokeColor = Color.LTGRAY;
    private int mDividerColor = Color.LTGRAY;
    private int mCount = 6;
    private int mDividerX;
    private int mStartX;
    private int mStartY;

    private int mDefaultWidth = 100;

    private String mText;
    private StringBuilder mBuilder = new StringBuilder();

    public PayPsdInputView(Context context) {
        super(context);
    }

    public PayPsdInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PayPsdInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public PayPsdInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mDividerX = mWidth / mCount;
        mHeight = Math.min(mDividerX, getHeight());
        mStartX = mDividerX / 2;
        mStartY = mHeight / 2;

        // 绘制边框
        paint.setColor(mStrokeColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mStrokeWidth);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(0, 0, mWidth, mHeight, mConner, mConner, paint);
        } else {
            canvas.drawRect(0, 0, mWidth, mHeight, paint);
        }

        // 画分隔线
        paint.setColor(mDividerColor);
        paint.setStrokeWidth(mDividerWidth);
        for (int i = 0; i < mCount - 1; i = i + 1) {
            canvas.drawLine(mDividerX * (i + 1), 0, mDividerX * (i + 1), mHeight, paint);
        }

        // 画密码
        for (int i = 0; i < mBuilder.length(); i = i + 1) {
            drawPsd(canvas, i);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) { // match_parent,或具体数值
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) { // wrap_content
            width = Math.min(mDefaultWidth, widthSize);
        } else {
            width = mDefaultWidth;
        }
        // 每个小框宽高相等
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(width / mCount, heightSize);
        } else {
            height = width / mCount;
        }
        setMeasuredDimension(width, height);
    }

    private void drawPsd(Canvas canvas, int index) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(mStartX + mDividerX * index, mStartY, mRadius, paint);
    }

    public void add(char c) {
        if (mBuilder.length() == mCount) {
            return;
        }
        mBuilder.append(c);
        invalidate();
    }

    public void delete() {
        if (mBuilder.length() == 0) {
            return;
        }
        mBuilder.deleteCharAt(mBuilder.length());
        invalidate();
    }

    public String getPassword() {
        return mBuilder.toString();
    }
}
