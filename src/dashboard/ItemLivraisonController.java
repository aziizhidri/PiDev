/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modeles.Livraison;

/**
 * FXML Controller class
 *
 * @author mrram
 */
public class ItemLivraisonController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ImageView Img;
    @FXML
    private Label role;
    @FXML
    private Label Dest;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label destinationTxt;
    @FXML
    private Label phone;
    @FXML
    private Label status;
    @FXML
    private Label destination;
    @FXML
    private Label prixTotalTzt;
    @FXML
    private Label methodeTxt;
    @FXML
    private Label id;

    public Livraison livraison;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setText(String.valueOf( livraison.getId()));
        nom.setText(String.valueOf( livraison.getId_user()));
        destinationTxt.setText(livraison.destination);
        prenom.setText(String.valueOf( livraison.getId()));
        phone.setText(String.valueOf( livraison.getId()));
        status.setText(String.valueOf( livraison.status));
        destination.setText(livraison.destination);
        prixTotalTzt.setText(String.valueOf( livraison.getPrixTotal()));
        methodeTxt.setText(livraison.methodePaiment);
    }    

    @FXML
    private void UpdateClicked(ActionEvent event) {
    }

    @FXML
    private void DeleteClicked(ActionEvent event) {
    }
    
}
