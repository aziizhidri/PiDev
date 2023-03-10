/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Panier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author ahmed
 */
public class PanierCRUD {
    Connection cnx2;
        public PanierCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
        
        
        public void ajouterpanier(Panier c) {
        try {
            String req = "INSERT INTO `panier`( `id_produit`, `id_commande`) VALUES ('"+c.getId_produit()+"','"+c.getId_commande()+"')";
            Statement ste = cnx2.createStatement();
            ste.executeUpdate(req);
            System.out.println("Panier ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Panier non ajouté");
                      }
 }
        public void modifierpanier(Panier c) {
        try {
            String req = "UPDATE `panier` SET `id_produit` = '" + c.getId_produit()+ "', `id_commande` = '" + c.getId_commande()+ "' WHERE `panier`.`id` = " + c.getId();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("panier updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void supprimerpanier(int id) {
        try {
            String req = "DELETE FROM `panier` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Panier deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public List<Panier> afficherpanier(){
        List<Panier> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM panier";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Panier p = new Panier();
                p.setId(rs.getInt(1));
                p.setId_produit(rs.getInt(2));
                p.setId_commande(rs.getInt(3));
                myList.add(p);
                
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
        public List<Panier> recuperer() throws SQLException {
        List<Panier> panier = new ArrayList<>();
        String s = "select * from panier";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Panier p = new Panier();
            p.setId(rs.getInt(1));
            p.setId_produit(rs.getInt(2));
            p.setId_commande(rs.getInt(3));

            panier.add(p);

        }
        System.out.println(panier);
        return panier;
    }
}

               
