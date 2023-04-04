package ma.learn.quiz.migration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DriveApiService {

    public DriveApiService() {
    }

    private static final String APP_NAME = "alc-project-drive-img";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String USER_IDENTIFIER_KEY = "526671645204-pefjgst2s07uo5k8v0srh75pc7i28e8p.apps.googleusercontent.com";
    private GoogleAuthorizationCodeFlow flow;

    @Value("${google.oauth.callback.uri}")
    private String CALLBACK_URL;

    @Value("${google.secret.key.path}")
    private org.springframework.core.io.Resource gdSecretKeys;


    @Value("${google.credentials.folder.path}")
    private Resource credentialsFolder;

    boolean isParcourFolderExist = false;
    boolean isSectionFolderExist = false;
    boolean isCoursFolderExist = false;

    File uploadedFolderForCours = new File();
    File uploadedFolderForParcours = new File();
    File uploadedFolderForSection = new File();

    public String createFile(String parcours, String cours, String section, String imgPath, String imgName) throws Exception {
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(gdSecretKeys.getInputStream()));
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, secrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(credentialsFolder.getFile())).build();


        Credential cred = this.flow.loadCredential(USER_IDENTIFIER_KEY);
        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("writer");

        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, cred).setApplicationName(APP_NAME).build();
        File file = new File();
        File folderSection = new File();
        File folderParcour = new File();
        File folderCours = new File();
        File uploadedFile = new File();
        boolean isImgExist = false;

        FileList sheckFile = drive.files().list().setFields("*").execute();
        for (File f : sheckFile.getFiles()
        ) {
            if (!isCoursFolderExist) {
                if (parcours.equals(f.getName())) {
                    isParcourFolderExist = true;
                    uploadedFolderForParcours = f;
                }
            }

            if (!isSectionFolderExist) {
                if (section.equals(f.getName())) {
                    isSectionFolderExist = true;
                    uploadedFolderForSection = f;
                }
            }
            if (!isCoursFolderExist) {

                if (cours.equals(f.getName())) {
                    isCoursFolderExist = true;
                    uploadedFolderForCours = f;
                }
            }

            if (imgName.equals(f.getName())) {
                isImgExist = true;
                uploadedFile = f;
            }
        }
        System.out.println("=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(parcours);
        System.out.println(section);
        System.out.println(cours);
        System.out.println(imgName);
        System.out.println("#########################################################################");
        System.out.println(isParcourFolderExist);
        System.out.println(isSectionFolderExist);
        System.out.println(isCoursFolderExist);
        System.out.println(isImgExist);
        if (!isParcourFolderExist) {
            folderParcour.setName(parcours);
            folderParcour.setMimeType("application/vnd.google-apps.folder");
            uploadedFolderForParcours = drive.files().create(folderParcour).setFields("id").execute();
            drive.permissions().create(uploadedFolderForParcours.getId(), permission).execute();

        }
        if (!isCoursFolderExist) {
            folderCours.setName(cours);
            folderCours.setMimeType("application/vnd.google-apps.folder");
            folderCours.setParents(Arrays.asList(uploadedFolderForParcours.getId()));
            uploadedFolderForCours = drive.files().create(folderCours).setFields("id").execute();
            drive.permissions().create(uploadedFolderForCours.getId(), permission).execute();

        }

        if (!isSectionFolderExist) {
            folderSection.setName(section);
            folderSection.setMimeType("application/vnd.google-apps.folder");
            folderSection.setParents(Arrays.asList(uploadedFolderForCours.getId()));
            uploadedFolderForSection = drive.files().create(folderSection).setFields("id").execute();
            drive.permissions().create(uploadedFolderForSection.getId(), permission).execute();
        }

        if (!isImgExist) {
            file.setName(imgName);
            file.setParents(Arrays.asList(uploadedFolderForSection.getId()));
            FileContent content = new FileContent("image/jpeg", new java.io.File(imgPath));
            uploadedFile = drive.files().create(file, content).setFields("id,thumbnailLink,webContentLink").execute();
            drive.permissions().create(uploadedFile.getId(), permission).execute();
        }

        System.out.println("==============================ID THUMBNAILLINK==============================================");
        System.out.println("File with name  " + imgName + " added to drive");
        System.out.println("============================================================================");
        return uploadedFile.getWebContentLink();
    }

}
