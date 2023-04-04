package ma.learn.quiz.service.facade;

import ma.learn.quiz.bean.Role;

import java.util.Collection;

public interface RoleService {
    Role save(Role role);
    void save(Collection<Role> roles);
    Role findByAuthority(String authority);
}
