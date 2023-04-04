package ma.learn.quiz.rest.admin;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import freemarker.template.TemplateException;
import ma.learn.quiz.bean.Admin;
import ma.learn.quiz.migration.DataBaseMigration;
import ma.learn.quiz.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin/admin")
public class AdminAdminRest {
    @GetMapping("/numero/{numro}")
    public Admin findByNumero(@PathVariable String ref) {
        return adminService.findByNumero(ref);
    }

    @DeleteMapping("/numero/{numero}")
    public int deleteByNumero(@PathVariable String ref) {
        return adminService.deleteByNumero(ref);
    }


    @GetMapping("/")
    public List<Admin> findAll() {
        return adminService.findAll();
    }


    @PostMapping("/")
    public int save(@RequestBody Admin admin) throws MessagingException, IOException, TemplateException {
        System.out.println(admin.getNom());
        System.out.println(admin.getUsername());
        return adminService.save(admin);
    }

    @GetMapping("/login/{login}/password/{password}")
    public Object findByCritere(@PathVariable String login, @PathVariable String password) {
        return adminService.findByCritere(login, password);
    }

    @Autowired
    private DataBaseMigration dataBaseMigration;


    @GetMapping("/app")
    public String start() throws Exception {
        return dataBaseMigration.htmlimagetext();
    }


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


    private static final String APP_NAME = "alc-project-drive-img";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String USER_IDENTIFIER_KEY = "526671645204-pefjgst2s07uo5k8v0srh75pc7i28e8p.apps.googleusercontent.com";
    private GoogleAuthorizationCodeFlow flow;

    @Autowired
    private AdminService adminService;

    @Value("${google.oauth.callback.uri}")
    private String CALLBACK_URL;
    @Value("${google.secret.key.path}")
    private Resource gdSecretKeys;
    @Value("${google.credentials.folder.path}")
    private Resource credentialsFolder;
}
