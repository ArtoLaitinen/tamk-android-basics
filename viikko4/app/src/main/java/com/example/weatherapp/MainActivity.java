package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeatherData(View view) {

        String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=Tampere&appid=dba60f59482d08e0f171893a7c1214b6&units=metric";
        StringRequest request = new StringRequest(Request.Method.GET, WEATHER_URL, response -> {
            // Säätiedot haettu onnistuneesti
            Toast.makeText(this, response, Toast.LENGTH_LONG).show();
            parseWeatherJsonAndUpdateUI(response);

        }, error -> {
            // Verkkovirhe yms.
            Toast.makeText(this, "VERKKOVIRHE", Toast.LENGTH_LONG).show();
        });

        //lähetetään request volleylla == lisätään request requestqueueen
        Volley.newRequestQueue(this).add(request);
        //tämän jälkeen tullaan jompaankumpaan callbackin haaraan kun request on valmis
    }

    private void parseWeatherJsonAndUpdateUI(String response) {
        //parsitaan json ja päivitetään näytölle lämpötila säätila ja tuulen nopeus
        try {
            JSONObject weatherJSON = new JSONObject(response);
            String weather = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("main");
            double temperature = weatherJSON.getJSONObject("main").getDouble("temp");
            double wind = weatherJSON.getJSONObject("wind").getDouble("speed");

            TextView temperatureTextView = findViewById(R.id.temperatureTextView);
            temperatureTextView.setText("" + temperature + " C");

            TextView windTextView = findViewById(R.id.windTextView);
            windTextView.setText("" + wind + " m/s");

            TextView weatherTextView = findViewById(R.id.weatherTextView);
            weatherTextView.setText(weather);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void openForeca(View view) {
        String foreca = "https://www.foreca.fi/Finland/Tampere/10vrk";
        Uri forecaUri = Uri.parse(foreca);
        Intent intent = new Intent(Intent.ACTION_VIEW, forecaUri);
        try{
            startActivity(intent);
        }
        catch (Exception e) {

        }
        /*
        //ei toimi jostain syystä tällä, tarkasta tämä

        //varmistetaan onko laitteella tämän intentin toteuttava palvelu
        if(intent.resolveActivity(getPackageManager()) != null) {
            //webbiselain löytyy
            startActivity(intent);
        }
        else {
            //ei webbiselainta, ei voi näyttää
            Toast.makeText(this, "no browser found", Toast.LENGTH_LONG).show();
        }

         */
    }
}