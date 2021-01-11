package edu.upc.dsa.minimo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity
{
    private Handler mHandler;
    private Runnable mRunnable;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final Context context = getApplicationContext();

        sharedPref = context.getSharedPreferences("Examen_Min2", Context.MODE_PRIVATE);
        boolean login = sharedPref.getBoolean("login", false);

        if (login) {
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(context, MainActivity.class));
                }
            };
        }

        else {
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(context, SearchActivity.class));
                }
            };
        }

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRunnable != null && mHandler != null)
            mHandler.removeCallbacks(mRunnable);
    }
}
