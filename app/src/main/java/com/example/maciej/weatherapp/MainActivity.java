package com.example.maciej.weatherapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	public static TextView  cityName;
	public static TextView  temp;
	private TextView  description;
	private TextView  humidity;
	private TextView  pressure;
	private TextView  wind;
	private TextView  sunrise;
	private TextView  sunset;
	private TextView  updated;
	private ImageView iconView;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		cityName = findViewById( R.id.cityNameTV );
		temp = findViewById( R.id.temperatureTV );
		description = findViewById( R.id.cloudText );
		humidity = findViewById( R.id.humidityText );
		pressure = findViewById( R.id.pressureText );
		wind = findViewById( R.id.withText );
		sunrise = findViewById( R.id.sunriseText );
		sunset = findViewById( R.id.sunsetText );
		updated = findViewById( R.id.lastUpdatedTV );
		iconView = findViewById( R.id.imageThumbailIconIV );

		temp.setText( "gówno" );



		findWeather();




	}

	private void findWeather() {

		GetDataFromApiUrl getDataFromApiUrl = new GetDataFromApiUrl();
		getDataFromApiUrl.execute(  );
		Log.e( "kurwa", "japierdole" );

		/*String url = "http://api.openweathermap.org/data/2.5/weather?q=Poznan&appid=b992f5d7096dc4a073bfa567e1b11286&units=metric";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
			@Override public void onResponse( final JSONObject response ) {

				try {
					JSONObject jsonObject = response.getJSONObject( "main" );

					JSONArray jsonArray = response.getJSONArray( "weather" );

					JSONObject object = jsonArray.getJSONObject( 0 );

					String tempS = String.valueOf( jsonObject.getInt( "temp" ) );
					String pressureS = String.valueOf( jsonObject.getInt( "pressure" ) );
					String humidityS = String.valueOf( jsonObject.getInt( "humidity" ) );

					Log.e( "temp",tempS );

					String city = response.getString( "name" );


					temp.setText(tempS);

					String descripction = object.getString( "description" );


				} catch ( JSONException e ) {
					e.printStackTrace();
				}


			}
		} , new Response.ErrorListener() {
			@Override public void onErrorResponse( final VolleyError error ) {

			}
		});

		RequestQueue queue = Volley.newRequestQueue( this );
		queue.add( jsonObjectRequest );*/
	}
}
