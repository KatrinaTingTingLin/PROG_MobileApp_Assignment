package com.example.tlin7877.assignment_1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.util.Log;

import com.example.tlin7877.assignment_1.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class AppLoadActivity extends Activity {
    private static final String TAG = AppLoadActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_initiate_load);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(AppLoadActivity.this, MainActivity.class);
                AppLoadActivity.this.startActivity(mainIntent);
                AppLoadActivity.this.finish();
            }
        }, 5000);
    }



    /**
     * Handle LoadButton clicked action
     */
    public void onLoadButtonClick(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
