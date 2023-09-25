package com.example.valuuttamuunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private float conversionRate = 2.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        conversionRate = intent.getFloatExtra("CONVERSION_RATE", 1.0f);

        String homeCurrencyText = intent.getStringExtra("HOME_CURRENCY");
        String destinationCurrencyText = intent.getStringExtra("DESTINATION_CURRENCY");

        if (homeCurrencyText != null && destinationCurrencyText != null) {
            TextView homeCurrency = findViewById(R.id.homeCurrency);
            TextView destinationCurrency = findViewById(R.id.destinationCurrency);
            homeCurrency.setText(homeCurrencyText);
            destinationCurrency.setText(destinationCurrencyText);
        }

    }


    public void openSettings(View view) {
        //nappulan tapahtumankäsittelijä
        Intent intent = new Intent(this, ConverterSettingsActivity.class);

        intent.putExtra("CONVERSION_RATE", conversionRate);

        TextView homeCurrency = findViewById(R.id.homeCurrency);
        TextView destinationCurrency= findViewById(R.id.destinationCurrency);

        intent.putExtra("HOME_CURRENCY", homeCurrency.getText().toString());
        intent.putExtra("DESTINATION_CURRENCY", destinationCurrency.getText().toString());

        //tämä komento käynnistää aktiviteetin
        startActivity(intent);
    }

    public void convertCurrency(View view) {
        // luetaan source kentästä luku, tehdään muunnos ja kirjoitetaan
        // destination kenttään valuuttamuunnos
        TextView destinationCurrencyText = findViewById(R.id.destinationCurrencyText);
        EditText homeCurrencyEditText = findViewById(R.id.homeCurrencyEditText);

        String homeCurrencyInput = homeCurrencyEditText.getText().toString();
        float homeCurrencyAmount = Float.parseFloat(homeCurrencyInput);

        float destinationCurrencyAmount = homeCurrencyAmount * conversionRate;

        destinationCurrencyText.setText(String.valueOf(destinationCurrencyAmount));
    }

    public void switchCurrencies(View view) {

        TextView homeCurrency = findViewById(R.id.homeCurrency);
        TextView destinationCurrency= findViewById(R.id.destinationCurrency);
        EditText homeCurrencyEditText = findViewById(R.id.homeCurrencyEditText);
        TextView destinationCurrencyText = findViewById(R.id.destinationCurrencyText);


        String tmpHomeCurrency = homeCurrency.getText().toString();
        homeCurrency.setText(destinationCurrency.getText().toString());
        destinationCurrency.setText(tmpHomeCurrency);

        String homeCurrencyInput = homeCurrencyEditText.getText().toString();
        homeCurrencyEditText.setText(destinationCurrencyText.getText().toString());
        destinationCurrencyText.setText(homeCurrencyInput);

        conversionRate = 1 / conversionRate;

    }
}