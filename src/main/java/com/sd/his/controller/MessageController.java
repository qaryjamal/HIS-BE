package com.sd.his.controller;

import com.sd.his.Utill.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MessageController {

    Notifications notifications = new Notifications(0);
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SimpMessagingTemplate template;


    @GetMapping("/notify")
    public String getNotification(Principal principal) {
        String username = "user";

        notifications.increment();
        logger.info("counter" + notifications.getCount() + "" + principal.getName());

        template.convertAndSendToUser(principal.getName(),"/queue/notification",notifications);

          return "Notifications successfully sent to Angular !";
    }


}
