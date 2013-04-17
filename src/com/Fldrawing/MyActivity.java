package com.Fldrawing;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    boolean add = false;
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sv_main);
        tv = (TextView) findViewById(R.id.timeleft);
        tv.setText("60");
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                int dispTime = Integer.valueOf(tv.getText().toString());
                if(dispTime == 0) {
                    add = true;
                } else if(dispTime == 60) {
                    add = false;
                }

                if(add) {
                    dispTime += 1;
                } else {
                    dispTime -= 1;
                }

                tv.setText(String.valueOf(dispTime));
                h.postDelayed(this, 1000);

            }
        }, 1000);
    }
}
