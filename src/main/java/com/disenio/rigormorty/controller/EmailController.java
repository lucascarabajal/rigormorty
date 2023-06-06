package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.models.request.EmailRequest;
import com.disenio.rigormorty.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class EmailController {

    private final EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<Object> sendMail(@RequestBody EmailRequest mail) throws MessagingException, IOException {
        emailSenderService.sendInlinedCssEmail(mail);
        return ResponseEntity.ok().body("Se envió mail");
    }
}
