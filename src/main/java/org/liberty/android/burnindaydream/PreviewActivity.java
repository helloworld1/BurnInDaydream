package org.liberty.android.burnindaydream;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by liberty on 1/10/15.
 */
public class PreviewActivity extends Activity {

    private FrameLayout rootLayout;

    private int currentColor = 0;

    private Handler handler = new Handler();

    private boolean enabled = true;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dream);
        rootLayout = (FrameLayout) findViewById(R.id.root);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        rootLayout.setBackgroundColor(Color.GRAY);
        switchColors();

        rootLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                enabled = !enabled;
            }
        });

    }

    public void switchColors() {
        handler.postDelayed(new Runnable() {
            public void run() {
                if (enabled) {
                    currentColor++;
                }
                if (currentColor % 4 == 0) {
                    rootLayout.setBackgroundColor(Color.GRAY);
                }
                if (currentColor % 4 == 1) {
                    rootLayout.setBackgroundColor(Color.RED);
                }
                if (currentColor % 4 == 2) {
                    rootLayout.setBackgroundColor(Color.GREEN);
                }
                if (currentColor % 4 == 3) {
                    rootLayout.setBackgroundColor(Color.BLUE);
                }
                switchColors();
            }
        }, 2000);
    }
}
