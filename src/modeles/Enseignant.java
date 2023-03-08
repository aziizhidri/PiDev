package modeles;

import java.util.ArrayList;
import java.util.List;

public class Enseignant extends User {
    private String universite;
    private String section;
    // private int niveau[];
    private List<Cour> cours = new ArrayList<Cour>();

    public Enseignant() {
        super();
        this.role = Role.enseignant;
    }

    public Enseignant(int id, String nom, String prenom, int phone, String email, String pwd, String carte_banq,
            String universite, String section) {
        super(id, nom, prenom, phone, email, pwd, carte_banq);
        this.universite = universite;
        this.section = section;
        this.role = Role.enseignant;

    }

    public List<Cour> getCours() {
        return this.cours;
    }

    public void setCours(List<Cour> cours) {
        this.cours = cours;
    }

    public String getUniversite() {
        return this.universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
