package com.zhj.viewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HongJay on 2018/4/3.
 */

public class TabButton extends View {
    private Paint mPaint;
    private int w, h;// 获取控件宽高
    private boolean isChecked = true;
    private String title;
    private int background_color;
    private int checked_color;
    private int checked_text_color;
    private int unchecked_text_color;

    public TabButton(Context context) {
        super(context, null);
        init();
    }

    public TabButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabButton);
        title = a.getString(R.styleable.TabButton_title);
        background_color = a.getColor(R.styleable.TabButton_background_color, Color.BLACK);
        checked_color = a.getColor(R.styleable.TabButton_checked_color, Color.BLACK);
        checked_text_color = a.getColor(R.styleable.TabButton_checked_text_color, Color.BLACK);
        unchecked_text_color = a.getColor(R.styleable.TabButton_unchecked_text_color, Color.BLACK);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
        mPaint.setAntiAlias(true);//抗锯齿
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = getMeasuredWidth();//获取view的宽度
        h = getMeasuredHeight();//获取view的高度
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (isChecked) {
                    isChecked = false;
                } else {
                    isChecked = true;
                }
                postInvalidate();
                break;

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(0x9f000000);
        mPaint.setColor(background_color);
        RectF rectF = new RectF(0, (float) (0.4 * h), w, h);
        canvas.drawRect(rectF, mPaint);

        mPaint = new Paint();
        mPaint.setColor(checked_color);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
        mPaint.setAntiAlias(true);//抗锯齿

        if (isChecked) {
            Path path5 = new Path();
            path5.moveTo((float) (0.1 * w), h);
            path5.lineTo((float) (0.15 * w), (float) (0.3 * h) + (float) (0.05 * w));
            path5.quadTo((float) (2 * h * w / (14 * h - w)), (float) (0.3 * h), (float) (0.2 * w), (float) (0.3 * h));
            path5.lineTo((float) (0.8*w), (float) (0.3 * h));
            path5.quadTo((float) ((12*h*w-w*w)/(14*h-w)), (float) (0.3 * h), (float) (0.85 * w), (float) (0.3 * h)+(float) (0.05 * w));
            path5.lineTo((float) (0.9 * w), h);
            path5.close();
            canvas.drawPath(path5, mPaint);

            Paint textPaint = new Paint();
            textPaint.setColor(checked_text_color);
            textPaint.setTextSize(h / 4);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setFakeBoldText(true); //true为粗体，false为非粗体
            //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
            textPaint.setTextAlign(Paint.Align.CENTER);


            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
            float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

            int baseLineY = (int) ((0.4 + 1) * h / 2 - top / 2 - bottom / 2);//基线中间点的y轴计算公式

            canvas.drawText(title, w / 2, baseLineY, textPaint);
        } else {
            Paint textPaint = new Paint();
            textPaint.setColor(unchecked_text_color);
            textPaint.setTextSize(h / 4);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setFakeBoldText(true); //true为粗体，false为非粗体
            //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
            textPaint.setTextAlign(Paint.Align.CENTER);


            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
            float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

            int baseLineY = (int) ((0.4 + 1) * h / 2 - top / 2 - bottom / 2);//基线中间点的y轴计算公式

            canvas.drawText(title, w / 2, baseLineY, textPaint);
        }

    }
}
