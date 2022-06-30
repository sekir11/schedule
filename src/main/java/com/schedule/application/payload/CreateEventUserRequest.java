package com.schedule.application.payload;

import lombok.Data;

@Data
public class CreateEventUserRequest {

    private String name;

    private String address;
}
