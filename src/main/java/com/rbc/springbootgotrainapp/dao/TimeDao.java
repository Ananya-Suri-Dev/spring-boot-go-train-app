package com.rbc.springbootgotrainapp.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TimeDao {

    private final JdbcTemplate jdbcTemplate;

    public TimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTime(LocalTime time) {
        String sql = "INSERT INTO your_table (time_column) VALUES (?)";
        jdbcTemplate.update(sql, time);
    }

    public List<LocalTime> findTimesBefore(LocalTime time) {
        String sql = "SELECT time_column FROM your_table WHERE time_column < ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);
        RowMapper<LocalTime> rowMapper = (rs, rowNum) -> LocalTime.parse(rs.getString("time_column"), formatter);
        return jdbcTemplate.query(sql, rowMapper, time);
    }
}
