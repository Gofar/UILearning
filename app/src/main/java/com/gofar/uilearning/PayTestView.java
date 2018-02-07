package com.gofar.uilearning;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author lcf
 * @date 2018/2/7 10:53
 * @since 1.0
 */
public class PayTestView extends View {
    public PayTestView(Context context) {
        super(context);
    }

    public PayTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PayTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBorder(canvas);
    }

    private void drawBorder(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10, paint);
        } else {
            //canvas.drawRect(10, 10, 800, 110, paint);
            RectF rectF = new RectF(10, 0, getWidth()-10, getHeight());
            canvas.drawRoundRect(rectF, 10, 10, paint);
        }
    }
}
