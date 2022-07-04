package com.schedule.domain.component;

import com.schedule.domain.model.Event;
import com.schedule.domain.model.User;
import com.schedule.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailComponentImpl implements MailComponent{

    private final UserRepository userRepository;

    private final MailSender mailSender;

    @Override
    public void sendMail(Event event, String userName) {
        User user = userRepository.getUser(userName);
        SimpleMailMessage msg = new SimpleMailMessage();
        //送信元アドレス
        msg.setFrom("test@gmail.com");
        msg.setTo(user.getAddress());
        msg.setSubject(event.getEventName());
        //アプリケーションが動いているアドレスに変える必要あり
        msg.setText(event.getMemo() + "\r\n" + "http://localhost:8080/events/" + event.getId() + "/" + userName + "\r\n");

        try {
            mailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
