package ma.learn.quiz.configuration;

import freemarker.core.ParseException;
import freemarker.template.*;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@AllArgsConstructor
public class EmailSenderService {
    private final Configuration configuration;

    @Async
    public void sentJavaMail(MailComponent mailComponent, String templateName) throws MessagingException, TemplateException, IOException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.titan.email");
        mailSender.setPort(465);
        mailSender.setUsername("noreply@engflexy.com");
        mailSender.setPassword("noreply27021985");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", true);
        mailSender.setJavaMailProperties(props);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "UTF-8");
        System.out.println("TO: " + mailComponent.getTo());
        mailMessage.setFrom(new InternetAddress("EngFlexy <noreply@engflexy.com>"));
        mailMessage.setTo(mailComponent.getTo());
        mailMessage.setSubject(mailComponent.getSubject());
        Template template = null;
        try {
            template = getTemplateByName(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String emailContent;
        if (templateName.equals(ConstantFileNames.CONFIRMATION_TEMPLATE_MAIL)) {
            emailContent = fillMailContent(mailComponent, template);
        } else {
            emailContent = fillMailContentForStudent(mailComponent, template);
        }
        mailMessage.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @Async
    public void sent(MailComponent mailComponent) throws MessagingException, IOException, TemplateException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.titan.email");
        mailSender.setPort(465);
        mailSender.setUsername("noreply@engflexy.com");
        mailSender.setPassword("noreply27021985");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", true);
        mailSender.setJavaMailProperties(props);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
        mailMessage.setTo(mailComponent.getTo());
        mailMessage.setFrom(new InternetAddress("EngFlexy <noreply@engflexy.com>"));
        mailMessage.setSubject(mailComponent.getSubject());
        mailMessage.setText(mailComponent.getContent(), true);
        mailSender.send(mimeMessage);
    }


    public Template getTemplateByName(String templateName)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {

        Template tempalte = configuration.getTemplate(templateName);
        return tempalte;
    }

    public String fillMailContent(MailComponent mailComponent, Template template)
            throws IOException, TemplateException {

        Map<String, Object> model = new HashMap<>();

        model.put("username", mailComponent.getUsername());
        model.put("password", mailComponent.getPassword());

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        StringBuffer content = new StringBuffer(html);

        return content.toString();
    }

    public String fillMailContentForStudent(MailComponent mailComponent, Template template)
            throws IOException, TemplateException {

        Map<String, Object> model = new HashMap<>();

        model.put("link", mailComponent.getLink());

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        StringBuffer content = new StringBuffer(html);

        return content.toString();
    }


}
