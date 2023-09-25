package com.example.valuuttamuunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConverterSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_settings);

        //luetaan intent, josta tämä käynnistetään
        Intent intent = getIntent();

        float conversionRate = intent.getFloatExtra("CONVERSION_RATE", 1.0f);
        EditText conversionRateEditText = findViewById(R.id.editTextConversionRate);
        conversionRateEditText.setText(String.valueOf(conversionRate));

        String homeCurrency = intent.getStringExtra("HOME_CURRENCY");
        String destinationCurrency = intent.getStringExtra("DESTINATION_CURRENCY");
        EditText homeCurrencyEditText = findViewById(R.id.homeCurrencyEdit);
        EditText destinationCurrencyEditText = findViewById(R.id.destinationCurrencyEdit);
        homeCurrencyEditText.setText(homeCurrency);
        destinationCurrencyEditText.setText(destinationCurrency);
    }

    public void backToConverter(View view) {
        //nappulan tapahtumankäsittelijä
        Intent intent = new Intent(this, MainActivity.class);

        EditText conversionRateEditText = findViewById(R.id.editTextConversionRate);
        float conversionRate = Float.parseFloat(conversionRateEditText.getText().toString());
        intent.putExtra("CONVERSION_RATE", conversionRate);

        EditText homeCurrencyEdit = findViewById(R.id.homeCurrencyEdit);
        EditText destinationCurrencyEdit = findViewById(R.id.destinationCurrencyEdit);
        intent.putExtra("HOME_CURRENCY", homeCurrencyEdit.getText().toString());
        intent.putExtra("DESTINATION_CURRENCY", destinationCurrencyEdit.getText().toString());

        //tämä komento käynnistää aktiviteetin
        startActivity(intent);
    }
}