package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {
    Paint paint = new Paint();
    Path path = new Path();

    // 基准点
    int X0 = 100, Y0 = 510;
    int Z = 120; // 每个柱状图占用宽度
    int L = 100; // 柱状图柱子宽度
    int P = 20; // 柱子间距20
    int V = 5; // 柱子高度最高为500,分为100份，每一份高度为5
    int size = 30; // 字size

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        paint.setColor(Color.WHITE);
        paint.setTextSize(size);
        paint.setTextAlign(Paint.Align.CENTER); // 文本居中显示

        canvas.drawLine(X0, 10, X0, Y0, paint); // 画纵轴

        // 开始画柱子
        drawPillar(canvas, X0, 1, "first");
        drawPillar(canvas, X0 + Z, 10, "two");
        drawPillar(canvas, X0 + Z * 2, 50, "three");
        drawPillar(canvas, X0 + Z * 3, 80, "four");
        drawPillar(canvas, X0 + Z * 4, 60, "five");
        drawPillar(canvas, X0 + Z * 5, 20, "six");
        drawPillar(canvas, X0 + Z * 6, 75, "seven");

        canvas.drawLine(X0 + Z * 7, Y0, X0 + Z * 7 + P * 2, Y0, paint); // 为美观给Y轴右边添加突出长P * 2的一段线
    }

    /**
     * 画柱子,包括柱子宽度和左间距，如下：
     *          *****
     *          *   *
     *          *   *
     *          *   *
     *        *******
     *
     * @param canvas 画布
     * @param x      当前x坐标，从这个坐标开始画
     * @param v      柱子的值（0-100）
     * @param text   柱子的说明（底部文字）
     */
    private void drawPillar(Canvas canvas, int x, int v, String text) {
        if (v > 0) {  // 柱子的值大于0,开始画柱子
            Paint paint1 = new Paint();
            paint1.setColor(Color.GREEN);
            path.reset();
            path.addRect(x + P, Y0 - v * V, x + P + L, Y0, Path.Direction.CW);
            canvas.drawPath(path, paint1);
        }
        canvas.drawLine(x, Y0, x + Z, Y0, paint); // 柱子Y轴线
        canvas.drawText(text, x + P + Z / 2, Y0 + paint.getFontSpacing(), paint); // 绘制底部文字
    }
}
