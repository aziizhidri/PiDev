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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import modeles.Livraison;
import services.ServiceLivraison;

/**
 * FXML Controller class
 *
 * @author mrram
 */
public class EditItemLivraisonController implements Initializable {

    @FXML
    private ImageView Img;
    @FXML
    private Label role;
    @FXML
    private Label labelscore;
    @FXML
    private TextField id_userTxt;
    @FXML
    private TextField statusttxttx;
    @FXML
    private TextField prixtxt;
    @FXML
    private TextField destinationtxt;
    @FXML
    private TextField methodetxt;
    @FXML
    private TextField scoreTxtUp;
    @FXML
    private ComboBox<?> typeBox;
    
    public Livraison livraison = new Livraison();
    
    public ServiceLivraison sL;
    
    public boolean update = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(update){
            id_userTxt.setText(String.valueOf(livraison.id_user));
            statusttxttx.setText(String.valueOf( livraison.status));
            destinationtxt.setText(livraison.destination);
            methodetxt.setText(livraison.methodePaiment);
        }
        else{
            livraison = new Livraison();
        }
        // TODO
    }    

    @FXML
    private void confirmClicked(ActionEvent event) {

//        Livraison l = new Livraison();


        livraison.id_user = Integer.valueOf( id_userTxt.getText());
        livraison.status = Boolean.valueOf( statusttxttx.getText());
        livraison.destination= destinationtxt.getText();
        livraison.methodePaiment = methodetxt.getText();
        livraison.date = methodetxt.getText();
//        l. scoreTxtUp.getText();

        System.err.println("~~"+ livraison.toString());
        if(update){
            sL.update(livraison);
        }
        else
            sL.add(livraison);

    }
    
}
