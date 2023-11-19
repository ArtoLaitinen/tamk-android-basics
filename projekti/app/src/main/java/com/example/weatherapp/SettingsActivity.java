package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Luetaan sharedprefenseistä, mikä yksikkö on valittuna ja asetetaan se switchin arvoksi
        SharedPreferences sharedPreferences = getSharedPreferences("WeatherPrefs", Context.MODE_PRIVATE);
        boolean isMetric = sharedPreferences.getBoolean("isMetric", true);
        Switch unitSwitch = findViewById(R.id.unitSwitch);
        unitSwitch.setChecked(isMetric);

    }

    public void openMainActivity(View view) {
        //tallennetaan valittu yksikkö sharedprefenseihin
        SharedPreferences sharedPreferences = getSharedPreferences("WeatherPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Switch unitSwitch = findViewById(R.id.unitSwitch);
        editor.putBoolean("isMetric", unitSwitch.isChecked());
        editor.apply();

        //avataan päänäkymä
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}