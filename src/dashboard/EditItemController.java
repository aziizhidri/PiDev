/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

//import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.U;
import javafx.stage.Stage;
import modeles.Enseignant;
import modeles.Etudiant;
import modeles.Recruteur;
import modeles.Role;
import modeles.User;
import services.ServiceEns;
import services.ServiceEtudiant;
import services.ServiceRecruteur;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author mrram
 */
public class EditItemController implements Initializable {

    @FXML
    private Label role;
    @FXML
    private Label labelsection;
    @FXML
    private Label labelscore;
    User u;
    @FXML
    private TextField nom;
    @FXML
    private TextField phone;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField carteBancaire;
    @FXML
    private TextField section;
    @FXML
    private TextField score;
    @FXML
    private ImageView Img;
    /**
     * Initializes the controller class.
     */
    @FXML
    // private ImageView img;
    ServiceUser sU = new ServiceUser();
    ServiceEtudiant sE = new ServiceEtudiant();
    ServiceEns sEn = new ServiceEns();
    ServiceRecruteur sR = new ServiceRecruteur();
    @FXML
    private TextField psw;
    ObservableList types = FXCollections.observableArrayList(
            "Apple", "Banana", "Pear", "Strawberry", "Peach", "Orange", "Plum");
//    private JFXComboBox<String> typeUser = new JFXComboBox<String>(types);
    int type = 0;
    @FXML
//    private JFXComboBox<?> type2;
//    @FXML
//    private ComboBox<?> typeBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (type == 1) {
            role.setText("Add");
        }
        if (type == 2) {
            nom.setText(u.getNom());
            phone.setText(String.valueOf(u.getPhone()));
            prenom.setText(u.getPrenom());
            email.setText(u.getEmail());
            carteBancaire.setText(u.getCarte_banq());
            psw.setText(u.getPwd());
            // section.setText(u.get);
            // score.setText(u.get);
        }
        ObservableList types = FXCollections.observableArrayList("Etudiant", "Enseignant", "Recruteur", "Admin");
//        typeBox.getItems().addAll(types);
        // typeUser.setItems(types);
//        typeBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            // System.out.println(newValue);
//            Role role = Role.empty;
//            role = role.value(newValue.toString());
////            System.out.println("role = " + role.toString());
//            if (role == Role.etudiant) {
//                labelsection.setText("Section");
//                labelscore.setText("Score");
//                
//                labelscore.setVisible(true);
//                score.setVisible(true);
//
////                labelscore.setText("Score");
////                labelscore.setText("Score");
//                // Etudiant e = (Etudiant) u;
//                // section.setText(e.getSection());
//                // score.setText(String.valueOf(e.getScore()));
//                // File file = new File("src/dashboard/images/student.jpg");
//                // Img.setImage(new Image(file.toURI().toString()));
//            }
//            if (role == Role.Recruteur) {
//                Recruteur r = (Recruteur) u;
//                File file = new File("src/dashboard/images/recruitment.png");
//                Img.setImage(new Image(file.toURI().toString()));
//                labelsection.setText("Societe");
//                
//                labelscore.setVisible(false);
//                score.setVisible(false);
//
//            }
//            if (role == Role.enseignant) {
//                Enseignant e = (Enseignant) u;
//                File file = new File("src/dashboard/images/teacher.png");
//                Img.setImage(new Image(file.toURI().toString()));
//                
//                labelscore.setText("Universite");
//                
//                labelscore.setVisible(false);
//                score.setVisible(false);
//                
//                // score.setText(e.getUniversite());
//            }
//            if (role == Role.admin) {
//                File file = new File("src/dashboard/images/admin.png");
//                Img.setImage(new Image(file.toURI().toString()));
//                
//                labelscore.setVisible(false);
//                score.setVisible(false);
//            }
//            if (role == Role.empty) {
//                
//                labelscore.setVisible(false);
//                labelsection.setVisible(false);
//
//                section.setText("");
//                score.setText("");
//            }

//        });

        // ***
        // typeBox.valueProperty().addListener(new ChangeListener<String>() {
    }

    @FXML
    private void confirmClicked(ActionEvent event) throws IOException {
        User x = new User();
        x.setNom(nom.getText());
        x.setPrenom(prenom.getText());
        x.setPhone(Integer.parseInt(phone.getText()));
        x.setEmail(email.getText());
        x.setCarte_banq(carteBancaire.getText());
        x.setPwd(psw.getText());
        // System.out.println(x);
        if (type == 1) {
            sU.add(x);
        }
        if (type == 2) {
            x.setId(u.getId());
            sU.update(x);
        }
        // HomeController Close = new HomeController();
        // Close.reload(event);
        // //LOGOUT ***
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
