package com.gofar.uilearning;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.Toast;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/25 10:02
 */
public class NewInputView extends AppCompatEditText {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mWidth;
    private int mHeight;
    private int mConner = 15;
    private int mStrokeWidth = 2;
    private int mDividerWidth = 1;
    private int mRadius = 15;
    private int mStrokeColor = Color.GRAY;
    private int mDividerColor = Color.LTGRAY;
    private int mCount = 6;
    private int mDividerX;
    private int mStartX;
    private int mStartY;

    private int mDefaultWidth = 100;

    private int mTextLength;

    public NewInputView(Context context) {
        super(context);
        initialize();
    }

    public NewInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public NewInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        setBackgroundColor(Color.TRANSPARENT);
        setCursorVisible(false);
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mCount)});
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
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
        for (int i = 0; i < mTextLength; i = i + 1) {
            drawPsd(canvas, i);
        }
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int width;
//        int height;
//
//        if (widthMode == MeasureSpec.EXACTLY) { // match_parent,或具体数值
//            width = widthSize;
//        } else if (widthMode == MeasureSpec.AT_MOST) { // wrap_content
//            width = Math.min(mDefaultWidth, widthSize);
//        } else {
//            width = mDefaultWidth;
//        }
//        // 每个小框宽高相等
//        if (heightMode == MeasureSpec.EXACTLY) {
//            height = heightSize;
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            height = Math.min(width / mCount, heightSize);
//        } else {
//            height = width / mCount;
//        }
//        setMeasuredDimension(width, height);
//    }


    private void drawPsd(Canvas canvas, int index) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(mStartX + mDividerX * index, mStartY, mRadius, paint);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        mTextLength = text.length();
        Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
        invalidate();
    }
}
