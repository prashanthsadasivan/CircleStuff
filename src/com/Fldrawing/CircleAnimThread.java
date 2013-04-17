package com.Fldrawing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created with IntelliJ IDEA.
 * User: prashanthsadasivan
 * Date: 4/17/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class CircleAnimThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private CircleSurfaceView panel;
    private boolean run = false;
    private Paint paint;

    public CircleAnimThread(SurfaceHolder surfaceHolder, CircleSurfaceView csv) {
        this.surfaceHolder = surfaceHolder;
        panel = csv;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }


    public void setRunning(boolean run) { //Allow us to stop the thread
        this.run = run;
    }

    boolean add = false;


    @Override
    public void run() {
        Canvas c;

        long starttime = System.currentTimeMillis();
        float mins = 60.0f;
        while (run) {     //When setRunning(false) occurs, run is

            c = null;      //set to false and loop ends, stopping thread
            long loopstartime = System.currentTimeMillis();
            if(!add) {
                mins = 60000.0f - (loopstartime - starttime);
            } else {
                mins = loopstartime - starttime;
            }
            if(!add && mins < 0) {
                starttime = loopstartime;
                add = true;
            } else if (add && mins > 60000.0) {
                starttime = loopstartime;
                add = false;
            }

            try {
                c = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    if(c != null) {
                        c.drawColor(Color.BLACK);
                        c.drawArc(new RectF(2.0f, 2.0f, (float) panel.getMeasuredWidth() - 2.0f, (float) panel.getMeasuredHeight() - 2.0f),270.0f, (mins/60000.0f)*360.0f,false,paint);
                    }
                }
            } finally {
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }



}
