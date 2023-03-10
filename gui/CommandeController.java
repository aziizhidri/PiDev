/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commande;
import java.io.IOException;
import services.CommandeCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.AfficherCommandeController;


/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CommandeController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfprix;
    @FXML
    private Button ajouter;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Button exit;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button afficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutercommande(ActionEvent event) {
        
        CommandeCRUD cc = new CommandeCRUD();
        
        int id=Integer.parseInt(tfid.getText());
        
        Date date1 =new Date(datepicker.getValue().getYear(),datepicker.getValue().getMonthValue(),datepicker.getValue().getDayOfMonth());
        int prix=Integer.parseInt(tfprix.getText());
        Commande c =new Commande();
        
        c.setId(id);
        c.setDate(date1);
        c.setPrix(prix);
        
        cc.ajoutercommande(c);
        
        
        tfid.clear();
        datepicker.getEditor().clear();
        tfprix.clear();
    }


    @FXML
    private void modifiercommande(ActionEvent event) {
        
        
        CommandeCRUD cc = new CommandeCRUD();
        int id=Integer.parseInt(tfid.getText());
        Date date1 =new Date(datepicker.getValue().getYear(),datepicker.getValue().getMonthValue(),datepicker.getValue().getDayOfMonth());
        int prix=Integer.parseInt(tfprix.getText());
        Commande c =new Commande();
        c.setId(id);
        c.setDate(date1);
        c.setPrix(prix);
        cc.modifiercommande(c);
        tfid.clear();
        datepicker.getEditor().clear();
        tfprix.clear();
    }


    @FXML
    private void affichercommande(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AfficherCommande.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 630, 400);
        Stage stage = new Stage();
        stage.setTitle("AfficherCommande");
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
