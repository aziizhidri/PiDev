package modeles;

public class Cour {
    int id;
    int id_enseignant;
    String titre;
    int prix;

    public Cour() {
    }

    public Cour(int id, int id_enseignant, String titre, int prix) {
        this.id = id;
        this.id_enseignant = id_enseignant;
        this.titre = titre;
        this.prix = prix;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", id_enseignant='" + getId_enseignant() + "'" +
            ", titre='" + getTitre() + "'" +
            ", prix='" + getPrix() + "'" +
            "}";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return this.prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_enseignant() {
        return this.id_enseignant;
    }

    public void setId_enseignant(int id_enseignant) {
        this.id_enseignant = id_enseignant;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}
