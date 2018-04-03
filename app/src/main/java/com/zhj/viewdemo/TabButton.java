package com.zhj.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HongJay on 2018/4/3.
 */

public class TabButton extends View {
    private Paint mPaint;
    private int w, h;// 获取控件宽高

    public TabButton(Context context) {
        super(context,null);
        init();
    }

    public TabButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public TabButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = getMeasuredWidth();//获取view的宽度
        h = getMeasuredHeight();//获取view的高度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(0x88000000);//A:ff,R:8b,G:c5,B:ba
        RectF rectF = new RectF(10,10,300,150);
        //这里的圆弧是椭圆的圆弧，所以要设置椭圆的两个半径
        canvas.drawRoundRect(rectF,10,10,mPaint);
    }
}
