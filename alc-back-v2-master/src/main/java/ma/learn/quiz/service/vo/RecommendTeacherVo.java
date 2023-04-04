package ma.learn.quiz.service.vo;

public class RecommendTeacherVo {
	private String id;
	private String nombrevote ;
	private String nom;
	private String prenom;
	private String Commentaire;
	private String telephone;
	private String login;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombrevote() {
		return nombrevote;
	}
	public void setNombrevote(String nombrevote) {
		this.nombrevote = nombrevote;
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
	public String getCommentaire() {
		return Commentaire;
	}
	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
}