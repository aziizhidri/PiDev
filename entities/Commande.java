/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ahmed
 */
public class Commande {

    private int id ;
    private Date date ;
    private int prix ;
    
    public Commande(){
        
    }
    public Commande(int id, int prix) {
        this.id = id;
        this.prix = prix;
    }
        
    public Commande(Date date, int prix) {
        this.date = date;
        this.prix = prix;
    }

    public Commande(int aInt, Date date, int aInt0) {
        this.id = aInt ;
        this.date = date ;
        this.prix = aInt0 ;
        
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date=" + date + ", prix=" + prix + '}';
    }
    

    
}
