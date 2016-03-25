package com.playbig.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import com.playbig.R;

/**
 * Created by User on 2/10/2016.
 */
public class Activity_splash  extends Activity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);








        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */





                Intent mainIntent = new Intent(Activity_splash.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}