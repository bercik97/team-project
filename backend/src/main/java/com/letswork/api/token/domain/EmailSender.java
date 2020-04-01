package com.letswork.api.token.domain;

import javax.mail.MessagingException;

interface EmailSender {

    void sendEmail(String to, String confirmationToken) throws MessagingException;
}
