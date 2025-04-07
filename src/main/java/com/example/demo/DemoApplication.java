package com.example.demo;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@SpringBootApplication
public class DemoApplication {
	private GeoApiContext context;

	@RequestMapping("/hello")
	String home() {
		return "Hello World(⋈◍＞◡＜◍)。✧♡";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Value("${api.key}")
    private String apiKey;

	public void MapsController() {
		this.context = new GeoApiContext.Builder()
				.apiKey(apiKey)
				.build();

		System.out.println("API Key: " + apiKey);
	}

	@GetMapping("/maps")
	public String geocode(@RequestParam String address) throws Exception {
		GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
		return results[0].formattedAddress;
	}
}
