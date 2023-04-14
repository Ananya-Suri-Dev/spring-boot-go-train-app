package com.rbc.springbootgotrainapp.controller;

import com.rbc.springbootgotrainapp.model.GoTrain;
import com.rbc.springbootgotrainapp.service.GoTrainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/goTrain/v1")
public class GoTrainController {
    public GoTrainController(GoTrainServiceImpl goTrainService) {
        this.goTrainService = goTrainService;
    }

    @Autowired
    private GoTrainServiceImpl goTrainService;

    @GetMapping("/schedules")
    public List<GoTrain> getSchedule(){
        return goTrainService.getSchedule();
    }

    @GetMapping("/schedules/{line}")
    public List<GoTrain> getScheduleForSpecificLine(@PathVariable String line){
        return goTrainService.getScheduleForSpecificLine(line);
    }

    @GetMapping("/schedules/{line}?departure={time}")
    public GoTrain getScheduleByLineAndDeparture(@PathVariable String line, Timestamp departure){
        return goTrainService.getScheduleByLineAndDeparture(line, departure);
    }
}
