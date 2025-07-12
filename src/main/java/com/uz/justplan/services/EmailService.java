package com.uz.justplan.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.uz.justplan.config.AppProperties;
import com.uz.justplan.core.ContactUs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private static final Log log = LogFactory.getLog(EmailService.class);

    @Autowired
    private AppProperties props;


    public void sendEmailNewUser(String toEmail, String name, String password) {
        sendEmail(toEmail,
                "Welcome to " + props.getName(),
                String.format("Dear %s, " +
                        "Here is your password %s." +
                        "</br>" +
                        "Regards", name, password));
    }

    public void sendEmailPasswordReset(String toEmail, String name, String password) {
        sendEmail(toEmail,
                "New password to " + props.getName(),
                String.format("Dear %s, " +
                        "Here is your password %s." +
                        "</br>" +
                        "Regards", name, password));
    }

    public void sendEmailContactUs(ContactUs m) {
        sendEmail(props.getEmail().getFromAddress(),
                String.format("Contact Us Email from: %s, Email: %s, Subject: %s", m.getName(), m.getEmail(), m.getSubject()),
                m.getDetails());
    }

    private void sendEmail(String emailTo, String subject, String body) {
        log.info("Sending email to: " + emailTo + " with subject: " + subject + ", body: " + body);
        log.info("Sending email app name: " + props.getName());
        log.info("Sending email from: " + props.getEmail().getFromAddress() + ", serviceKey: " + props.getEmail().getServiceKey());

        Email from = new Email(props.getEmail().getFromAddress());
        Email to = new Email(emailTo);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(props.getEmail().getServiceKey());
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            log.info("Status Code: " + response.getStatusCode());
            log.info("Body: " + response.getBody());
            log.info("Headers: " + response.getHeaders());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
