package com.rbc.springbootgotrainapp;

import com.rbc.springbootgotrainapp.model.GoTrain;
import com.rbc.springbootgotrainapp.reposiroty.GoTrainRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class GoTrainIntegrationTest {

    @Autowired
    GoTrainRepository goTrainRepository;

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @LocalServerPort
    int randomServerPort;

    private String baseUrl = "http://localhost";

    @Autowired
    public static TestRestTemplate testRestTemplate;

    //Wil execute before running all the test
    @BeforeAll
    public static void init(){
        testRestTemplate = new TestRestTemplate();
    }

    //Will execute before running each test case
    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(randomServerPort+"").concat("/products");
    }

    //Making sure we are deleting the added records from the database leaving it to the original state
    @Test
    @Sql(statements = {"INSERT INTO GoTrain_Tbl(id, line, departure, arrival) VALUES (100,'Lakeshore', 1300, 1400)"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = {"DELETE FROM GoTrain_Tbl WHERE id=100 "}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
     void testGoTrainSchedule(){

       List<GoTrain> goTrains =  testRestTemplate.getForObject(baseUrl, List.class);
        Assertions.assertEquals(1, goTrains.size());
        Assertions.assertEquals(1, goTrains.size());

    }

}
