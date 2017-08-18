package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {
    Paint paint = new Paint();

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setColor(Color.RED);
            canvas.drawArc(200, 0, 600, 400, 180, 135, true, paint);

            paint.setColor(Color.parseColor("#F6BA40"));
            canvas.drawArc(200, 0, 600, 400, -45, 45, true, paint);

            paint.setColor(Color.parseColor("#884898"));
            canvas.drawArc(200, 0, 600, 400, 0, 15, true, paint);

            paint.setColor(Color.GRAY);
            canvas.drawArc(200, 0, 600, 400, 15, 15, true, paint);

            paint.setColor(Color.MAGENTA);
            canvas.drawArc(200, 0, 600, 400, 30, 30, true, paint);

            paint.setColor(Color.YELLOW);
            canvas.drawArc(200, 0, 600, 400, 60, 45, true, paint);

            paint.setColor(Color.BLUE);
            canvas.drawArc(200, 0, 600, 400, 105, 75, true, paint);
        }
    }
}
