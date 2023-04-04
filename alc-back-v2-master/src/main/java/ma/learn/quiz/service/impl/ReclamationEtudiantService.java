package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.ReclamationEtudiant;
import ma.learn.quiz.bean.User;
import ma.learn.quiz.dao.ReclamationEtudiantDao;
import ma.learn.quiz.dao.TypeReclamationEtudiantDao;
import ma.learn.quiz.exception.NotAnImageFileException;
import ma.learn.quiz.service.Util.UtilString;
import ma.learn.quiz.service.vo.ReclamationEtudiantVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static ma.learn.quiz.filter.JwtConstant.*;
import static org.springframework.http.MediaType.*;

@Service

public class ReclamationEtudiantService extends AbstractService {
    @Autowired
    private ReclamationEtudiantDao reclamationEtudiantDao;
    @Autowired
    private TypeReclamationEtudiantDao typeReclamationEtudiantDao;
    @Autowired
    private AdminService adminService;

    @Autowired
    private ProfService profService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TypeReclamationEtudiantService typeReclamationEtudiantService;
    @Autowired
    public EntityManager entityManager;

    public ReclamationEtudiant findReclamationEtudiantById(Long id) {
        return reclamationEtudiantDao.findReclamationEtudiantById(id);
    }

    public ReclamationEtudiant saveReclamationEtudiant(ReclamationEtudiant reclamationEtudiant) throws Exception {
        Optional<User> user = userService.findById(reclamationEtudiant.getUser().getId());
        if (user.isPresent()) {
            reclamationEtudiant.setDateReclamation(new Date());
            reclamationEtudiant.setUser(user.get());
            reclamationEtudiant.setPostView(false);
            reclamationEtudiant.setReference(UtilString.generateStringNumber(6));
            return reclamationEtudiantDao.save(reclamationEtudiant);
        } else {
            throw new Exception("User not found !");
        }

    }

    public int reponseReclamationEtudiant(ReclamationEtudiant reclamationEtudiant1, Date dateTraitementforEtudiant) {
        ReclamationEtudiant reclamationEtudiant = reclamationEtudiantDao.findReclamationEtudiantById(reclamationEtudiant1.getId());
        if (reclamationEtudiant == null) {
            return -1;
        } else {
            reclamationEtudiant.setTraite(reclamationEtudiant1.getTraite());
            reclamationEtudiant.setDateTraitement(dateTraitementforEtudiant);
            reclamationEtudiant.setCommentaireTraiteur(reclamationEtudiant1.getCommentaireTraiteur());
            reclamationEtudiant.setDateReponse(new Date());

            reclamationEtudiantDao.save(reclamationEtudiant);
            return 1;
        }
    }

    public int viewReclamationEtudiant(Long idReclamationEtudiant1) {
        ReclamationEtudiant reclamationEtudiant = reclamationEtudiantDao.findReclamationEtudiantById(idReclamationEtudiant1);

        if (reclamationEtudiant == null) {
            return -1;
        } else {
            reclamationEtudiant.setPostView(true);
            reclamationEtudiantDao.save(reclamationEtudiant);
            return 1;
        }
    }

    public List<ReclamationEtudiant> findReclamationEtudiantByEtudiantId(Long id) {
        return reclamationEtudiantDao.findReclamationEtudiantByUserId(id);
    }

    public List<ReclamationEtudiant> findAll() {
        return reclamationEtudiantDao.findAll();
    }

    public ReclamationEtudiant findReclamationEtudiantByIdAndEtudiantId(Long id, Long idetudiant) {
        return reclamationEtudiantDao.findReclamationEtudiantByIdAndUserId(id, idetudiant);
    }

    public List<ReclamationEtudiant> findAllByCriteria(ReclamationEtudiantVo reclamationEtudiantVo) {
        String query = this.init("ReclamationEtudiant");
        if (reclamationEtudiantVo.getEtudiant() != null) {
            query += this.addCriteria("etudiant.nom", reclamationEtudiantVo.getEtudiant().getNom(), "LIKE");
        }
        if (reclamationEtudiantVo.getReference() != null) {
            query += this.addCriteria("reference", reclamationEtudiantVo.getReference(), "LIKE");
        }
        if (reclamationEtudiantVo.getDateReclamation() != null) {
            query += this.addCriteria("dateReclamation", reclamationEtudiantVo.getDateReclamation(), "LIKE");
        }
        if (reclamationEtudiantVo.getTraite() != null) {
            query += this.addCriteria("traite", reclamationEtudiantVo.getTraite());
        }

        System.out.println("query = " + query);
        return entityManager.createQuery(query).getResultList();
    }

    public List<ReclamationEtudiant> updateAll(List<ReclamationEtudiant> reclamationEtudiants) {
        for (ReclamationEtudiant r : reclamationEtudiants
        ) {
            if (r.getTraite() != true) {
                r.setTraite(true);
                r.setDateTraitement(new Date());
                this.reclamationEtudiantDao.save(r);
            }
        }
        return reclamationEtudiants;
    }

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

     public ReclamationEtudiant saveReclamationImage(ReclamationEtudiant reclamationEtudiant, MultipartFile img) throws IOException, NotAnImageFileException {

        if (img != null) {
            if (!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(img.getContentType())) {
                throw new NotAnImageFileException(img.getOriginalFilename() + NOT_AN_IMAGE_FILE);
            }
            Path userFolder = Paths.get(RECLAMATION_FOLDER + reclamationEtudiant.getId()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED + userFolder);
            }
            Files.deleteIfExists(Paths.get(userFolder + reclamationEtudiant.getId().toString() + DOT + JPG_EXTENSION));
            Files.copy(img.getInputStream(), userFolder.resolve(reclamationEtudiant.getId().toString() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            reclamationEtudiant.setImg(setProfileImageUrl(reclamationEtudiant.getId().toString()));
            ReclamationEtudiant r = this.reclamationEtudiantDao.save(reclamationEtudiant);
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + img.getOriginalFilename());
            return r;
        }
        return reclamationEtudiant;
    }

    private String setProfileImageUrl(String id) {
        System.out.println(id);
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(RECLAMATION_IMAGE_PATH + id +
                FORWARD_SLASH + id + DOT + JPG_EXTENSION).toUriString();
    }
}
