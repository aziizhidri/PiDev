/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.Commande;
import java.io.IOException;
import java.sql.Connection;
import services.CommandeCRUD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import static jdk.nashorn.tools.ShellFunctions.input;
import utils.MyConnection;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AfficherCommandeController implements Initializable {
    
    

     @FXML
    private TableView<Commande> table;
            
    @FXML
    private TableColumn<Commande, Integer> idtable;
    @FXML
    private TableColumn<Commande, Date> datetable;
    @FXML
    private TableColumn<Commande, Integer> prixtable;
    
    ObservableList<Commande> data=FXCollections.observableArrayList();
    ObservableList<Commande> commande=FXCollections.observableArrayList();
    
    CommandeCRUD cc = new CommandeCRUD();
    Commande cmd = null ;
    
    
    ObservableList<Commande> Commande ; 
    @FXML
    private Button supprimer;
    @FXML
    private Button exit;
    
   
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Commande> p = cc.recuperer();
            ObservableList<Commande> listPers = FXCollections.observableArrayList(p);// convertir list to ObservableList fiha iterato
            idtable.setCellValueFactory(new PropertyValueFactory<>("id"));

            datetable.setCellValueFactory(new PropertyValueFactory<>("date"));
            prixtable.setCellValueFactory(new PropertyValueFactory<>("prix"));

            table.setItems(listPers);
            try {
                Connection cnx = MyConnection.getInstance().getCnx();
                ResultSet rs = cnx.createStatement().executeQuery("select * from commande");

                while (rs.next()) {
                    
                    commande.add(new Commande(
                            
                            rs.getInt("id"),
                            rs.getDate("date"),
                            rs.getInt("prix")
                            
                    ));

                }

            } catch (SQLException ex) {
                Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            table.setItems(commande);

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void supprimercommande(ActionEvent event) {
        cmd = table.getSelectionModel().getSelectedItem();
        CommandeCRUD cc = new CommandeCRUD();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            Supprimer(cmd.getId());

        } else if (input == 1) {

        }
    }
    public void Supprimer(int id) {
        try {
            Connection cnx = MyConnection.getInstance().getCnx();
            String sql = "DELETE FROM commande WHERE id="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Commande Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    
    
    

    @FXML
    private void exit(ActionEvent event) {
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
    
}

