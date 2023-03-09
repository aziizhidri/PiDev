/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplacepidev;

import java.sql.Date;

/**
 *
 * @author Bedys
 */
public class produitData {
    
    private Integer id;
    private String libelle;
    private String categorie;
    private String disponibilite;
    private Double prix;
    private Integer qte;
    private Date date;
    private String image;
    
    public produitData (Integer id, String libelle, String categorie, String disponibilite, Double prix, String image, Integer qte, Date date){
        this.id = id;
        this.libelle = libelle;
        this.categorie = categorie;
        this.disponibilite = disponibilite;
        this.prix = prix;
        this.qte = qte;
        this.date = date;
        this.image = image;
    }
    
    public Integer getId(){
        return id;
    }
    public String getLibelle(){
        return libelle;
    }
    public String getCategorie(){
        return categorie;
    }
    public String getDisponibilite(){
        return disponibilite;
    }
    public Double getPrix(){
        return prix;
    }
    public String getImage(){
        return image;
    }
    public Integer getQte(){
        return qte;
    }
    public Date getDate(){
        return date;
    }
}
