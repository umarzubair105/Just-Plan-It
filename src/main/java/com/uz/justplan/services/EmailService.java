package com.uz.justplan.services;

import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
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

        Resend resend = new Resend("re_djWFM4MA_MDa7kfWq1TrWLewV5D99qasr");


        try {
            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from("Acme <umarzubair@projecthaven.com>")
                    .to("umarzubair@gmail.com")
                    .subject("it works!")
                    .html("<strong>hello world</strong>")
                    .build();

            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
            log.info("Sent-------------------" + data.getId());
            log.info("Sent-------------------" + data.toString());
        } catch (Exception e) {//| ResendException
            log.error(e.getMessage(), e);
            //e.printStackTrace();
        }
/*

        Resend resend = new Resend("••••••••••••••••••••••••••••••••••••");

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("onboarding@resend.dev")
                .to("umarzubair@gmail.com")
                .subject("Hello World")
                .html("<p>Congrats on sending your <strong>first email</strong>!</p>")
                .build();

        SendEmailResponse data = resend.emails().send(sendEmailRequest);
*/

        /*Email from = new Email(props.getEmail().getFromAddress());
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
        }*/
    }

    //private String
}
