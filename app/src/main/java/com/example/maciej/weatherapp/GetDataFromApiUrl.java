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

public class GetDataFromApiUrl extends AsyncTask<Void,Void,Void> {
	StringBuilder data = new StringBuilder(  );

	String temp;
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

			 temp = String.valueOf( mainObject.getInt( "temp" ) );
			/*String pressureS = String.valueOf( jsonObject.getInt( "pressure" ) );
			String humidityS = String.valueOf( jsonObject.getInt( "humidity" ) );*/

			Log.e( "temp", temp );

			//String city = response.getString( "name" );


			//temp.setText(tempS);

			String descripction = object.getString( "description" );


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

		MainActivity.temp.setText( this.temp );
	}
}
