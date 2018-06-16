package com.langford.maven.WeatherApp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class App {
	
	private Weather weather = new Weather();
	private JFrame frame;
	private JPanel panel;
	
    public static void main(String[] args) {
    	
    	App app = new App();
    	app.loadWeatherData();
    	app.setupUI();
    	app.loadUI();
    	app.updateUI();
    	
        
    }
    
    private void loadWeatherData() {
    	weather.loadWeatherData();
    }
    
    private void setupUI() {
    	
    	frame = new JFrame("Current Weather");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	
    }
    
    private void loadUI() {
        
    	panel.removeAll();
        JLabel locLabel = new JLabel("Location: " + weather.getLocationName());
        JLabel descLabel = new JLabel("Description: " + weather.getDescription());
        JLabel tempLabel = new JLabel(String.format("Temperature: %.2f C", weather.getTemp()));
        JLabel humdLabel = new JLabel("Humidity: " + weather.getHumidity() + "%");
        JLabel cloudLabel = new JLabel("Cloud Coverage: " + weather.getCloudCov() + "%");
        
        panel.add(locLabel);
        panel.add(descLabel);
        panel.add(tempLabel);
        panel.add(humdLabel);
        panel.add(cloudLabel);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    	
    }
    
    private void updateUI() {
    	Runnable helloRunnable = new Runnable() {
	        public void run() {
	        	loadWeatherData();
	            loadUI();
	        }
	    };
	
	    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	    executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);
		
    }
    
}
