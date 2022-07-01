package com.schedule.application.controller;

import com.schedule.application.payload.EditParticipantRequest;
import com.schedule.domain.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParticipantRestController {

    private final ParticipantService participantService;

    @PatchMapping(path = "/participants")
    public void editParticipant(@RequestBody EditParticipantRequest request) {
        System.out.println(request);
        participantService.editParticipant(request.toModel());
    }
}
