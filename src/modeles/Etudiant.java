/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

/**
 *
 * @author mrram
 */
public class Etudiant extends User {

    private String section;
    private int niveau;
    private int score;

    public Etudiant() {
        super();
        this.role = Role.etudiant;

    }

    public Etudiant(int id, String nom, String prenom, int phone, String email, String pwd, String carte_banq,
            String section, int niveau, int score) {
        super(id, nom, prenom, phone, email, pwd, carte_banq);
        this.section = section;
        this.niveau = niveau;
        this.score = score;
        this.role = Role.etudiant;
    }

    public Etudiant(String nom, String prenom, int phone, String email, String pwd, String carte_banq,
            String section, int niveau, int score) {
        super(nom, prenom, phone, email, pwd, carte_banq);
        this.section = section;
        this.niveau = niveau;
        this.score = score;
        this.role = Role.etudiant;
    }

    @Override
    public String toString() {

        return "{" + super.toString()
                + " section='" + getSection() + "'"
                + ", niveau='" + getNiveau() + "'"
                + ", score='" + getScore() + "'"
                + "}";
    }

    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getNiveau() {
        return this.niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
