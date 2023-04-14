package com.rbc.springbootgotrainapp.service;

import com.rbc.springbootgotrainapp.exception.LineNotFoundException;
import com.rbc.springbootgotrainapp.model.GoTrain;
import com.rbc.springbootgotrainapp.reposiroty.GoTrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GoTrainServiceImpl {

    public GoTrainServiceImpl(GoTrainRepository goTrainRepository) {
        this.goTrainRepository = goTrainRepository;
    }

    @Autowired
    private GoTrainRepository goTrainRepository;


    public List<GoTrain> getSchedule(){
        return goTrainRepository.findAll();
    }

    public List<GoTrain> getScheduleForSpecificLine(String line){
        //exceptional handling
        if(goTrainRepository.findByLine(line).isEmpty())
            throw new LineNotFoundException("HTTP 404 (Not Found) : Line does not exist");
        return goTrainRepository.findByLine(line);
    }

    public GoTrain getScheduleByLineAndDeparture(String line, Timestamp departure){
        //exceptional handling
        if(goTrainRepository.findByLine(line).isEmpty())
            throw new LineNotFoundException("HTTP 404 (Not Found) : Line does not exist");
        return goTrainRepository.findByLineAndDeparture(line,departure);
    }

    public List<GoTrain> save(List<GoTrain> goTrains) {
        return goTrainRepository.saveAll(goTrains);
    }
}
