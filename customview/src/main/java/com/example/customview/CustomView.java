package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.TypedArrayUtils;

public class CustomView extends View {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 200;

    private int centerX;
    private int centerY;

    private int left;
    private int right;
    private int top;
    private int bottom;

    private boolean isVertical = true;

    private Paint paint;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(typedArray.getColor(R.styleable.CustomView_rectColor, 0));
        paint.setStyle(Paint.Style.STROKE);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = MeasureSpec.getSize(widthMeasureSpec) / 2;
        centerY = MeasureSpec.getSize(heightMeasureSpec) / 2;

        left = centerX - WIDTH / 2;
        right = centerX + WIDTH / 2;
        top = centerY - HEIGHT / 2;
        bottom = centerY + HEIGHT / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(left, top, right, bottom, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            rotate();
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    private void rotate() {
        if (isVertical) {
            left = centerX - WIDTH / 2;
            right = centerX + WIDTH / 2;
            top = centerY - HEIGHT / 2;
            bottom = centerY + HEIGHT / 2;
            isVertical = false;
        } else {
            left = centerX - HEIGHT / 2;
            right = centerX + HEIGHT / 2;
            top = centerY - WIDTH / 2;
            bottom = centerY + WIDTH / 2;
            isVertical = true;
        }
    }
}
