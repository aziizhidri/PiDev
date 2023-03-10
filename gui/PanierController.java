/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Panier;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.PanierCRUD;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class PanierController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button afficher;
    @FXML
    private Button exit;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcommande;
    @FXML
    private TextField tfproduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterpanier(ActionEvent event) {
        PanierCRUD pp = new PanierCRUD();
        
        int id=Integer.parseInt(tfid.getText());
        
        int id_produit=Integer.parseInt(tfproduit.getText());
        int id_commande=Integer.parseInt(tfcommande.getText());
        Panier p =new Panier();
        
        p.setId(id);
        p.setId_produit(id_produit);
        p.setId_commande(id_commande);
        
        pp.ajouterpanier(p);
        
        
        tfid.clear();
        tfproduit.clear();
        tfcommande.clear();
    }

    @FXML
    private void modifierpanier(ActionEvent event) {
        
        PanierCRUD pp = new PanierCRUD();
        int id=Integer.parseInt(tfid.getText());
        int id_produit=Integer.parseInt(tfproduit.getText());
        int id_commande=Integer.parseInt(tfcommande.getText());
        Panier p =new Panier();
        p.setId(id);
        p.setId_produit(id_produit);
        p.setId_commande(id_commande);
        
        pp.modifierpanier(p);
        
        tfid.clear();
        tfproduit.clear();
        tfcommande.clear(); 
    }

    @FXML
    private void afficherpanier(ActionEvent event) {
        
        try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AfficherPanier.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 630, 400);
        Stage stage = new Stage();
        stage.setTitle("AfficherPanier");
        stage.setScene(scene);
        stage.show();
        
        ((Node)(event.getSource())).getScene().getWindow().hide();  
        } catch (IOException ex) {
        System.out.println(ex.getMessage());   
        }
    }

    @FXML
    private void exit(ActionEvent event) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Accueil.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 630, 400);
        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();
        
        ((Node)(event.getSource())).getScene().getWindow().hide();  
        } catch (IOException ex) {
        System.out.println(ex.getMessage());   
        }
    }

    
}
