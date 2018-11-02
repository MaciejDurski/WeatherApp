package com.example.maciej.weatherapp;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	public static TextView  cityName;
	public static TextView  temp;
	public static TextView description;
	public static TextView humidity;
	public static TextView pressure;
	public static TextView wind;
	public static TextView sunrise;
	public static TextView sunset;
	public static TextView updated;
	public static ImageView iconView;
	public static TextView cloud;


	public static String weatherSumm;
	Boolean isPlaying = false;
	TextToSpeech toSpeech;
	int result;


	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		cityName = findViewById( R.id.cityNameTV );
		temp = findViewById( R.id.temperatureTV );
		description = findViewById( R.id.cloudText );
		humidity = findViewById( R.id.humidityText );
		pressure = findViewById( R.id.pressureText );
		wind = findViewById(R.id.windText);
		sunrise = findViewById( R.id.sunriseText );
		sunset = findViewById( R.id.sunsetText );
		updated = findViewById( R.id.lastUpdatedTV );
		cloud = findViewById(R.id.cloudText);
		iconView = findViewById( R.id.imageThumbailIconIV );


		toSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int i) {
				if (i == TextToSpeech.SUCCESS) {
					result = toSpeech.setLanguage(Locale.UK);

				} else {
					Toast.makeText(MainActivity.this, "not available", Toast.LENGTH_SHORT).show();
				}
			}
		});



		findWeather();
		readingWeather();


	}

	private void readingWeather() {


		final ImageButton readWeatherBtn = findViewById(R.id.imageButton);
		readWeatherBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (isPlaying == false) {
					if ((result == TextToSpeech.LANG_MISSING_DATA)) {
						Toast.makeText(MainActivity.this, "lang not available", Toast.LENGTH_SHORT).show();

					} else {

						toSpeech.speak(weatherSumm, TextToSpeech.QUEUE_FLUSH, null);
					}
					readWeatherBtn.setImageResource(R.mipmap.speaker);
					isPlaying = true;
				} else {
					if (toSpeech != null) {
						toSpeech.stop();
					}
					readWeatherBtn.setImageResource(R.mipmap.mute);
					isPlaying = false;
				}

			}
		});
	}

	private void findWeather() {

		GetDataFromApiUrl getDataFromApiUrl = new GetDataFromApiUrl();
		getDataFromApiUrl.execute(  );



	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (toSpeech != null) {
			toSpeech.stop();
			toSpeech.shutdown();
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		if (toSpeech != null) {
			toSpeech.stop();
			toSpeech.shutdown();
		}
	}

	public static void setWeatherSumm(String weatherSumm) {
		MainActivity.weatherSumm = weatherSumm;
	}


}
