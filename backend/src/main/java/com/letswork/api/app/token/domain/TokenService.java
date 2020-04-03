package com.letswork.api.app.token.domain;

import com.letswork.api.app.token.domain.exception.InvalidTokenException;
import com.letswork.api.app.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

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
        String htmlMsg = "Aby potwierdzi&#263; konto, kliknij prosz&#281; na poni&#380;szy link:<br>" +
                "<a href='http://localhost:3000/confirm-account?token=" + confirmationToken + "'>" +
                "http://localhost:3000/confirm-account?token=" + confirmationToken + "</a>";
        mimeMessage.setContent(htmlMsg, "text/html");
        helper.setTo(to);
        helper.setSubject("Dokończenie rejestracji");
        helper.setFrom("LetsWork");
        mailSender.send(mimeMessage);
    }


    public void cleanAllExpiredTokens() {
        repository.findAll()
                .stream()
                .filter(token -> Math.abs(getCurrentTimeInSeconds() - Long.parseLong(token.getDateInSeconds())) > 900)
                .forEach(repository::delete);
    }

    private long getCurrentTimeInSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis()));
    }

    public TokenEntity findTokenByConfirmationToken(String confirmationToken) {
        TokenEntity token = repository.findByConfirmationToken(confirmationToken);
        if (token == null) {
            throw new InvalidTokenException(InvalidTokenException.CAUSE.CONFIRMATION_TOKEN_NOT_EXISTS);
        }
        return token;
    }

    public void deleteToken(TokenEntity token) {
        repository.delete(token);
    }
}
