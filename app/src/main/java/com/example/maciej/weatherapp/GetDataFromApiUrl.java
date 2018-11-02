package com.example.maciej.weatherapp;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDataFromApiUrl extends AsyncTask<Void,Void,Void> {
	StringBuilder data = new StringBuilder(  );

	String temp, wind, cloud, pressure, humidity, sunrise, sunset,description,weatherSumm;
	@Override protected Void doInBackground( final Void... voids ) {

		try {
			URL url = new URL( "http://api.openweathermap.org/data/2.5/weather?q=Poznan&appid=b992f5d7096dc4a073bfa567e1b11286&units=metric" );

			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );

			String line = "";
			while((line!=null)) {
				line= bufferedReader.readLine();
				data.append( line+"\r\n" );
			}

			JSONObject jsonObject = new JSONObject( data.toString() );

			JSONArray jsonArray = jsonObject.getJSONArray( "weather" );

			JSONObject object = jsonArray.getJSONObject( 0 );

			JSONObject mainObject = jsonObject.getJSONObject( "main" );

			JSONObject windObjec = jsonObject.getJSONObject("wind");

			JSONObject cloudObject = jsonObject.getJSONObject("clouds");

			JSONObject sysObject = jsonObject.getJSONObject("sys");

			 temp = String.valueOf( mainObject.getDouble( "temp" ) );
			wind = String.valueOf( windObjec.getDouble( "speed" ) );
			cloud = String.valueOf(cloudObject.getDouble("all"));
			pressure= String.valueOf(mainObject.getDouble("pressure"));
			humidity = String.valueOf(mainObject.getDouble("humidity"));
			sunrise = String.valueOf(sysObject.getLong("sunrise"));
			sunset= String.valueOf(sysObject.getLong("sunset"));
			description=String.valueOf(object.get("description"));
			weatherSumm= "In Poznan there is"+temp+"degrees, there is "+description+" and the pressure shows "+pressure+" hPa";





		} catch ( MalformedURLException e ) {
			 e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		} catch ( JSONException e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override protected void onPostExecute( final Void aVoid ) {
		super.onPostExecute( aVoid );
		Date currentTime = Calendar.getInstance().getTime();

		String time = DateFormat.getDateTimeInstance().format(currentTime);



		MainActivity.temp.setText( this.temp+ (char) 0x00B0+"C" );
		MainActivity.wind.setText("Wind: "+this.wind);
		MainActivity.cloud.setText("Cloud: "+this.cloud+"%");
		MainActivity.pressure.setText("Pressure: "+this.pressure+" hPa");
		MainActivity.humidity.setText("Humidity: "+this.humidity+"%");
		MainActivity.sunrise.setText("Sunrise: "+convert(this.sunrise));
		MainActivity.sunset.setText("Sunset: "+ convert(this.sunset));
		MainActivity.updated.setText("Last updated: "+time);
		MainActivity.iconView.setImageResource(weatherIconResource());
		MainActivity.setWeatherSumm(weatherSumm);
	}


	private String convert(String time){
		long dv = Long.valueOf(time)*1000;
		Date date = new java.util.Date(dv);
		String formatData = new SimpleDateFormat("hh:mma").format(date);
		return formatData;
	}

	private int weatherIconResource(){
		int resource=0;
		if(description.equals("clear sky")){
			resource = R.mipmap.clear_sky;
		}else if (description.equals("few clouds")){
			resource = R.mipmap.few_clouds;
		} else if (description.equals("scattered clouds")){
			resource = R.mipmap.scattered_clouds;
		}else if (description.equals("broken clouds")){
			resource = R.mipmap.broken_clouds;
		}else if (description.equals("shower rain")){
			resource = R.mipmap.showe_rain;
		} else if (description.equals("rain")){
			resource = R.mipmap.rain;
		}else if (description.equals("thunderstorm")){
			resource = R.mipmap.thunderstrom;
		}else if (description.equals("snow")){
			resource = R.mipmap.snow;
		}else{
			resource=R.mipmap.ic_launcher;
		}


		return resource;
	}
}
