/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Commande;
import java.time.LocalDate;
import services.CommandeCRUD;
import utils.MyConnection;

/**
 *
 * @author ahmed
 */
public class MainClass {
    public static void main(String[] args) {
        //MyConnection mc = new MyConnection();
        CommandeCRUD ccd = new CommandeCRUD();
        //Commande c2 = new Commande(Date, 0);
        //ccd.ajouterCommande2(c2);
        
        //System.out.println(ccd.afficherCommandes());
        
    }
    
}
