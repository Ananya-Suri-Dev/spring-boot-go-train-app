package com.rbc.springbootgotrainapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GoTrain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String line;
    private Timestamp departure;
    private Timestamp arrival;


    public GoTrain(int id, Timestamp departure, Timestamp arrival) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
    }

    public GoTrain(int id, Timestamp arrival) {
        this.id = id;
        this.arrival = arrival;
    }
}
