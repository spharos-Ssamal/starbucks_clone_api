package com.ssamal.starbucks_clone_api.v1.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;
    public String joinEmail(String email, int randNum){
        String setForm = senderEmail;
        String toMail = email;
        String title = "회원가입 인증 이메일입니다.";
        String content =
                "가입을 환영합니다." +
                        "<br><br>" +
                        "인증 번호는 " + randNum + " 입니다.<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입 해 주세요.";
        mailSend(setForm, toMail, title, content);

        return Integer.toString(randNum);
    }
    public String mailSend(String setForm, String toMail, String title, String content){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setForm);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return toMail;
    }

}
