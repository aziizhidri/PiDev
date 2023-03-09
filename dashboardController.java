/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplacepidev;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;




/**
 * FXML Controller class
 *
 * @author Bedys
 */

public class dashboardController implements Initializable{
    
    @FXML
    private AnchorPane addProduct_form;

    @FXML
    private Button ajoutProduit_ajoutBtn;

    @FXML
    private Button ajoutProduit_btn;

    @FXML
    private ComboBox<?> ajoutProduit_categorie;

    @FXML
    private TableColumn<produitData, String> ajoutProduit_col_produitCategorie;

    @FXML
    private TableColumn<produitData, String> ajoutProduit_col_produitDate;

    @FXML
    private TableColumn<produitData, String> ajoutProduit_col_produitDisponibilite;
    
    @FXML
    private TableView<produitData> ajoutProduit_tableView;
    
    @FXML
    private TableColumn<produitData, String> ajoutProduit_col_produitID;

    @FXML
    private TableColumn<produitData, String> ajoutProduit_col_produitNom;

    @FXML
    private TableColumn<produitData, String> ajoutProduit_col_produitPrix;

    @FXML
    private TableColumn<produitData, String> ajoutProduit_col_produitQte;

    @FXML
    private ComboBox<?> ajoutProduit_disponibilite;

    @FXML
    private ImageView ajoutProduit_imageView;

    @FXML
    private Button ajoutProduit_importBtn;

    @FXML
    private Button ajoutProduit_modifierBtn;

    @FXML
    private Button ajoutProduit_nettoyerBtn;

    @FXML
    private TextField ajoutProduit_prix;

    @FXML
    private TextField ajoutProduit_produitID;

    @FXML
    private TextField ajoutProduit_produitNom;

    @FXML
    private TextField ajoutProduit_qte;

    @FXML
    private TextField ajoutProduit_recherche;

    @FXML
    private Button ajoutProduit_supprimerBtn;

    @FXML
    private Button close;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane dashboard_peintureDisp;

    @FXML
    private AnchorPane dashboard_pinceauDisp;

    @FXML
    private AnchorPane dashboard_tableauDisp;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Image image;
    
