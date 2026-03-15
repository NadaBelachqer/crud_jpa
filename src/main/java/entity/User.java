package entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String genre;

    @Temporal(TemporalType.DATE)
    private Date datebirth;

    private String email;
    private String motdepasse;

    private Double bourse;
    private String currency;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Date getDatebirth() { return datebirth; }
    public void setDatebirth(Date datebirth) { this.datebirth = datebirth; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotdepasse() { return motdepasse; }
    public void setMotdepasse(String motdepasse) { this.motdepasse = motdepasse; }

    public Double getBourse() { return bourse; }
    public void setBourse(Double bourse) { this.bourse = bourse; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}