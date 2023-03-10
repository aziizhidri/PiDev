/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyConnection;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ahmed
 */
public class CommandeCRUD {
    
    Connection cnx2;
    public CommandeCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterCommande2(Commande c){
        try {
            String requete2 ="INSERT INTO commande (date,prix)"
                    + "VALUES (?,?)";
            PreparedStatement cst = cnx2.prepareStatement(requete2);
            //cst.setInt(1, c.getId());
            cst.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
            cst.setInt(2,c.getPrix());
            cst.executeUpdate();
            System.out.println("Commande ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
        public void ajoutercommande(Commande c) {
        try {
            String req = "INSERT INTO `commande`( `date`, `prix`) VALUES ('"+c.getDate()+"','"+c.getPrix()+"')";
            Statement ste = cnx2.createStatement();
            ste.executeUpdate(req);
            System.out.println("Commande ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Commande non ajouté");
                      }
 }
    
    
        public void modifiercommande(Commande c) {
        try {
            String req = "UPDATE `commande` SET `date` = '" + c.getDate()+ "', `prix` = '" + c.getPrix()+ "' WHERE `commande`.`id` = " + c.getId();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
            public void supprimercommande(int id) {
        try {
            String req = "DELETE FROM `commande` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            public boolean supprimer(Commande t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx2.prepareStatement("delete from commande where id = ? ");
                  req.setInt(1, t.getId());

            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }

    
    public List<Commande> affichercommandes(){
        List<Commande> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM commande";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Commande c = new Commande();
                c.setId(rs.getInt(1));
                c.setDate(rs.getDate(2));
                c.setPrix(rs.getInt(3));
                myList.add(c);
                
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }       
    
    
    public ObservableList<Commande> listercommande() {
        ObservableList<Commande> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM commande";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Commande c = new Commande();
                c.setId(rs.getInt(1));
                c.setDate(rs.getDate(2));
                c.setPrix(rs.getInt(3));
                myList.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
}
    public List<Commande> recuperer() throws SQLException {
        List<Commande> commande = new ArrayList<>();
        String s = "select * from commande";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Commande c = new Commande();
            c.setId(rs.getInt(1));
            c.setDate(rs.getDate(2));
            c.setPrix(rs.getInt(3));
            


            commande.add(c);

        }
        System.out.println(commande);
        return commande;
    }
}
