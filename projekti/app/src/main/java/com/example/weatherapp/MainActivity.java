package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


/*
kysy:
    gps permission kysyminen (ei tarvitsisi käynnistää uudestaan)
    error handling (getWeatherData)
    näytön kääntö (kutsutaanko api uudestaan?, miksi ei näy kunnolla)
    mitä toiseen ruutuun?
    lisätäänkö mahdollisuus omaan hakuun?
 */
public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private double latitude;
    private double longitude;
    private static final String API_KEY = "dba60f59482d08e0f171893a7c1214b6";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGPS();
    }

    public void startGPS() {
        //Chekataan, onko oikeudet paikkatietoon, jos ei ole, pyydetään oikeudet dialogilla
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //meillä ei oikeuksia -> pyydetään ne
            requestPermissions(new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            return;
        }
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Jos on oikeudet, luetaan gps ja/tai rekisteöidytään kuuntelemaan LAT LNG -parametreja
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 500, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                getWeatherData();
            }
        });
    }

    public void getWeatherData(){
        String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%.6f&lon=%.6f&appid=%s&units=metric";
        String formattedWeatherUrl = String.format(WEATHER_URL, latitude, longitude, API_KEY);

        StringRequest request = new StringRequest(Request.Method.GET, formattedWeatherUrl, response -> {
            parseWeatherJsonAndUpdateUI(response);
        }, error -> {
            Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
        });

        //lähetetään request volleylla == lisätään request requestqueueen
        Volley.newRequestQueue(this).add(request);

    }

    private void parseWeatherJsonAndUpdateUI(String response) {
        //koska tiedämme, että gps on nyt löytynyt, poistetaan lataus teksti näkyvistä
        TextView loadingTextView = findViewById(R.id.loadingTextView);
        loadingTextView.setVisibility(View.GONE);

        //parsitaan json ja päivitetään tiedot näytölle
        try {
            JSONObject weatherJSON = new JSONObject(response);

            String city = weatherJSON.getString("name");
            TextView cityTextView = findViewById(R.id.cityTextView);
            cityTextView.setText(city);

            String iconID = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("icon");
            ImageView iconImageView = findViewById(R.id.iconImageView);
            String imageUrl = "https://openweathermap.org/img/wn/" + iconID + "@4x.png";
            Picasso.get().load(imageUrl).into(iconImageView);

            double temperature = weatherJSON.getJSONObject("main").getDouble("temp");
            TextView temperatureTextView = findViewById(R.id.temperatureTextView);
            temperatureTextView.setText(temperature + " °C" );

            double wind = weatherJSON.getJSONObject("wind").getDouble("speed");
            String windString = getString(R.string.wind);
            TextView windTextView = findViewById(R.id.windTextView);
            windTextView.setText(windString + ": " + wind + " m/s");

            double humidity = weatherJSON.getJSONObject("main").getDouble("humidity");
            String humidityString = getString(R.string.humidity);
            TextView humidityTextView = findViewById(R.id.humidityTextView);
            humidityTextView.setText(humidityString + ": " + humidity + " %");


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}

