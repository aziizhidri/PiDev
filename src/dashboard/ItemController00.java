/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mrram
 */
public class ItemController00 implements Initializable {

    @FXML
    private Label Username;

    String test;

//    public ItemController(String haja) {
//        this.test = haja;
//    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Username.setText("ahmeddd");
        Username.setText(test);

    }

    void initData(String u) {
        Username.setText(u);
    }

}
