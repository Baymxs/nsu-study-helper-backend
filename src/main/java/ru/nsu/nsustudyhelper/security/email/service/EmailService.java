package ru.nsu.nsustudyhelper.security.email.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;

    public void sendMessage(String[] to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("team.study.helper@yandex.ru");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendMessage(String to, String subject, String body) {
        sendMessage(new String[]{to}, subject, body);
    }

    public void sendMessageWithImage(String[] to, String subject, String body, byte[] image, String format) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("<html>" + "<body><div>" + body + " </div>"
                + "<div><img src=cid:image /></div>", true);

        ByteArrayDataSource dataSource = new ByteArrayDataSource(image, "image/" + format);
        helper.addInline("image", dataSource);
        emailSender.send(message);
    }
}
