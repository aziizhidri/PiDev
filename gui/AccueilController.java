/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AccueilController implements Initializable {

    @FXML
    private Button commande;
    @FXML
    private Button panier;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void opencommande(ActionEvent event) {
            try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Commande.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 630, 400);
        Stage stage = new Stage();
        stage.setTitle("Commande");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();  
    } catch (IOException ex) {
        System.out.println(ex.getMessage());   
    }

}

    @FXML
    private void openpanier(ActionEvent event) {
        
        try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Panier.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 630, 400);
        Stage stage = new Stage();
        stage.setTitle("Panier");
        stage.setScene(scene);
        stage.show();
        
        ((Node)(event.getSource())).getScene().getWindow().hide();  
        } catch (IOException ex) {
        System.out.println(ex.getMessage());   
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        
        Stage stage =(Stage) exit.getScene().getWindow();
        stage.close();
    }
    
}
