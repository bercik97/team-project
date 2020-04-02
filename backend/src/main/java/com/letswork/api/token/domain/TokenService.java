package com.letswork.api.token.domain;

import com.letswork.api.token.domain.exception.InvalidTokenException;
import com.letswork.api.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor
class TokenService implements EmailSender {

    private final TokenRepository repository;
    private final TokenFactory factory;
    private final JavaMailSender mailSender;

    public void sendRegisterConfirmationToken(UserEntity user) {
        try {
            TokenEntity token = factory.create(user);
            sendEmail(user.getEmail(), token.getConfirmationToken());
            repository.save(token);
        } catch (Exception e) {
            throw new InvalidTokenException(InvalidTokenException.CAUSE.CONFIRMATION_TOKEN_NOT_SENT);
        }
    }

    @Override
    public void sendEmail(String to, String confirmationToken) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        String htmlMsg = "Aby potwierdzić konto, kliknij proszę na poniższy link:<br>" +
                "<a href='http://localhost:3000/confirm-account?token=" + confirmationToken + "'>" +
                "http://localhost:3000/confirm-account?token=" + confirmationToken + "</a>";
        mimeMessage.setContent(htmlMsg, "text/html");
        helper.setTo(to);
        helper.setSubject("Dokończenie rejestracji");
        helper.setFrom("LetsWork");
        mailSender.send(mimeMessage);
    }
}
