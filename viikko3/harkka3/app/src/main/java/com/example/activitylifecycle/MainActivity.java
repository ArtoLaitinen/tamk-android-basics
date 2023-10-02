package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("APP", "Activity onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("APP", "Activity onResume");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("APP", "Activity onStart");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("APP", "Activity onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("APP", "Activity onDestroy");
    }
}
