package org.liberty.android.burnindaydream;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class BurnInDream extends DreamService {

    private FrameLayout rootLayout;

    private int currentTick = 0;

    private Handler handler = new Handler();

    private int maxTicks = 0;

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

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int limit = preferences.getInt("daydream_time_limit", 1200);
        maxTicks = limit / 2;
    }

    public void switchColors() {
        handler.postDelayed(new Runnable() {
            public void run() {
                currentTick++;
                if (currentTick % 4 == 0) {
                    rootLayout.setBackgroundColor(Color.GRAY);
                }
                if (currentTick % 4 == 1) {
                    rootLayout.setBackgroundColor(Color.RED);
                }
                if (currentTick % 4 == 2) {
                    rootLayout.setBackgroundColor(Color.GREEN);
                }
                if (currentTick % 4 == 3) {
                    rootLayout.setBackgroundColor(Color.BLUE);
                }
               //  Log.v("BurnInDream", "CurrentTick: " + currentTick + " max tick: " + maxTicks);
                if (currentTick > maxTicks) {

                    finish();
                } else {
                    switchColors();
                }
            }
        }, 2000);

    }
        
}

