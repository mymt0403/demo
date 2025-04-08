package com.example.demo;

import com.example.dao.BulkyGarbageFacilityDAO;
import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
	public String fetchData() throws Exception {
		Connection conn = BulkyGarbageFacilityDAO.conn();
		List<BulkyGarbageFacility> facilityList =  BulkyGarbageFacilityDAO.fetchFacilities(conn);

		List<String> facilities = new ArrayList<>();

		facilityList.forEach(fac -> facilities.add(fac.getFacilityName()));

		return facilities.toString();
	}
}
