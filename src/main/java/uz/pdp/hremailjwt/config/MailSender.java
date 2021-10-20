package uz.pdp.hremailjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Component
public class MailSender {
    @Autowired
    JavaMailSender javaMailSender;


    public Boolean sendEmailToVerify(String sendingEmail, String emailCode) {
        try {


            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Ibrokhim");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Assalomu alaykum");

//            "<form action=\"/api/auth/verifyEmail?emailCode="+emailCode+"&email=" +sendingEmail+"\">\n" +
//                    "    <input type=\"text\" placeholder=\"type your password to login in platform\">\n" +
//                    "    <button>submit</button>\n" +
//                    "</form>"
            mailMessage.setText("<form action=\"http://localhost:8080/api/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "\">\n" +
                    "    <input type=\"text\" placeholder=\"type your password to login in platform\">\n" +
                    "    <button>submit</button>\n" +
                    "</form>");
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean sendEmailTaskCreated(String from,
                                        String taskName,
                                        String sendingEmail,
                                        String taskCode) {
        try {


            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Here new task for you " + taskName);

//            "<form action=\"/api/auth/verifyEmail?emailCode="+emailCode+"&email=" +sendingEmail+"\">\n" +
//                    "    <input type=\"text\" placeholder=\"type your password to login in platform\">\n" +
//                    "    <button>submit</button>\n" +
//                    "</form>"
            mailMessage.setText("http://localhost:8080/api/task/confirm?taskCode=" + taskCode);
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean sendEmailToTaskCreater(String from, String taskName, String sendingEmail, Timestamp deadLine){
        try {


            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("This task done "+taskName);

//            "<form action=\"/api/auth/verifyEmail?emailCode="+emailCode+"&email=" +sendingEmail+"\">\n" +
//                    "    <input type=\"text\" placeholder=\"type your password to login in platform\">\n" +
//                    "    <button>submit</button>\n" +
//                    "</form>"
            mailMessage.setText("Task dead line is = "+deadLine+"\n"+
                    "Done in "+new Date());
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
