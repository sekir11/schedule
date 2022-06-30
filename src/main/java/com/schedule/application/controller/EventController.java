package com.schedule.application.controller;

import com.schedule.application.payload.GetEventListPayload;
import com.schedule.application.payload.GetEventPayload;
import com.schedule.application.session.SessionContext;
import com.schedule.domain.model.Event;
import com.schedule.domain.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final SessionContext sessionContext;
    private final EventService eventService;

    @GetMapping(path = "/events")
    public String events(Model model) {
        String name = sessionContext.getSessionUser().getName();
        List<Event> events = eventService.getEventsByName(name);
        model.addAttribute("user", sessionContext.getSessionUser());
        model.addAttribute("eventList", GetEventListPayload.of(events));
        return "main";
    }

    @GetMapping(path = "/create-event-page")
    public String getCreateEventPage(Model model) {
        model.addAttribute("user", sessionContext.getSessionUser());
        return "create-event";
    }

    @GetMapping(path = "/events/{id}/{userName}")
    public String getEventDetail(@PathVariable("id") Integer id,
                                 @PathVariable("userName") String userName,
                                 Model model) {
        Event event = eventService.getEvent(id);
        model.addAttribute("event", GetEventPayload.of(event));
        return "event-detail";
    }



}
