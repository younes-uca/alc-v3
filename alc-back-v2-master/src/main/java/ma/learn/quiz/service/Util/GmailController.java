package ma.learn.quiz.service.Util;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.GmailScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/gmail")
public class GmailController {


    @GetMapping("/googlesignin")
    public void googleSignIn(HttpServletResponse response) throws Exception {
        this.generateFlow();
        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        String redirectUrl = url.setRedirectUri(CALLBACK_URL).setAccessType("offline").build();
        response.sendRedirect(redirectUrl);
    }


    @PostMapping("/oauth")
    public void saveAuthorizationCode(@RequestParam("code") String code) throws Exception {
        System.out.println(code);
        if (code != null) {
            saveToken(code);
        } else {
            throw new Exception("Code is null, try again");
        }
    }

    public void generateFlow() throws Exception {
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(gdSecretKeys.getInputStream()));
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, secrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(credentialsFolder.getFile())).build();
    }

    private void saveToken(String code) throws Exception {
        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(CALLBACK_URL).execute();
        flow.createAndStoreCredential(response, USER_IDENTIFIER_KEY);
    }


    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = new ArrayList<>(
            Arrays.asList(GmailScopes.MAIL_GOOGLE_COM,
                    GmailScopes.GMAIL_ADDONS_CURRENT_MESSAGE_ACTION,
                    GmailScopes.GMAIL_INSERT,
                    GmailScopes.GMAIL_COMPOSE,
                    GmailScopes.GMAIL_SEND,
                    GmailScopes.GMAIL_METADATA
            ));
    private static final String USER_IDENTIFIER_KEY = "16221812503-ldub53c0ogp8ce313fgtehule5sfo9jo.apps.googleusercontent.com";
    private GoogleAuthorizationCodeFlow flow;


    @Value("${gmail-google.oauth.callback.uri}")
    private String CALLBACK_URL;
    @Value("${gmail-google.secret.key.path}")
    private Resource gdSecretKeys;
    @Value("${gmail-google.credentials.folder.path}")
    private Resource credentialsFolder;
}
