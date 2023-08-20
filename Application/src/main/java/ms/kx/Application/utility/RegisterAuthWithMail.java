package ms.kx.Application.utility;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class RegisterAuthWithMail {
    private static final String fromEmail = "no_reply@ks.msx.com";
    private static final String subject = "Verification Code";
    private static final String body = "Your verification code is ";
    @Getter
    private final int confirmationCode = getCode();

    public void sendConfirmationMail(Session session, String toEmail){
        try {
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body + confirmationCode, "UTF-8");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(msg);
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    private int getCode(){
        int minValue = 1000;
        int maxValue = 1999;
        return (int) Math.floor(Math.random() * (maxValue - minValue + 1) + minValue);
    }
}
