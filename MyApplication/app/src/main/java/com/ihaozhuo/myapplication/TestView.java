package com.ihaozhuo.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author : zhenh.
 * Created by Orz on 2017/12/27 16:55.
 */

public class TestView extends View {
    private Paint mPaint = new Paint();

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);

//        mPaint.setStrokeWidth(100);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(100, 100, mPaint);
//
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawPoint(250, 100, mPaint);
//
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(400, 100, mPaint);
//
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setColor(Color.GREEN);
//        //每两个确定一个坐标点
//        float[] points = {550, 100, 700, 100, 850, 100, 850, 250, 700, 250, 550, 250};
////   canvas.drawPoints(points, mPaint);
//        //offset代表跳过前两个
//        //count代表除了跳过的，剩下的再执行8个
//        //所以最后绘制出来的就是中间的｛700, 100, 850, 100, 850, 250, 700, 250｝
//        canvas.drawPoints(points, 2, 8, mPaint);

        mPaint.setColor(Color.GREEN);
        float[] points = {300, 50, 450, 200, 600, 200, 500, 300};
        //用canvas.drawLines(points, mPaint)会画出两条线，分别为从300, 50到450, 200,和从600, 200到500, 300
//   canvas.drawLines(points, mPaint);
        canvas.drawLines(points, 2, 4, mPaint);


        mPaint.setAntiAlias(true);
        Path path = new Path();
        path.addArc(new RectF(600, 200, 800, 400), -225, 225);
        path.arcTo(new RectF(800, 200, 1000, 400), -180, 225, false);
        path.lineTo(800, 542);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, mPaint);
        mPaint.reset();

    }
}


