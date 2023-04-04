package ma.learn.quiz.service.impl;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.Admin;
import ma.learn.quiz.bean.Role;
import ma.learn.quiz.dao.AdminDao;
import ma.learn.quiz.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ma.learn.quiz.filter.RoleConstant.ROLE_ADMIN;

@Service
public class AdminService extends AbstractService {
    public Admin findByNumero(String ref) {
        return adminDao.findByNumero(ref);
    }

    public Admin findByUsername(String login) {
        return adminDao.findByUsername(login);
    }

    public int deleteByNumero(String ref) {
        return adminDao.deleteByNumero(ref);
    }

    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    public Admin findAdminById(Long id) {
        return adminDao.findAdminById(id);
    }

    public int save(Admin admin) throws MessagingException, IOException, TemplateException {
        if (findAdminById(admin.getId()) != null) {
            return -1;
        }
        if (findByUsername(admin.getUsername()) != null) {
            return -2;
        } else {
            String password = this.userService.generatePassword();
            admin.setPassword(password);
            admin.setAuthorities(Arrays.asList(new Role(ROLE_ADMIN)));
            admin.setRole(ROLE_ADMIN);
            //userService.save(admin);
            adminDao.save(admin);
            return 1;
        }

    }

    public Object findByCritere(String login, String password) {
        if (!containsMalicous(login)) {
            String query = "SELECT item FROM Admin item WHERE 1=1";
            query += addCriteria("username", login);
            query += addCriteria("password", password);
            return entityManager.createQuery(query).getSingleResult();
        } else return null;
    }


    @Autowired
    private AdminDao adminDao;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserService userService;
    @Autowired
    private EtudiantService etudiantService;
}
