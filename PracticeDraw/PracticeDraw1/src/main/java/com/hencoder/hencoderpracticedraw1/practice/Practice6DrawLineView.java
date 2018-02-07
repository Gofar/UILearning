package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice6DrawLineView extends View {
    Paint paint = new Paint();

    public Practice6DrawLineView(Context context) {
        super(context);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawLine() 方法画直线
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(16);
        canvas.drawLine(300, 150, 700, 350, paint);

        paint.setStrokeWidth(2);
        canvas.drawLine(20, 20, 400, 20, paint);
        canvas.drawLine(20, 20, 20, 400, paint);

        paint.setStrokeWidth(3);
        canvas.drawLine(400, 20, 800, 20, paint);
        canvas.drawLine(400, 20, 400, 800, paint);
    }
}
