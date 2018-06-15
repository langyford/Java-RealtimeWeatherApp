package com.langford.maven.WeatherApp;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class Weather {
	
	private JSONObject response;
	private double temp;
	private int cloudCov;
	private int humidity;
	private String description;
	
	// TODO:
	private String locationName; // http://ip-api.com/json/
	// TIME
	
	public void loadWeatherData() {
		
		JSONObject locationObj = getCurrentLocation();
		double lon = locationObj.getDouble("lon");
		double lat = locationObj.getDouble("lat");
		this.locationName = locationObj.getString("city") + ", " + locationObj.getString("countryCode");
		
		try {
		
			// Openweathermap API call
			String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=60a59a9068bcbe5381c86ecb005d9adc";
			HttpResponse<JsonNode> request = Unirest.get(url)
			  .header("accept", "application/json")
			  .asJson();
	
			// retrieve the parsed JSONObject from the response
			response = request.getBody().getObject();
			
			if (response != null) {
				initialiseData();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private JSONObject getCurrentLocation() {
		
		JSONObject locationResponse = null;
		try {
		
		// ip-location API call
		HttpResponse<JsonNode> request = Unirest.get("http://ip-api.com/json/")
		  .header("accept", "application/json")
		  .asJson();

		// retrieve the parsed JSONObject from the response
		locationResponse = request.getBody().getObject();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return locationResponse;
		
	}
	
	private void initialiseData() {
		
		setDescription();
		setTemp();
		setCloudCov();
		setHumidity();
		
	}
	
	private void setTemp() {
		
		// get temperature data
		JSONObject mainObj = response.getJSONObject("main");
		double kelvinTemp = mainObj.getDouble("temp");
		
		// convert to celcius
		this.temp = kelvinTemp - 273.15;
		
	}
	public double getTemp() {
		return this.temp;
	}
	
	private void setHumidity() {
		
		// get temperature data
		JSONObject mainObj = response.getJSONObject("main");
		this.humidity = mainObj.getInt("humidity");
		
	}
	public int getHumidity() {
		return this.humidity;
	}
	
	private void setCloudCov() {
		
		// get temperature data
		JSONObject cloudObj = response.getJSONObject("clouds");
		this.cloudCov = cloudObj.getInt("all");
		
	}
	public int getCloudCov() {
		return this.cloudCov;
	}
	
	private void setDescription() {
		
		// get description data
		JSONObject weatherObj = response.getJSONArray("weather").getJSONObject(0);
		this.description = weatherObj.getString("description");
		
	}
	public String getDescription() {
		return this.description;
	}
	
	public String getLocationName() {
		return this.locationName;
	}
	

}
