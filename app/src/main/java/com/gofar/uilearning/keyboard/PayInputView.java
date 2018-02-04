package com.gofar.uilearning.keyboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gofar.uilearning.R;

/**
 * @author lcf
 * @date 2018/2/4 21:00
 * @since 1.0
 */
public class PayInputView extends View {
    /**
     * 密码模式，用黑点表示文字
     */
    public static final int MODE_PASSWORD = 0;
    /**
     * 文本模式，直接展示文字
     */
    public static final int MODE_TEXT = 1;

    private static final int DEFAULT_BORDER_WIDTH = 2;
    private static final int DEFAULT_BORDER_COLOR = Color.GRAY;
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFAULT_TEXT_SIZE = 15;
    private static final int DEFAULT_COUNT = 6;
    private static final int DEFAULT_MODE = MODE_PASSWORD;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 密码黑点的半径
     */
    private int mPointRadius = 20;
    /**
     * 边框宽度
     */
    private int mBorderWidth;
    /**
     * 边框颜色
     */
    private ColorStateList mBorderColor;
    /**
     * 圆角大小
     */
    private int mCornerRadius;
    /**
     * 密码文字颜色
     */
    private ColorStateList mTextColor;
    /**
     * 密码文字大小
     */
    private int mTextSize;
    /**
     * 密码个数
     */
    private int mCount;
    /**
     * 总宽度
     */
    private int mWidth;
    /**
     * 总高度
     */
    private int mHeight;
    /**
     * X轴分隔间距（每个输入框的宽度）
     */
    private int mDividerX;
    /**
     * 密码文字绘制起始点X
     */
    private int mStartX;
    /**
     * 密码文字绘制起始点y
     */
    private int mStartY;
    /**
     * 文字或黑点的模式
     */
    private int mMode;

    /**
     * 密码字符串
     */
    private String mPassword = "";

    public PayInputView(Context context) {
        super(context);
    }

    public PayInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PayInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public PayInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void resolveAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PayInputView, defStyleAttr, 0);
        mBorderWidth = a.getDimensionPixelSize(R.styleable.PayInputView_border_width, DEFAULT_BORDER_WIDTH);
        mBorderColor = a.getColorStateList(R.styleable.PayInputView_border_color);
        if (mBorderColor == null) {
            mBorderColor = ColorStateList.valueOf(DEFAULT_BORDER_COLOR);
        }
        mCornerRadius = a.getDimensionPixelOffset(R.styleable.PayInputView_corner_radius, -1);
        mTextColor = a.getColorStateList(R.styleable.PayInputView_text_color);
        if (mTextColor == null) {
            mTextColor = ColorStateList.valueOf(DEFAULT_TEXT_COLOR);
        }
        mTextSize = a.getDimensionPixelSize(R.styleable.PayInputView_text_size, DEFAULT_TEXT_SIZE);
        mCount = a.getInt(R.styleable.PayInputView_count, DEFAULT_COUNT);
        mMode = a.getInt(R.styleable.PayInputView_text_mode, DEFAULT_MODE);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();
        mDividerX = mWidth / mCount;
        mStartX = mDividerX / 2;
        mStartY = mHeight / 2;

        drawBorder(canvas);
        drawDivider(canvas);
        drawText(canvas);
    }

    /**
     * 绘制边框
     */
    private void drawBorder(Canvas canvas) {
        mPaint.setColor(mBorderColor.getDefaultColor());
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        if (mCornerRadius > 0) {
            // 画圆角矩形
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRoundRect(0, 0, mWidth, mHeight, mCornerRadius, mCornerRadius, mPaint);
            } else {
                canvas.drawRoundRect(new RectF(0, 0, mWidth, mHeight), mCornerRadius, mCornerRadius, mPaint);
            }
        } else {
            // 画矩形
            canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
        }
    }

    /**
     * 绘制分割线
     */
    private void drawDivider(Canvas canvas) {
        mPaint.setColor(mBorderColor.getDefaultColor());
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < mCount - 1; i++) {
            canvas.drawLine(mDividerX * (i + 1), 0, mDividerX * (i + 1), mHeight, mPaint);
        }
    }

    /**
     * 绘制文本（密码用点表示）
     */
    private void drawText(Canvas canvas) {
        int length = mPassword.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                mPaint.setColor(mTextColor.getDefaultColor());
                mPaint.setStyle(Paint.Style.FILL);
                if (mMode == MODE_PASSWORD) {
                    canvas.drawCircle(mStartX + mDividerX * i, mStartY, mPointRadius, mPaint);
                } else {
                    mPaint.setTextSize(getResources().getDisplayMetrics().scaledDensity * mTextSize);
                    // 测量文字宽高
                    Rect rect = new Rect();
                    String text = String.valueOf(mPassword.charAt(i));
                    mPaint.getTextBounds(text, 0, text.length(), rect);
                    // 绘制文字
                    canvas.drawText(text, mStartX - rect.width() / 2, mStartY + rect.height() / 2, mPaint);
                }
            }
        }
    }
}
