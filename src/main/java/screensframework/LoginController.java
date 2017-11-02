package screensframework;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import login.Authentification;
import login.login;

/**
 * FXML Controller class
 *
 * @author fathi
 */
public class LoginController implements Initializable {

    @FXML
    JFXPasswordField pass;
    @FXML
    JFXComboBox usertype;
    @FXML
    Button button1;
    @FXML
    Label incorrect;
    
    boolean boolcombo = false;
    boolean bollogin = false;
    ObservableList<String> type = FXCollections.observableArrayList("عادي", "مسؤل");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usertype.setItems(type);
        usertype.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (usertype.getSelectionModel().getSelectedIndex() == 0) {
                    pass.setDisable(true);

                } else {
                    pass.setDisable(false);
                }

                boolcombo = true;
            }
        });
    }
 
    @FXML
    public void checkUser(ActionEvent evt) {
      checkUser();
    }
 public void checkUser() {
        try {
            incorrect.setText("aaaaaaaaaaa");
            System.out.println("loogin");
            if (boolcombo) {
                if (usertype.getSelectionModel().getSelectedItem().equals("عادي")) {
                    ScreensFramework.changeScene("Screen1.fxml");
                    login.setLogout();
                } else {
                    if (!pass.equals("")) {
                        System.out.println("llllllllll");
                        Authentification.getAuthentification(pass.getText());
                        bollogin = Authentification.isAuthentificated();
                        System.out.println("llllllllll");
                        if (bollogin) {

                            System.out.println("yes" + bollogin);
                            ScreensFramework.changeScene("Screen1.fxml");
                        }else{
                            incorrect.setText("كلمة السر غير صحيحة");
                        }

                    }

                }
            }
        } catch (Exception e) {

        }
    }
}
