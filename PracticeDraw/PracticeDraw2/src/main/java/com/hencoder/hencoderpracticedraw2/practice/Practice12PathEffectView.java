package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    CornerPathEffect pathEffect = new CornerPathEffect(20); // 拐角变圆角
    DiscretePathEffect discretePathEffect = new DiscretePathEffect(20, 5); //随机偏远(长度，偏移量)
    DashPathEffect dashPathEffect = new DashPathEffect(new float[]{20, 5, 10, 5}, 0); // 虚线（长度，间隔，长度，间隔）
    PathDashPathEffect pathDashPathEffect; // 用某个path替换
    SumPathEffect sumPathEffect=new SumPathEffect(discretePathEffect,dashPathEffect); // 组合两种变换，同时作用
    ComposePathEffect composePathEffect=new ComposePathEffect(dashPathEffect,discretePathEffect); // 组合两种变换，第二种在第一种变换的基础上再进行变换

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);

        Path dashPath=new Path();
        dashPath.lineTo(20,-30);
        dashPath.lineTo(40,0);
        dashPath.close();
        // 参数1：用来变换的path
        // 参数2：两个path的起点间间距，不是两者中间间距
        // 参数3：偏移量
        // 参数3：拐角模式--TRANSLATE：位移，ROTATE：旋转，MORPH：变体
        pathDashPathEffect=new PathDashPathEffect(dashPath,50,0, PathDashPathEffect.Style.MORPH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        paint.setPathEffect(discretePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
