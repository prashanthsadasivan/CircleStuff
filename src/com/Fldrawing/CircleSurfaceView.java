package com.Fldrawing;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created with IntelliJ IDEA.
 * User: prashanthsadasivan
 * Date: 4/17/13
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class CircleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public CircleSurfaceView(Context context) {
        super(context);
        init();
    }

    public CircleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    CircleAnimThread thread;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        setWillNotDraw(false); //Allows us to use invalidate() to call onDraw()
        thread = new CircleAnimThread(getHolder(), this); //Start the thread that
        thread.setRunning(true);                     //will make calls to
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            thread.setRunning(false);                //Tells thread to stop
            thread.join();                           //Removes thread from mem.
        } catch (InterruptedException e) {

        }
    }
}
