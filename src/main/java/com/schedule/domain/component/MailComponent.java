package com.schedule.domain.component;

import com.schedule.domain.model.Event;

public interface MailComponent {

    void sendMail(Event event, String userName);
}
