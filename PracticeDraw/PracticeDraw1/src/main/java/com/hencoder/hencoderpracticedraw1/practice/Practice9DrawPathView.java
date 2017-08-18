package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    Paint paint=new Paint();
    Path path=new Path();

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            // addArc和arcTo是画弧形，同canvas.drawArc()
            // 前4个参数表示椭圆，第五个是起始角度，第六个是划过的角度(正值顺时针画，负值逆时针画)
            path.addArc(200, 200, 400, 400, -225, 225); // 心形的左上部分
            path.arcTo(400, 200, 600, 400, -180, 225, false); // 心形右上部分
            path.lineTo(400, 542); // 心形底部
            canvas.drawPath(path,paint);

            // 画一个对应倒着的红色心形
            paint.setColor(Color.RED);
            path.reset();
            path.addArc(600,342,800,542,225,-225); // 心形左下部
            path.arcTo(800,342,1000,542,180,-225,false); // 心形右下部
            path.lineTo(800,200); // 心形顶部
            canvas.drawPath(path,paint);
        }
    }
}
