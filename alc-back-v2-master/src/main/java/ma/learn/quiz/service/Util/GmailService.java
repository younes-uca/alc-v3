package ma.learn.quiz.service.Util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Service
public class GmailService {
    private static final String APPLICATION_NAME = "gmailService";

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String>  SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);


    private static final String USER_IDENTIFIER_KEY = "16221812503-ldub53c0ogp8ce313fgtehule5sfo9jo.apps.googleusercontent.com";
    private GoogleAuthorizationCodeFlow flow;



    @Value("${gmail-google.secret.key.path}")
    private Resource gdSecretKeys;
    @Value("${gmail-google.credentials.folder.path}")
    private  Resource credentialsFolder;



    public  Message sendEmail(
                              String bodyText,
                              String messageSubject,
                                    String toEmailAddress)
            throws MessagingException, IOException {
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(gdSecretKeys.getInputStream()));
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, secrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(credentialsFolder.getFile())).build();
        Credential cred = this.flow.loadCredential(USER_IDENTIFIER_KEY);
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, cred)
                .setApplicationName(APPLICATION_NAME)
                .build();

        Gmail.Users.GetProfile user =  service.users().getProfile(USER_IDENTIFIER_KEY);
        System.out.println("user id: " + user.getUserId());


        MimeMessage email = this.createEmailWithAttachment(toEmailAddress, messageSubject, bodyText);

        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.getEncoder().encodeToString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        try {
            // Create send message
            message = service.users().messages().send("me", message).execute();
            System.out.println("Message id: " + message.getId());
            System.out.println(message.toPrettyString());
            return message;
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to send message: " + e.getDetails());
            throw e;
        }
    }



    public  MimeMessage createEmailWithAttachment(String to, String subject, String bodyText) throws MessagingException, IOException {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to)); //
        email.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(bodyText, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        email.setContent(multipart,"text/html");

        return email;
    }


}
