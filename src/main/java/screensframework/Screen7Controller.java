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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javassist.compiler.TokenId;
import static screensframework.Screen6Controller.actionindividual;
import static screensframework.ScreensFramework.actionInstance;

/**
 * FXML Controller class
 *
 * @author fathi
 */
public class Screen7Controller implements Initializable {
    
    @FXML
    ComboBox transactioncombo;
    @FXML
    ComboBox ActionSyn;
    @FXML
    ComboBox classes;
    @FXML
    ComboBox hokmcombo;
    final ObservableList<String> Transaction = FXCollections.observableArrayList("الصرف", "الإئتمان", "الوديعة", "المضاربة", "المرابحة", "اﻹيجارة", "التورق", "السلم", "المساومة", "المشاركة", "الكفالة","البيع بالتقسيط");
    final ObservableList<String> Hokm = FXCollections.observableArrayList("حرام", "حلال");
    final ObservableList<String> ActionSynList = FXCollections.observableArrayList();
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
    
    static List<String> actionindividual = new ArrayList<>();
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
    boolean boolActionSyn = false;
    @FXML
    Pane classpane;
    @FXML
    TextField textFieldclass;
    List<cas> cslk = new ArrayList<>();
    Connection connect;
    PreparedStatement ps;
    
    public Screen7Controller() throws ClassNotFoundException {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        classpane.setVisible(false);
        hokmcombo.setItems(Hokm);
        transactioncombo.setItems(Transaction);
        classes.setItems(classlist);
        classlist.addAll(SuperClass.getAllCalss());
        button2.setDisable(true);
        
        ActionSyn.setItems(ActionSynList);
        ActionSynList.addAll(actionInstance);
        ActionSynList.add("غير موجود");
        
        ActionSyn.setOnAction(new EventHandler() {
            
            @Override
            public void handle(Event event) {
                if (change) {
                    if (ActionSyn.getSelectionModel().getSelectedItem().equals("")) {
                        boolclasses = false;
                        
                    } else {
                        boolActionSyn = true;
                        if (checkChange()) {
                            button2.setDisable(false);
                        } else {
                            button2.setDisable(true);
                        }
                    }
                }
            }
        });
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
                                
                                if (checkChange()) {
                                    button2.setDisable(false);
                                } else {
                                    button2.setDisable(true);
                                }
                                
                            } else {
                                button2.setDisable(true);
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
                                
                                if (checkChange()) {
                                    button2.setDisable(false);
                                } else {
                                    button2.setDisable(true);
                                }
                                
                            } else {
                                button2.setDisable(true);
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
                        if (checkChange()) {
                            button2.setDisable(false);
                        } else {
                            button2.setDisable(true);
                        }
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
                        if (checkChange()) {
                            button2.setDisable(false);
                        } else {
                            button2.setDisable(true);
                        }
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
                        if (checkChange()) {
                            button2.setDisable(false);
                        } else {
                            button2.setDisable(true);
                        }
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
                                
                                if (checkChange()) {
                                    button2.setDisable(false);
                                } else {
                                    button2.setDisable(true);
                                }
                                
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
                                
                                if (checkChange()) {
                                    button2.setDisable(false);
                                } else {
                                    button2.setDisable(true);
                                }
                                
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
                                
                                if (checkChange()) {
                                    button2.setDisable(false);
                                } else {
                                    button2.setDisable(true);
                                }
                                
                            } else {
                                button2.setDisable(true);
                                boolsuna = false;
                            }
                        }
                    }
                    
                }
                );
        
        change = true;
    }
    
    public void save() {
        String sql = "";
        int index = 0;
        try {
            this.connect = new Connection();
        } catch (Exception ex) {
            Logger.getLogger(Screen7Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (checkChange()) {
                
                String classch = classes.getSelectionModel().getSelectedItem().toString();
                String action = this.action.getText();
                String benifit = this.benifit.getText();
                String transaction = this.transactioncombo.getSelectionModel().getSelectedItem().toString();
                
                String quren = this.quren.getText();
                String suna = this.suna.getText();
                String ijtihad = this.ijtihad.getText();
                boolean findcase = false;
                boolean havesyn = false;
                String hokm = hokmcombo.getSelectionModel().getSelectedItem().toString();
                if (!(action.equals("") || benifit.equals("") || transaction.equals("") || classch.equals(""))) {
                    for (cas c : ScreensFramework.cases) {
                        if (transaction.equals(c.getTransaction()) && classch.equals(c.getBenifit())) {
                            
                            cslk.add(c);
                        }
                    }
                    if (!cslk.isEmpty()) {
                        if ((ActionSyn.getSelectionModel().getSelectedItem().equals("غير موجود") || ActionSyn.getSelectionModel().getSelectedItem().equals(""))) {
                            actionindividual = IndividualList.getSameAsIndividuals(action);
                            if (!actionindividual.isEmpty()) {
                                havesyn = true;
                                for (cas c : cslk) {
                                    for (String act : actionindividual) {
                                        if (c.getAction().equals(act)) {                                            
                                            findcase = true;
                                        }
                                    }
                                }
                            }
                            
                        } else {
                            actionindividual = IndividualList.getSameAsIndividuals(action);
                            if (!actionindividual.isEmpty()) {
                                havesyn = true;
                                for (cas c : cslk) {
                                    for (String act : actionindividual) {
                                        if (c.getAction().equals(act)) {
                                            findcase = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!findcase) {
                    
                    if (!(quren.equals("") || suna.equals("") || ijtihad.equals("") || hokm.equals(""))) {
                        if (!(action.equals("") || benifit.equals("") || transaction.equals("") || classch.equals(""))) {
                            
                            Insert.insert(new cas(action, classch, transaction), new fatwa(hokm, quren, suna, ijtihad));

                        //AddFatwa addfatwa = new AddFatwa();
                     /*   sql = "insert into fatwa ( id, hokm ,quren ,suna , ijtihad) values ( NULL ,?,?,?,?)";
                             ps = connect.update(sql);
                             ps.setString(1, hokm);
                             ps.setString(2, quren);
                             ps.setString(3, suna);
                             ps.setString(4, ijtihad);
                             ps.executeUpdate();
                             try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                             if (generatedKeys.next()) {

                             index = (int) generatedKeys.getLong(1);
                             System.out.println("bbbbbbbbbbbbbbbbb" + index);
                             } else {
                             throw new SQLException("Creating user failed, no ID obtained.");
                             }
                             }
                             ps.close();*/
                            //index = addfatwa.addFatwa(hokm, quren, suna, ijtihad);
                            String classz = null;
                            boolean find1 = SuperClass.isIndividual(benifit);
                            if (find1) {
                                classz = SuperClass.getSubClass(benifit);
                            } else {
                                SuperClass.createIndividual(classch, benifit);
                                classz = classch;
                            }
                            if (!havesyn) {
                                find1 = false;
                                for (String s : actionInstance) {
                                    if (s.equals(action)) {                                        
                                        find1 = true;
                                        break;
                                    }
                                }
                                if (!find1) {                                    
                                    String act = ActionSyn.getSelectionModel().getSelectedItem().toString();
                                    if (!(act.equals("")||act.equals("غير موجو"))) {
                                        SuperClass.addAction(action, ActionSyn.getSelectionModel().getSelectedItem().toString());
                                    } else {
                                        SuperClass.addAction(action, "");
                                    }
                                }
                            }
                        }
                    }
                    
                    ScreensFramework.refreshCase();
                }
            }
        } catch (Exception e) {
            System.err.println("aaaaaaaaaaa" + e);
        }
        
    }
    
    public void isCaseExist(String Action) {
        
    }
    
    @FXML
    public void newClass(ActionEvent evt) {
        classpane.setVisible(true);
    }
    
    @FXML
    public void newClasss(ActionEvent evt) {
        if (booltextFieldclass) {
            String text = textFieldclass.getText();
            if (!text.equals("")) {
                SuperClass.addSubClass(text);
                classlist.add(text);
                classpane.setVisible(false);
            }
        }
    }
    
    @FXML
    public void saveFatwa() {
        this.save();
        ScreensFramework.refreshCase();
    }
    
    public boolean checkChange() {
        if ((boolclasses && boolAction && boolbenifit && boolHokmComobox && boolTransaction && boolijtihad && boolquren && boolsuna&&boolActionSyn)) {
            
            return true;
        } else {
            
            return false;
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
    
    public void gotToSceen6() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen6.fxml");
            
        } catch (IOException ex) {
        }
    }
    
    public void gotToSceen7() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen7.fxml");
            
        } catch (IOException ex) {
        }
    }
}
