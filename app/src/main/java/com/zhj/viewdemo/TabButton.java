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
import android.view.View;

/**
 * Created by HongJay on 2018/4/3.
 */

public class TabButton extends View {
    private Paint mPaint;
    private int w, h;// 获取控件宽高
    private boolean isChecked = false;
    private String title;
    private int background_color;
    private int checked_color;
    private int checked_text_color;
    private int unchecked_text_color;
    private float mBackGroundHigh;
    private float mCheckedHigh;
    private float mBottomLeftMargin;
    private RectF mBackGroundRectF;
    private Paint mTextPaint;
    private Path mPath;

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
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
        mPaint.setAntiAlias(true);//抗锯齿
        mTextPaint = new Paint();
        mTextPaint.setColor(checked_text_color);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setFakeBoldText(true); //true为粗体，false为非粗体
        //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mBackGroundRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = getMeasuredWidth();//获取view的宽度
        h = getMeasuredHeight();//获取view的高度
        mBackGroundHigh = 0.4f * h;
        mCheckedHigh = 0.3f * h;
        mBottomLeftMargin = 0.1f * w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mBackGroundRectF.set(0, mBackGroundHigh, w, h);
        mPaint.setColor(background_color);
        canvas.drawRect(mBackGroundRectF, mPaint);
        mTextPaint.setTextSize(h / 4);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) ((mBackGroundHigh / h + 1) * h / 2 - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        if (isChecked) {
            mPaint.setColor(checked_color);
            mTextPaint.setColor(checked_text_color);
            mPath.moveTo(mBottomLeftMargin, h + 1);
            mPath.lineTo((float) (0.15 * w), mCheckedHigh + (float) (0.05 * w));
            mPath.quadTo((float) (2 * h * w / (14 * h - w)), mCheckedHigh, (float) (0.2 * w), mCheckedHigh);
            mPath.lineTo((float) (0.8 * w), mCheckedHigh);
            mPath.quadTo((float) ((12 * h * w - w * w) / (14 * h - w)), mCheckedHigh, (float) (0.85 * w), mCheckedHigh + (float) (0.05 * w));
            mPath.lineTo(w - mBottomLeftMargin, h + 1);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        } else {
            mTextPaint.setColor(unchecked_text_color);
        }
        canvas.drawText(title, w / 2, baseLineY, mTextPaint);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        invalidate();
    }
}
