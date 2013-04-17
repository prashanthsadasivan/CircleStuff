package com.Fldrawing;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: prashanthsadasivan
 * Date: 4/16/13
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class CircleBackground extends TextView{


    Paint paint;
    Path path;

    public CircleBackground(Context context) {
        super(context);
        init();
    }

    public CircleBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleBackground(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);

    }

    float mins = 60;
    boolean add = false;

    public void changeit() {
        if(mins - 1 < 0) {
            add = true;
        } else if(mins + 1 > 60){
            add = false;
        }
        if(add) {
            mins += 1;
        } else {
            mins -= 1;
        }
        if(mins < 15) {
            paint.setColor(Color.RED);
        } else if(mins < 30) {
            paint.setColor(Color.YELLOW);
        } else {
            paint.setColor(Color.BLUE);
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);

        canvas.drawArc(new RectF(2.0f, 2.0f, (float)getMeasuredWidth() - 2.0f, (float)getMeasuredHeight() - 2.0f),270.0f, (mins/60.0f)*360.0f,false,paint);

    }
}
