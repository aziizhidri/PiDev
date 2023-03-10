/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ahmed
 */
public class Panier {
    private int id ;
    private int id_produit ; 
    private int id_commande ;
    
    public Panier() {
        
    }

    public Panier(int id_produit, int id_commande) {
        this.id_produit = id_produit;
        this.id_commande = id_commande;
    }
    

    public Panier(int id, int id_produit, int id_commande) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_commande = id_commande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", id_produit=" + id_produit + ", id_commande=" + id_commande + '}';
    }
    
}
