package com.mmdev.customcontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CircleProgress extends androidx.appcompat.widget.AppCompatImageView {

    private static final String TAG = "CircleProgress";
    private Paint paint1, paint2,paint3;
    private int percent = 0;

    public CircleProgress(@NonNull Context context) {
        super(context);
        initialize(context);
    }

    public CircleProgress(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public CircleProgress(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);

        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);

        paint3 = new Paint();
        paint3.setColor(Color.BLACK);
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.FILL_AND_STROKE);


        startPainting();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth()/2 - 60, getHeight()/2, 30,paint1);
        canvas.drawCircle(getWidth()/2  , getHeight()/2, 30,paint2);
        canvas.drawCircle(getWidth()/2 + 60 , getHeight()/2, 30,paint3);
    }

    private void startPainting(){
        Thread thread;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (percent < 100){
                        Thread.sleep(15);
                        postInvalidate();
                        percent++;
                    }
                    if (percent == 100) {
                        percent = 0;
                        startPainting();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