    public void addProduitAdd(){
        
        String sql = "INSERT INTO produit (id, libelle, categorie, disponibilite, prix, qte, image, date)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        connect = database.connectDb();
        
        try{
            
            Alert alert;
            
            if(ajoutProduit_produitID.getText().isEmpty()
                    || ajoutProduit_produitNom.getText().isEmpty()
                    || ajoutProduit_qte.getText().isEmpty()
                    || ajoutProduit_prix.getText().isEmpty()
                    || ajoutProduit_categorie.getSelectionModel().getSelectedItem() == null
                    || ajoutProduit_disponibilite.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("veuillez remplir tous les champs vides");
                alert.showAndWait();
                
            }else{
                
                    String checkData = "SELECT id FROM produit WHERE id = '"
                            +ajoutProduit_produitID.getText()+"'";
                    
                    statement = connect.createStatement();
                    result = statement.executeQuery(checkData);
                    
                    if(result.next()){
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Message d'erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Le produit d'ID: "+ajoutProduit_produitID.getText()+ " existe déjà");
                        alert.showAndWait();
                    }else{
                        
                    
                
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, ajoutProduit_produitID.getText());
                    prepare.setString(2, ajoutProduit_produitNom.getText());
                    prepare.setString(6, ajoutProduit_qte.getText());
                    prepare.setString(5, ajoutProduit_prix.getText());
                    prepare.setString(3, (String)ajoutProduit_categorie.getSelectionModel().getSelectedItem());
                    prepare.setString(4, (String)ajoutProduit_disponibilite.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(8, String.valueOf(sqlDate));
            
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Message d'information");
                alert.setHeaderText(null);
                alert.setContentText("Produit Ajouté avec succès");
                alert.showAndWait();
                    
                    addProduitShowListData();
                    addProduitReset();
            }}
            
           
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void addProduitUpdate(){
        
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        
        String sql = "UPDATE produit SET libelle = '"
                +ajoutProduit_produitNom.getText()+"', categorie ='"
                +ajoutProduit_categorie.getSelectionModel().getSelectedItem()+"', disponibilite = '"
                +ajoutProduit_disponibilite.getSelectionModel().getSelectedItem()+"', prix = '"
                +ajoutProduit_prix.getText()+"', qte = '"
                +ajoutProduit_qte.getText()+"', image = '"+uri+"' WHERE id = '"
                +ajoutProduit_produitID.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            if(ajoutProduit_produitID.getText().isEmpty()
                    || ajoutProduit_produitNom.getText().isEmpty()
                    || ajoutProduit_qte.getText().isEmpty()
                    || ajoutProduit_prix.getText().isEmpty()
                    || ajoutProduit_categorie.getSelectionModel().getSelectedItem() == null
                    || ajoutProduit_disponibilite.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("veuillez remplir tous les champs vides");
                alert.showAndWait();
                
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Mettre a jour le produit d'ID:" + ajoutProduit_produitID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit mis a jour!");
                    alert.showAndWait();
                    
                    addProduitShowListData();
                    addProduitReset();
                                        
                }                                
            }            
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void addProduitDelete(){
        
        String sql = "DELETE FROM produit WHERE id = '"+ajoutProduit_produitID.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            if(ajoutProduit_produitID.getText().isEmpty()
                    || ajoutProduit_produitNom.getText().isEmpty()
                    || ajoutProduit_qte.getText().isEmpty()
                    || ajoutProduit_prix.getText().isEmpty()
                    || ajoutProduit_categorie.getSelectionModel().getSelectedItem() == null
                    || ajoutProduit_disponibilite.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("veuillez remplir tous les champs vides");
                alert.showAndWait();
                
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Supprimer le produit d'ID:" + ajoutProduit_produitID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit supprimé!");
                    alert.showAndWait();
                    
                    addProduitShowListData();
                    addProduitReset();
                                        
                }                                
            }       
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void addProduitReset(){
        ajoutProduit_produitID.setText("");
        ajoutProduit_produitNom.setText("");
        ajoutProduit_qte.setText("");
        ajoutProduit_prix.setText("");
        ajoutProduit_categorie.getSelectionModel().clearSelection();
        ajoutProduit_disponibilite.getSelectionModel().clearSelection();
        
        ajoutProduit_imageView.setImage(null);
        
        getData.path = "";
        
    }
    
    private String[] addProduitListC = {"Pinceau", "Peinture", "Tableau"};
    public void addProduitListCategorie(){
        List<String> listC = new ArrayList<>();
        
        for(String data: addProduitListC){
            listC.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listC);
        ajoutProduit_categorie.setItems(listData);
        
    }
    
    
        
    private String[] addProduitDisponibilite = {"Disponible", "Pas disponible"};
    public void addProduitDisponibilite(){
        List<String> listD = new ArrayList<>();
        
        for(String data: addProduitDisponibilite){
            listD.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listD);
        ajoutProduit_disponibilite.setItems(listData);
    } 
    
    public void addProduitImportImage(){
        
        
        FileChooser open = new FileChooser();
        open.setTitle("Import Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));
        
        File file = open.showOpenDialog(main_form.getScene().getWindow());
        
        if(file != null){
            image = new Image(file.toURI().toString(), 149, 166, false, true);
            
            ajoutProduit_imageView.setImage(image);
            
            getData.path = file.getAbsolutePath();
        }
        
    }
    
    public ObservableList<produitData> addProduitData(){
        
        String sql = "SELECT * FROM produit";
        
        ObservableList<produitData> listData = FXCollections.observableArrayList();
        
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            produitData prodData;
            
            while(result.next()){
                prodData = new produitData(result.getInt("id"),result.getString("libelle")
                        , result.getString("categorie"), result.getString("disponibilite")
                        , result.getDouble("prix"), result.getString("image")
                        , result.getInt("qte") , result.getDate("date"));
                
                listData.add(prodData);
            }
            
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }
    
    private ObservableList<produitData> addProduitList;
    
    public void addProduitShowListData(){
        addProduitList = addProduitData();
        
        ajoutProduit_col_produitID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ajoutProduit_col_produitNom.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        ajoutProduit_col_produitCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        ajoutProduit_col_produitDisponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        ajoutProduit_col_produitPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ajoutProduit_col_produitQte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        ajoutProduit_col_produitDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        ajoutProduit_tableView.setItems(addProduitList);
        
    }
    
    public void addProduitSearch(){
        
        FilteredList<produitData> filter = new FilteredList<>(addProduitList, e-> true);
        ajoutProduit_recherche.textProperty().addListener((Observable, oldValue, newValue) ->{
            
            filter.setPredicate(predicateProduitData ->{
            
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
                
                if(predicateProduitData.getId().toString().contains(searchKey)){
                return true;
            }else if(predicateProduitData.getLibelle().toLowerCase().contains(searchKey)){
                return true;
            }else if(predicateProduitData.getCategorie().toLowerCase().contains(searchKey)){
                return true;
            }else if(predicateProduitData.getDisponibilite().toLowerCase().contains(searchKey)){
                return true;
            }else if(predicateProduitData.getPrix().toString().contains(searchKey)){
                return true;
            }else if(predicateProduitData.getQte().toString().contains(searchKey)){
                return true;
            }else if(predicateProduitData.getDate().toString().contains(searchKey)){
                return true;
            }else return false;
                
                
                });
            
        });
        
        SortedList<produitData> sortList = new SortedList<>(filter);
               
        sortList.comparatorProperty().bind(ajoutProduit_tableView.comparatorProperty());
        ajoutProduit_tableView.setItems(sortList);
    }
    
    public void addProduitSelect(){
        
        produitData prodData =  ajoutProduit_tableView.getSelectionModel().getSelectedItem();
        int num = ajoutProduit_tableView.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < - 1){return;}
        
        ajoutProduit_produitID.setText(String.valueOf(prodData.getId()));
        ajoutProduit_produitNom.setText(prodData.getLibelle());
        ajoutProduit_qte.setText(String.valueOf(prodData.getQte()));
        ajoutProduit_prix.setText(String.valueOf(prodData.getPrix()));
        
             
        String uri = "file:" + prodData.getImage();
        
        image = new Image(uri, 149, 166, false, true);
        ajoutProduit_imageView.setImage(image);
        getData.path = prodData.getImage();
        
    }
    
    public void SwitchForm(ActionEvent event){
        
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            addProduct_form.setVisible(false);
            
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #ffee00, #805021);");
            ajoutProduit_btn.setStyle("-fx-background-color:transparent");
            
        }else if (event.getSource() == ajoutProduit_btn){
            dashboard_form.setVisible(false);
            addProduct_form.setVisible(true);
            
            ajoutProduit_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #ffee00, #805021);");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            
            addProduitShowListData();
            addProduitDisponibilite();
            addProduitListCategorie();
            addProduitSearch();
            
        }
        
    }
    
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void close(){
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addProduitShowListData();
        addProduitDisponibilite();
        addProduitListCategorie();
    }
}

  


 

