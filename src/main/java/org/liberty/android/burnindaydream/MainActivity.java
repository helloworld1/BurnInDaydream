package org.liberty.android.burnindaydream;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button daydreamLaunchButton = (Button) findViewById(R.id.daydream_launch_button);
        daydreamLaunchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent launchDaydreamIntent = new Intent(android.provider.Settings.ACTION_DREAM_SETTINGS);
                startActivity(launchDaydreamIntent);
            }
        });

        Button previewButton = (Button) findViewById(R.id.preview_button);
        previewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PreviewActivity.class));
            }
        });
    }
}
