/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import entities.Commande;
import entities.Panier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CommandeCRUD;
import services.PanierCRUD;
import utils.MyConnection;
 

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AfficherPanierController implements Initializable {

    @FXML
    private TableView<Panier> table1;
    @FXML
    private TableColumn<Panier, Integer> idtable;
    @FXML
    private TableColumn<Panier, Integer> produittable;
    @FXML
    private TableColumn<Panier, Integer> commandetable;
    @FXML
    private Button supprimer;
    @FXML
    private Button exit;

    ObservableList<Panier> data=FXCollections.observableArrayList();
    ObservableList<Panier> panier=FXCollections.observableArrayList();
    
    ObservableList<Panier> Panier ;
    
    PanierCRUD pp = new PanierCRUD();
    
    Panier pn = null ;
    @FXML
    private ImageView imgqr;
    @FXML
    private Button gen;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Panier> p = pp.recuperer();
            ObservableList<Panier> listPers = FXCollections.observableArrayList(p);// convertir list to ObservableList fiha iterato
            idtable.setCellValueFactory(new PropertyValueFactory<>("id"));

            produittable.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            commandetable.setCellValueFactory(new PropertyValueFactory<>("id_commande"));

            table1.setItems(listPers);
            
            
            try {
                Connection cnx = MyConnection.getInstance().getCnx();
                ResultSet rs = cnx.createStatement().executeQuery("select * from panier");

                while (rs.next()) {
                    
                    panier.add(new Panier(
                            
                            rs.getInt("id"),
                            rs.getInt("id_produit"),
                            rs.getInt("id_commande")
                            
                    ));

                }

            } catch (SQLException ex) {
                Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            table1.setItems(panier);

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    
        // TODO

    @FXML
    private void supprimerpanier(ActionEvent event) {
        pn = table1.getSelectionModel().getSelectedItem();
        PanierCRUD pp = new PanierCRUD();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            Supprimer(pn.getId());

        } else if (input == 1) {

        }
    }
    public void Supprimer(int id) {
        try {
            Connection cnx = MyConnection.getInstance().getCnx();
            String sql = "DELETE FROM panier WHERE id="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Panier Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    private void createQR(String qrText) throws WriterException {
        try {
            String path = "C:\\Users\\ahmed\\OneDrive\\Bureau\\qr_code.png";  
            BitMatrix matrix = new MultiFormatWriter().encode(qrText, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
            alert("QR Code Created");
            setQRImage(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setQRImage(String path) {
    try {
        FileInputStream stream = new FileInputStream(path);
        javafx.scene.image.Image image = new javafx.scene.image.Image(stream);
        imgqr.setImage(image);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}
    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    @FXML
    private void generateQr(ActionEvent event) throws WriterException {
        Panier selectedPanier = table1.getSelectionModel().getSelectedItem();
        if (selectedPanier != null) {
            String qrText = String.valueOf(selectedPanier.getId());
            createQR(qrText);
        } else {
            alert("Please select a row.");
        }
    }

    @FXML
    private void exit(ActionEvent event) {
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
    }    

    


