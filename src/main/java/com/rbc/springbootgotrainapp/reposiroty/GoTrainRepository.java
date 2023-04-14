package com.rbc.springbootgotrainapp.reposiroty;

import com.rbc.springbootgotrainapp.model.GoTrain;
import org.springframework.data.jpa.repository.JpaRepository;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;


public interface GoTrainRepository extends JpaRepository<GoTrain, Integer> {
    List<GoTrain> findByLine(String line);

    GoTrain findByLineAndDeparture(String line, Timestamp departure);
}
