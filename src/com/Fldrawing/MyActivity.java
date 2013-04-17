package com.Fldrawing;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    CircleBackground bg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sv_main);
    }
}
