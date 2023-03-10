/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import Test.SendEmail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modeles.Livraison;
import modeles.Role;
import modeles.User;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author mrram
 */
public class ItemController implements Initializable {

    ServiceUser sU = new ServiceUser();

    private Label Username;

    String test;
    User U;
    @FXML
    private Label prenom;
    @FXML
    private Label phone;
    @FXML
    private Label section;
    @FXML
    private Label score;
    @FXML
    private Label role;
    @FXML
    private Label nom;
    @FXML
    private Label mail;
    @FXML
    private Label cartbanq;
    @FXML
    private ImageView Img;
    @FXML
    private Label id;
    @FXML
    private AnchorPane mainAnchor;
    private Label SectionLabel;
    @FXML
    private Label LabelSection;
    @FXML
    private Label LabelScore;

    
    public Livraison livraison;
//    public ItemController(String haja) {
//        this.test = haja;
//    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Username.setText("ahmeddd");
        nom.setText(U.getNom());


        prenom.setText(U.getPrenom());
        phone.setText(String.valueOf(U.getPhone()));
//        role.setText(U.getRole().toString());
        mail.setText(U.getEmail());
        cartbanq.setText(U.getCarte_banq());

    }


    @FXML
    private void DeleteClicked(ActionEvent event) throws IOException {
        sU.delete(U);


        HomeController Close = new HomeController();
        Close.reload(event);

    }

    @FXML
    private void UpdateClicked(ActionEvent event) throws IOException {

          EditItemLivraisonController cont = new EditItemLivraisonController();
        
          Livraison l = new Livraison();
          l.id_user = U.getId();
        cont.livraison= l;
//        cont.update = true;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditItemLivraison.fxml"));
        loader.setController(cont);
//        mainAnchor = loader.load();

        Stage stage = new Stage();
        stage.setTitle(" Update Window");
        stage.setScene(new Scene(loader.load()));
        stage.show();
        HomeController Close = new HomeController();
    }

}
