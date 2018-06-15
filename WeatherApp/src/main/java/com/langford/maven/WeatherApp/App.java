package com.langford.maven.WeatherApp;

public class App {
	
    public static void main(String[] args) {
    	
        Weather weather = new Weather();
        weather.loadWeatherData();
        
        System.out.println("Location: " + weather.getLocationName());
        System.out.println("Description: " + weather.getDescription());
        System.out.println("Temperature: " + weather.getTemp());
        System.out.println("Humidity: " + weather.getHumidity() + "%");
        System.out.println("Cloud Coverage: " + weather.getCloudCov() + "%");
        
    }
    
}
