package com.disenio.rigormorty.service;

import com.disenio.rigormorty.models.request.EmailRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;

import java.io.*;
import java.net.URL;
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

        InputStream logoStream = new URL("https://rigormorty.s3.amazonaws.com/assets/logo.png").openStream();
        byte[] logoBytes = IOUtils.toByteArray(logoStream);
        helper.addInline("logo", new ByteArrayResource(logoBytes), "image/png");

        InputStream rounderUp = new URL("https://rigormorty.s3.amazonaws.com/assets/rounder-up.png").openStream();
        byte[] rounderUpBytes = IOUtils.toByteArray(rounderUp);
        helper.addInline("rounder-up", new ByteArrayResource(rounderUpBytes), "image/png");

        InputStream divider = new URL("https://rigormorty.s3.amazonaws.com/assets/divider.png").openStream();
        byte[] dividerBytes = IOUtils.toByteArray(divider);
        helper.addInline("divider", new ByteArrayResource(dividerBytes), "image/png");

        InputStream rounderDwn = new URL("https://rigormorty.s3.amazonaws.com/assets/rounder-dwn.png").openStream();
        byte[] rounderDwnBytes = IOUtils.toByteArray(rounderDwn);
        helper.addInline("rounder-dwn", new ByteArrayResource(rounderDwnBytes), "image/png");

        helper.setTo(mail.getTo());
        helper.setFrom(mail.getFrom());
        emailSender.send(message);
    }

    public ResponseEntity<Object> sendInlinedCssEmail(EmailRequest mail) throws MessagingException, IOException {

        mail.setFrom("from@gmail.com");//replace with your desired email
        mail.setTo(mail.getTo());//replace with your desired email
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", mail.getNombre());
        model.put("fechaVencimiento", mail.getFechaVencimiento());
        model.put("parcela", mail.getParcela());
        model.put("title", mail.getTitle());
        mail.setModel(model);

        sendEmail(mail);
        return ResponseEntity.ok().body("Se envío correctamente el mail");
    }
}
