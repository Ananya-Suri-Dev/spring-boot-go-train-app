package com.rbc.springbootgotrainapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.springbootgotrainapp.dao.TimeDao;
import com.rbc.springbootgotrainapp.exception.AppBadRequestException;
import com.rbc.springbootgotrainapp.model.GoTrain;
import com.rbc.springbootgotrainapp.service.GoTrainServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class SpringBootGotrainAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGotrainAppApplication.class, args);

        // Create a data source for your database
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/mydb", "username", "password");
        // Create a JdbcTemplate with the data source
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Create a TimeDao with the JdbcTemplate
        TimeDao timeDao = new TimeDao(jdbcTemplate);

        // Save a time value to the database
        LocalTime time = LocalTime.parse("2:30pm", DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH));
        timeDao.saveTime(time);

        // Find the times that are less than the search time in the database
        LocalTime searchTime = LocalTime.parse("3:00pm", DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH));
        List<LocalTime> timesBeforeSearchTime = timeDao.findTimesBefore(searchTime);

    }


	//JSON file to db
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
            }
            catch (AppBadRequestException b ) {
                System.out.println("departure is not a valid 12 or 24-hour format time, : " + b.getMessage());}
            catch (IOException e ) {
                System.out.println("Unable to save the schedule info : " + e.getMessage());
            }
        };
	}


}
