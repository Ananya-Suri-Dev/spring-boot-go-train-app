package com.rbc.springbootgotrainapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.springbootgotrainapp.model.GoTrain;
import com.rbc.springbootgotrainapp.service.GoTrainServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class SpringBootGotrainAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGotrainAppApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(GoTrainServiceImpl goTrainService){
        return args -> {
            // Read JSON file and write to in-memory db(H2)
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<GoTrain>> typeReference = new TypeReference<List<GoTrain>>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/schedule.json");
            try {
                List<GoTrain> goTrains = mapper.readValue(inputStream, typeReference);
                goTrainService.save(goTrains);
                System.out.println("Schedule info saved!");
            } catch (IOException e) {
                System.out.println("Unable to save the schedule info : " + e.getMessage());
            }
        };
	}
}
