package org.liberty.android.burnindaydream;

import android.graphics.Color;
import android.os.Handler;
import android.service.dreams.DreamService;
import android.view.View;
import android.widget.FrameLayout;

public class BurnInDream extends DreamService {

    private FrameLayout rootLayout;

    private int currentColor = 0;

    private Handler handler = new Handler();
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        // Exit dream upon user touch
        setInteractive(false);
        // Hide system UI
        setFullscreen(true);

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
    }

    public void switchColors() {
        handler.postDelayed(new Runnable() {
            public void run() {
                currentColor++;
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

