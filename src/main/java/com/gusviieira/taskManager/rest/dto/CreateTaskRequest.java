package com.gusviieira.taskManager.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CreateTaskRequest {
    private String name;
    private String description;
    private int status;
    private LocalDate limitDate;
}
