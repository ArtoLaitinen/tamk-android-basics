package com.example.harkka3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view) {
        //getting the button id that was pressed
        int buttonId = view.getId();

        Button button = findViewById(buttonId);
        button.setBackgroundColor(Color.YELLOW);
    }

}