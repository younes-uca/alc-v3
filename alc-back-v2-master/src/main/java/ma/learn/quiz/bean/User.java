package ma.learn.quiz.bean;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;

@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String username;
    protected String password;
    protected String nom;
    protected String prenom;
    protected String numero;
    protected String addresse;
    protected String country;
    protected String ville;
    protected int age;
    protected Date dateNaissance;
    protected String image;
    @Column(length = 512)
    private String token;
    protected String skype;
    protected boolean accountNonExpired=true;
    protected boolean credentialsNonExpired=true;
    protected boolean accountNonLocked=true;
    protected boolean enabled=true;
    @ManyToMany(fetch = EAGER)
    protected Collection<Role> authorities;
    protected String role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numero='" + numero + '\'' +
                ", addresse='" + addresse + '\'' +
                ", country='" + country + '\'' +
                ", ville='" + ville + '\'' +
                ", age=" + age +
                ", dateNaissance=" + dateNaissance +
                ", image='" + image + '\'' +
                ", token='" + token + '\'' +
                ", skype='" + skype + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", role='" + role + '\'' +
                '}';
    }

    public User() {
    }

    public User(Long id, String username, String password, String nom, String prenom, String numero,
                String addresse, Date dateNaissance,String country, String ville, int age, String image, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, boolean enabled, Collection<Role> authorities, String role , String skype) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.addresse = addresse;
        this.ville = ville;
        this.age = age;
        this.image = image;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.authorities = authorities;
        this.role = role;
        this.dateNaissance = dateNaissance;
        this.country = country;
        this.skype =skype;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<Role> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
