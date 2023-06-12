package com.disenio.rigormorty.service;

import com.disenio.rigormorty.models.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(EmailRequest mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mail.getModel());

        String html = templateEngine.process("mail-template", context);
        helper.setSubject(mail.getSubject());
        helper.setText(html, true);
        File logo = new File("src/main/resources/images/logo.png");
        helper.addInline("logo",logo);
        File rounderUp = new File("src/main/resources/images/rounder-up.png");
        helper.addInline("rounder-up",rounderUp);
        File divider = new File("src/main/resources/images/divider.png");
        helper.addInline("divider",divider);
        File rounderDwn = new File("src/main/resources/images/rounder-dwn.png");
        helper.addInline("rounder-dwn", rounderDwn);
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getFrom());
        emailSender.send(message);
    }

    public void sendInlinedCssEmail(EmailRequest mail) throws MessagingException, IOException {

        mail.setFrom("from@gmail.com");//replace with your desired email
        mail.setTo(mail.getTo());//replace with your desired email
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", mail.getNombre());
        model.put("fechaVencimiento", mail.getFechaVencimiento());
        model.put("parcela", mail.getParcela());
        model.put("title", mail.getTitle());
        mail.setModel(model);

        sendEmail(mail);
    }
}
