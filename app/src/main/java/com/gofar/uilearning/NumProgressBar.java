package com.gofar.uilearning;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Author: lcf
 * Description: 带数字的ProgressBar(数字在上面显示)
 * Since: 1.0
 * Date: 2017/9/14 16:08
 */
public class NumProgressBar extends ProgressBar {

    private int mOldPaddingTop;
    private int mOldPaddingBottom;
    private int mOldPaddingLeft;
    private int mOldPaddingRight;

    private Bitmap mBitmap;
    private float mImgWidth;
    private float mImgHeight;

    private Paint mPaint;
    private String mText;  // 展示的文字
    private float mTextWidth; // 文字宽度
    private int mTextSize = 14;  // 默认TextSize 14sp
    private int mTextColor = Color.WHITE; // 默认文字颜色白色
    private Resources mRes;

    private int mTextPaddingLeft;
    private int mTextPaddingTop;
    private int mImgPaddingLeft;
    private int mImgPaddingTop;

    private boolean isSetPadding;

    public NumProgressBar(Context context) {
        super(context);
        init();
    }

    public NumProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    public void setPadding(@Px int left, @Px int top, @Px int right, @Px int bottom) {
        if (isSetPadding) {
            super.setPadding(left, top, right, bottom);
        }
    }

    private void init() {
        mRes = getResources();
        initBitmap();
        initPaint();
        setPadding();
    }

    /**
     * 数字的背景图片
     */
    private void initBitmap() {
        mBitmap = BitmapFactory.decodeResource(mRes, R.drawable.progress_top_bg);
        if (mBitmap != null) {
            mImgWidth = mBitmap.getWidth();
            mImgHeight = mBitmap.getHeight();
        } else {
            mImgWidth = 0;
            mImgHeight = 0;
        }
    }

    /**
     * 初始化数字画笔
     */
    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize * mRes.getDisplayMetrics().scaledDensity); // 将dp转为px
        mPaint.setColor(mTextColor);
    }

    /**
     * 设置Padding
     */
    private void setPadding() {
        int top = getBitmapHeight() + mOldPaddingTop;
        int left = getBitmapWidth() / 2 + mOldPaddingLeft;
        int right = getBitmapWidth() / 2 + mOldPaddingRight;
        int bottom = mOldPaddingBottom;
        isSetPadding = true;
        setPadding(left, top, right, bottom);
        isSetPadding = false;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mText = getProgress() * 100 / getMax() + "%";
        mTextWidth = mPaint.measureText(mText);
        Rect bounds = getProgressDrawable().getBounds();
        float imgX = bounds.width() * getProgress() / getMax() + mImgPaddingLeft + mOldPaddingLeft;
        float imgY = mImgPaddingTop + mOldPaddingTop;
        float textX = bounds.width() * getProgress() / getMax() + mImgWidth / 2 - mTextWidth / 2 + mTextPaddingLeft + mOldPaddingLeft;
        //float textY = imgY + mTextPaddingTop + mImgHeight / 2 + getTextHeight() / 4;
        float textY = imgY + mTextPaddingTop + mImgHeight / 2;

        canvas.drawBitmap(mBitmap, imgX, imgY, mPaint);
        canvas.drawText(mText, textX, textY, mPaint);
    }

    /**
     * 设置真正的Padding
     *
     * @param left   左
     * @param top    上
     * @param right  右
     * @param bottom 下
     */
    private void setMyPadding(int left, int top, int right, int bottom) {
        mOldPaddingLeft = left;
        mOldPaddingTop = top;
        mOldPaddingRight = right;
        mOldPaddingBottom = bottom;
        isSetPadding = true;
        setPadding(left + getBitmapWidth() / 2, top + getBitmapHeight(), right + getBitmapWidth() / 2, bottom);
        isSetPadding = false;
    }

    private int getBitmapWidth() {
        return (int) Math.ceil(mImgWidth);
    }

    private int getBitmapHeight() {
        return (int) Math.ceil(mImgHeight);
    }

    public void setTextSize(int size) {
        this.mTextSize = size;
        mPaint.setTextSize(size * mRes.getDisplayMetrics().density);
    }

    public void setTextColor(int color) {
        this.mTextColor = color;
        mPaint.setColor(color);
    }

    public void setTextPadding(int left, int top) {
        this.mTextPaddingLeft = left;
        this.mTextPaddingTop = top;
    }

    public void setImgPadding(int left, int top) {
        this.mImgPaddingLeft = left;
        this.mImgPaddingTop = top;
    }

    private float getTextHeight() {
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        return (float) Math.ceil(metrics.descent - metrics.top) + 2;
    }

    public int getTextPaddingLeft() {
        return mTextPaddingLeft;
    }

    public void setTextPaddingLeft(int textPaddingLeft) {
        this.mTextPaddingLeft = mTextPaddingLeft;
    }

    public int getTextPaddingTop() {
        return mTextPaddingTop;
    }

    public void setTextPaddingTop(int textPaddingTop) {
        this.mTextPaddingTop = mTextPaddingTop;
    }

    public int getImgPaddingLeft() {
        return mImgPaddingLeft;
    }

    public void setImgPaddingLeft(int imgPaddingLeft) {
        this.mImgPaddingLeft = mImgPaddingLeft;
    }

    public int getImgPaddingTop() {
        return mImgPaddingTop;
    }

    public void setImgPaddingTop(int imgPaddingTop) {
        this.mImgPaddingTop = mImgPaddingTop;
    }
}
