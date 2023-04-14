package com.rbc.springbootgotrainapp;

import com.rbc.springbootgotrainapp.controller.GoTrainController;
import com.rbc.springbootgotrainapp.model.GoTrain;
import com.rbc.springbootgotrainapp.service.GoTrainServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = GoTrainController.class)
 class GoTrainControllerTest {

    @MockBean
    private GoTrainServiceImpl goTrainService;

    @Autowired
    private MockMvc mockMvc;

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Test
     void testGetSchedule() throws Exception {
        BDDMockito.given(goTrainService.getSchedule()).willReturn((List<GoTrain>)new GoTrain(100, "Lakeshore", timestamp, timestamp));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/schedule)"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("100"))
                .andExpect(jsonPath("line").value("Lakeshore"))
                .andExpect(jsonPath("departure").value("timestamp"))
                .andExpect(jsonPath("arrival").value("timestamp"));
    }

    @Test
     void testGetScheduleForSpecificLine() throws Exception {
        BDDMockito.given(goTrainService.getScheduleForSpecificLine("Lakeshore")).willReturn((List<GoTrain>) new GoTrain(100, timestamp, timestamp ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/schedule/Lakeshore"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("100"))
                .andExpect(jsonPath("departure").value("timestamp"))
                .andExpect(jsonPath("arrival").value("timestamp"));

    }

    @Test
    void testGetScheduleByLineAndDeparture() throws Exception {
        BDDMockito.given(goTrainService.getScheduleByLineAndDeparture("Lakeshore", timestamp)).willReturn( new GoTrain(100, timestamp));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/schedule/Lakeshore"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("100"))
                .andExpect(jsonPath("arrival").value("timestamp"));
    }
}
