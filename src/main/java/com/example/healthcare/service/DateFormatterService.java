package com.example.healthcare.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class DateFormatterService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String format(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}