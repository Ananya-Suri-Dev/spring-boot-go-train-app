package com.rbc.springbootgotrainapp;

import com.rbc.springbootgotrainapp.model.GoTrain;
import com.rbc.springbootgotrainapp.reposiroty.GoTrainRepository;
import com.rbc.springbootgotrainapp.service.GoTrainServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GoTrainServiceTest {

    @MockBean
    GoTrainRepository goTrainRepository;

    @MockBean
    GoTrainServiceImpl goTrainService;

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Test
    public void testGetSchedule() {
        BDDMockito.given(goTrainRepository.findAll())
                .willReturn((List<GoTrain>) new GoTrain(100, "Lakeshore",timestamp, timestamp));
        List<GoTrain> goTrainFindByLine = goTrainService.getSchedule();
        Assertions.assertTrue(goTrainFindByLine.isEmpty());
    }

    @Test
    public void testGetScheduleForSpecificLine() {
        BDDMockito.given(goTrainRepository.findByLine(ArgumentMatchers.anyString()))
                .willReturn((List<GoTrain>) new GoTrain(100, timestamp, timestamp));

        List<GoTrain> goTrainFindByLine = goTrainService.getScheduleForSpecificLine("Lakeshore");
        Assertions.assertTrue(goTrainFindByLine.isEmpty());
    }

}
