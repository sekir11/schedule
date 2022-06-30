package com.schedule.application.controller;

import com.schedule.application.payload.CreateEventRequest;
import com.schedule.domain.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventRestController {

    private final EventService eventService;

    @PostMapping(path = "/events")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody CreateEventRequest request) {
        eventService.createEvent(request.toModel());
    }



}
