/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import Case.cas;
import Fatw.fatwa;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import login.login;

/**
 * FXML Controller class
 *
 * @author fathi
 */
public class Screen8Controller implements Initializable {

    @FXML
    ComboBox transactioncombo;
    @FXML
    ComboBox classes;
    @FXML
    ComboBox hokmcombo;
    ComboBox TransactionComboBox;

    final ObservableList<String> Transaction = FXCollections.observableArrayList("الصرف", "الإئتمان", "الوديعة", "المضاربة", "المرابحة", "اﻹيجارة", "التورق", "السلم", "المساومة", "المشاركة", "الكفالة");
    final ObservableList<String> Hokm = FXCollections.observableArrayList("حرام", "حلال");
    ObservableList<String> classlist = FXCollections.observableArrayList();
    @FXML
    TextField action;
    @FXML
    TextField benifit;
    @FXML
    TextArea quren;
    @FXML
    TextArea suna;
    @FXML
    TextArea ijtihad;
    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    Button menu;
    @FXML
    ContextMenu contextmenu;
    @FXML
    Pane classpane;
    @FXML
    TextField textFieldclass;
    @FXML
    AnchorPane ach;
    boolean change = false;
    boolean boolAction = false;
    boolean boolHokmComobox = false;
    boolean boolbenifit = false;
    boolean boolTransaction = false;
    boolean boolijtihad = false;
    boolean boolquren = false;
    boolean boolsuna = false;
    boolean booltextFieldclass = false;
    boolean boolclasses = false;

    BooleanProperty inici = new SimpleBooleanProperty();
    ;
    cas ca = new cas();
    fatwa fatwa = new fatwa();
    List<String> classList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        classList = SuperClass.getAllCalss();
        classpane.setVisible(false);
        classes.setItems(classlist);
        transactioncombo.setItems(Transaction);
        hokmcombo.setItems(Hokm);
        classlist.addAll(classList);

        ca = ScreensFramework.ca;
        // inic.visibleProperty().bind(inici);

        fatwa = ScreensFramework.fatwa;

        textFieldclass.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (change) {
                    if (!newValue.equals("")) {

                        booltextFieldclass = true;

                    } else {

                        booltextFieldclass = false;
                    }
                }
            }

        });
        action.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {

                                boolAction = true;

                            } else {

                                boolAction = false;
                            }
                        }
                    }

                }
                );
        benifit.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {

                                boolbenifit = true;

                            } else {

                                boolbenifit = false;
                            }
                        }
                    }

                }
                );
        classes.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {

                if (change) {
                    if (classes.getSelectionModel().getSelectedItem().equals("")) {
                        boolclasses = false;
                    } else {
                        boolclasses = true;

                    }
                }

            }
        });
        hokmcombo.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {

                if (change) {
                    if (hokmcombo.getSelectionModel().getSelectedItem().equals("")) {
                        boolHokmComobox = false;
                    } else {
                        boolHokmComobox = true;

                    }
                }

            }
        });
        transactioncombo.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {

                if (change) {
                    if (transactioncombo.getSelectionModel().getSelectedItem().equals("")) {
                        boolTransaction = false;
                    } else {
                        boolTransaction = true;

                    }
                }

            }
        });
        ijtihad.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {

                                boolijtihad = true;

                            } else {
                                button2.setDisable(true);
                                boolijtihad = false;
                            }
                        }
                    }

                }
                );
        quren.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {

                                boolquren = true;

                            } else {
                                button2.setDisable(true);
                                boolquren = false;
                            }
                        }
                    }

                }
                );
        suna.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {

                                boolsuna = true;
                            } else {
                                button2.setDisable(true);
                                boolsuna = false;
                            }
                        }
                    }

                }
                );

        setFatwaCase();
    }

    void setFatwaCase() {
        action.setText(ca.getAction());
        benifit.setText(ca.getBenifit());
        hokmcombo.getSelectionModel().select(fatwa.getHokm());
        transactioncombo.getSelectionModel().select(ca.getBenifit());
        classes.getSelectionModel().select(ca.getTransaction());
        quren.setText(fatwa.getIjtihad());
        suna.setText(fatwa.getSuna());
        ijtihad.setText(fatwa.getIjtihad());

    }

    @FXML
    public void newClass(ActionEvent evt) {
        classpane.setVisible(true);
    }

    @FXML
    public void newClasss(ActionEvent evt) {
        try {

            if (booltextFieldclass) {
                String text = textFieldclass.getText();
                if (!text.equals("")) {
                    if (!classList.contains(text)) {
                        SuperClass.addSubClass(text);
                        classlist.add(text);
                        classpane.setVisible(false);
                    } else {
                        classpane.setVisible(false);
                    }
                }
            }
        } catch (Exception e) {

        }

    }

    @FXML
    public void saveChange(ActionEvent evt) {
        try {
            ProgressIndicator inic = new ProgressIndicator();
            ach.getChildren().add(inic);
            inic.setLayoutX(button2.getLayoutX());
            inic.setLayoutY(button2.getLayoutY() + 20);
            inici.set(true);
            savechange();
          
             inic.setVisible(false);
            inic.setDisable(true);

            ach.getChildren().remove(inic);
            inici.set(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savechange() {

        try { 
            if(checkChange()){
            String classch="";
            String transaction="";
            classch = (String)classes.getSelectionModel().getSelectedItem();
            String action = this.action.getText();
            String benifit = this.benifit.getText();
            transaction = (String) this.transactioncombo.getSelectionModel().getSelectedItem();

            String quren = this.quren.getText();
            String suna = this.suna.getText();
            String ijtihad = this.ijtihad.getText();
            String hokm = hokmcombo.getSelectionModel().getSelectedItem().toString();

            if (!(quren.equals("") || suna.equals("") || ijtihad.equals("") || hokm.equals(""))) {
                if (!(action.equals("") || benifit.equals("") || transaction.equals("") || classch.equals(""))) {

                    Updater.updateFatwa(new fatwa(fatwa.getId(), hokm, quren, suna, ijtihad));
                    Updater.updateCase(new cas(ca.getId(), action, classch, transaction));
                    ScreensFramework.refreshCase();
                }
            }
           
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void changeScene() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen1.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen2() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen.fxml");
        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen3() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen3.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen4() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen4.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen5() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen5.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen6() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen6.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToLogin() {
        try {
            login.setLogout();
            ScreensFramework.changeScene("login.fxml");
        } catch (IOException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkChange() {
        if ((boolclasses && boolAction && boolbenifit && boolHokmComobox && boolTransaction && boolijtihad && boolquren && boolsuna)) {

            return true;
        } else {

            return false;
        }

    }
}
