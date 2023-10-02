package com.example.tilantallentaminen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String message = "Counter: ";
    private int clickCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Jos bundle on olemassa, luetaan se
        if(savedInstanceState != null){
            clickCounter = savedInstanceState.getInt("COUNTER_VALUE", 0);
        }


        TextView counterText = findViewById(R.id.counterTextView);
        counterText.setText(message + clickCounter);
    }

    public void buttonClicked (View view) {
        clickCounter++;
        TextView counterText = findViewById(R.id.counterTextView);
        counterText.setText(message + clickCounter);
    }

    @Override
    protected void onSaveInstanceState (Bundle bundle){
        //järjestelmä kutsuu tätä metodia tarpeen mukaan, jotta voimme aktiviteetin tilan
        super.onSaveInstanceState(bundle);

        //talletetaan counterin nykyinen arvo
        bundle.putInt("COUNTER_VALUE", clickCounter);
    }

    /*
    Toinen tapa lukea arvo bundlesta
    @Override
    protected void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
        clickCounter = bundle.getInt("COUNTER_VALUE", 0);
    }
     */

}