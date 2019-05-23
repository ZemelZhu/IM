package com.im.zemel.services;

import com.im.zemel.bean.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class UntilService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendHtmlEmail(Email email) {
        try {
            MimeMessage message = this.mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(this.emailFrom);
            messageHelper.setTo(email.getTo());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(email.getText(), true);
            this.mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
