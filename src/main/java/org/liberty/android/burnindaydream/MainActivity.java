package org.liberty.android.burnindaydream;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity
{
    private EditText timeLimitEdit;

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
        timeLimitEdit = (EditText) findViewById(R.id.daydream_time_limit_edit);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int limit = preferences.getInt("daydream_time_limit", 1200);
        timeLimitEdit.setText("" + limit);
    }

    @Override
    public void onPause() {
        super.onPause();
        int limit = Integer.valueOf(timeLimitEdit.getText().toString());
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putInt("daydream_time_limit", limit);
        editor.commit();
    }
}
