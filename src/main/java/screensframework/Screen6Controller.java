/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import Case.cas;
import Fatw.GetFatwa;
import Fatw.fatwa;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodRequests;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import login.login;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import static screensframework.ScreensFramework.actionInstance;

/**
 * FXML Controller class
 *
 * @author fathi
 */
public class Screen6Controller implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    ComboBox QuestionComboBox;
    @FXML
    ComboBox ActionComboBox;
    @FXML
    ComboBox GoodsComboBox;
    @FXML
    ComboBox TransactionComboBox;
    @FXML
    Button button1;
    @FXML
    Button button2;

    Button addFatwa;

    @FXML
    TableView<cas> tableView;
    @FXML
    private TableColumn<cas, String> action;
    @FXML
    private TableColumn<cas, String> benifit;
    @FXML
    private TableColumn<cas, String> transaction;
    private ObservableList<cas> data;

    static List<String> actionindividual = new ArrayList<>();
    static List<String> benifitindividual = new ArrayList<>();

    final ObservableList<String> Question = FXCollections.observableArrayList("هل يجوز", "ما حكم", "ما حد");
    final ObservableList<String> Action = FXCollections.observableArrayList("شراء", "بيع", "إستأجار", "أقتراض");
    final ObservableList<String> Goods = FXCollections.observableArrayList("قطعة أرض", "منزل", "سيارة", "محل", "مال");
    final ObservableList<String> Transaction = FXCollections.observableArrayList("الصرف", "الإئتمان", "الوديعة", "المضاربة", "المرابحة", "اﻹيجارة", "التورق", "السلم", "المساومة", "المشاركة", "الكفالة","البيع_بالتقسيط");
    boolean change = false;
    boolean boolAction = false;
    boolean boolQuestionComobox = false;
    boolean boolGoodsCombox = false;
    boolean boolTransaction = false;
    boolean findaction = false;
    boolean findbenifit = false;
    boolean findtransac = false;
    boolean find = false;
    int index;
    //List<Case> cases;
    cas c = new cas();

    @FXML
    private Pane analyse;
    @FXML
    Pane caseLike;
    @FXML
    Pane evidence;
    @FXML
    Pane general;
    @FXML
    Label hokme;
    @FXML
    TextArea quren;
    @FXML
    TextArea sunah;
    @FXML
    TextArea ijtihad;

    @FXML
    Button menu;
    @FXML
    ContextMenu contextmenu;

    fatwa ftw;
    List<cas> cslk = new ArrayList<>();

    @FXML
    AnchorPane p;
    @FXML
    MenuItem button3;

    Label label = new Label();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setPrefSize(100, 50);
        label.setLayoutX(300);
        label.setLayoutY(2);
        boolean admin = false;
        tableView.setOnScrollFinished(new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                System.out.println("scroll finish");
            }

        });
        admin = login.isLogin();
        if (!admin) {
            button3.setDisable(true);
            button2.setDisable(true);

        }
        //label.getStylesheets().add("/screensframework/mycss.css");
        //label.getStyleClass().add("mylabel");
        label.setText("الحالات المشابهة");

        caseLike.setVisible(false);
        evidence.setVisible(false);
        analyse.setVisible(false);
        general.setVisible(false);
        QuestionComboBox.setItems(Question);
        ActionComboBox.setItems(Action);
        GoodsComboBox.setItems(Goods);
        TransactionComboBox.setItems(Transaction);

        //button1.setDisable(true);
        //button2.setDisable(true);
        //AutoCompleteComboBoxListener< Object> autoCompleteComboBoxListener = new AutoCompleteComboBoxListener<>(ActionComboBox);
        //AutoCompleteComboBoxListener< Object> autoCompleteComboBoxListener1 = new AutoCompleteComboBoxListener<>(GoodsComboBox);
        //AutoCompleteComboBoxListener< Object> autoCompleteComboBoxListener2 = new AutoCompleteComboBoxListener<>(TransactionComboBox);
        QuestionComboBox.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {

                if (change) {
                    if (QuestionComboBox.getSelectionModel().getSelectedItem().equals("")) {
                        boolQuestionComobox = false;
                        button1.setDisable(true);
                    } else {

                        boolQuestionComobox = true;
                        if (checkChange()) {
                            System.out.println("aaa" + checkChange());

                        } else {

                        }
                    }
                }

            }
        });
        ActionComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                System.out.println("123");
                if (change) {
                    if (ActionComboBox.getSelectionModel().getSelectedItem().equals("")) {
                        boolAction = false;
                        button1.setDisable(true);
                    } else {
                        boolAction = true;

                        if (checkChange()) {
                            // button1.setDisable(false);
                        } else {
                            // button1.setDisable(true);
                        }
                    }
                }
            }
        }
        );
        
        GoodsComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (change) {
                    if (GoodsComboBox.getSelectionModel().getSelectedItem().equals("")) {
                        boolGoodsCombox = false;
                        button1.setDisable(true);
                    } else {
                        boolGoodsCombox = true;

                        if (checkChange()) {
                            // button1.setDisable(false);
                        } else {
                            // button1.setDisable(true);
                        }

                    }
                }
            }
        });

        TransactionComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (change) {
                    if (TransactionComboBox.getSelectionModel().getSelectedItem().equals("")) {
                        boolTransaction = false;
                        button1.setDisable(true);

                    } else {
                        boolTransaction = true;

                    }
                }
            }
        });
        //getCase();

        change = true;
    }

    @FXML
    public void getCaseElements(ActionEvent actionEvent) {

        try {

            tableView.setItems(null);
            tableView.setPlaceholder(new Label("لا يوجد حالات مشابهة"));
            getCaseElement();
        } catch (Exception ex) {
            Logger.getLogger(Screen6Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCaseElement() {
        String action = null;
        String benifit = null;
        String transaction = null;
        boolean test = false;
        List<cas> cases = new ArrayList<>();

        init();
        checkField();
        //if(ScreensFramework.cases.)
        if (boolQuestionComobox && boolTransaction) {
            System.out.println("1");
            try {
                action = ActionComboBox.getSelectionModel().getSelectedItem().toString();
                benifit = GoodsComboBox.getSelectionModel().getSelectedItem().toString();
                transaction = TransactionComboBox.getSelectionModel().getSelectedItem().toString();
            } catch (Exception e) {
                System.err.println(e);
            }

            //This Function return true if it get the index of fatwa according to the case appropriate else return false
            if ((!action.equals("") && !benifit.equals("") && !transaction.equals(""))) {
                boolean find5 = isInListOfBenifit(benifit);
                if (find5) {
                    String benf = getBenifitClass(benifit);
                    for (cas c : ScreensFramework.cases) {
                        if (benf.equals(c.getBenifit())) {
                            cases.add(c);
                        }
                    }

                    boolean find4 = isInListOfAction(action);
                    if (find4) {
                        // check action if is in case base
                        for (cas c : cases) {
                            if (action.equals(c.getAction()) && transaction.equals(c.getTransaction())) {

                                test = true;
                                index = c.getInde();
                                break;

                            }

                        }
                        if (test) {
                            setFatwa(index);
                        } else {
                            ////check action in list of synonyme
                            actionindividual = IndividualList.getSameAsIndividuals(action);
                            if (!actionindividual.isEmpty()) {
                                for (String act : actionindividual) {
                                    for (cas c : cases) {
                                        if (act.equals(c.getAction()) && transaction.equals(c.getTransaction())) {
                                            index = c.getInde();
                                            ScreensFramework.ca = c;
                                            test = true;
                                            break;

                                        }
                                    }

                                }
                                if (test) {//check action in list of synonyme
                                    setFatwa(index);
                                } else {
                                    notFindHokm();
                                }
                            } else {
                                notFindHokm();
                            }
                        }
                    } else {
                        notFindHokm();
                    }

                } else {
                    notFindHokm();
                }
            }
        }
        System.out.println("2");
        if (test) {
            analyse.setVisible(test);
            general.setVisible(test);
            evidence.setVisible(test);
            getEvidence();
            getLikeCase();
            //evidence.setVisible(test);
        }
        button2.setVisible(test);
        // System.out.println("" + ActionComboBox.getSelectionModel().getSelectedItem().toString());

    }

    public void init() {

        hokme.setText("");
        quren.setText("");
        sunah.setText("");
        ijtihad.setText("");

    }

    public void setFatwa(int index) {
        // button2.setDisable(false);
        getFatwa();
        System.out.println("aaaaa" + index);
        GetFatwa gc = new GetFatwa();
        ftw = new fatwa();
        ftw = gc.getFatwa(index);

        ScreensFramework.fatwa = ftw;
        hokme.setText(" " + ftw.getHokm() + " ");
    }

    public void notFindHokm() {
        Dialogue d = new Dialogue();
        d.displayDialog(" هذه الحالة غير موجودة");
    }

    public boolean getIndex(String action, String benifit, String transaction) {
        findaction = false;
        findbenifit = false;
        findtransac = false;
        index = -1;
        actionindividual = IndividualList.getSameAsIndividuals(action);
        benifitindividual = IndividualList.getSameAsIndividuals(benifit);
        boolean test = false;
        if (checkChange()) {
            if (boolAction && boolGoodsCombox && boolQuestionComobox && boolTransaction) {
                for (cas cc : ScreensFramework.cases) {
                    if (action.equals(cc.getAction())) {
                        findaction = true;
                    }
                    if (benifit.equals(cc.getBenifit())) {
                        findbenifit = true;
                    }

                    if (transaction.equals(cc.getTransaction())) {
                        findtransac = true;

                    }
                    if (findaction && findbenifit && findtransac) {
                        find = true;
                        test = true;
                        index = cc.getInde();
                        break;
                    }
                }

            }

        }
        return test;
    }

    public void getCase() {
        //cases = c.getCases();

        c = ScreensFramework.cases.get(1);
    }

    public void checkField() {
        try {
            if (ActionComboBox.getSelectionModel().getSelectedItem().equals("")) {
                boolAction = true;
            }
            if (GoodsComboBox.getSelectionModel().getSelectedItem().equals("")) {
                boolGoodsCombox = true;
            }
            if (TransactionComboBox.getSelectionModel().getSelectedItem().equals("")) {
                boolTransaction = true;
            }

        } catch (Exception e) {

        }
    }

    public void getLikeCase() {

        //caseLike.setVisible(true);
        if (true) {

            try {
                //analyse.setVisible(true);
                likecase();

            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(Screen6Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //      action.setCellValueFactory(new PropertyValueFactory<>("action"));
        //action.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    public void likecase() throws OWLOntologyCreationException {
        String actions = null;
        String benifits = null;
        String transactions = null;
        checkField();
        List<cas> cases = new ArrayList<>();
        if (boolQuestionComobox && boolTransaction && boolGoodsCombox) {

            actions = ActionComboBox.getSelectionModel().getSelectedItem().toString();
            benifits = GoodsComboBox.getSelectionModel().getSelectedItem().toString();
            transactions = TransactionComboBox.getSelectionModel().getSelectedItem().toString();
            actionindividual.clear();
            cslk.clear();
            boolean find5 = isInListOfBenifit(benifits);
            if (find5) {

                String benf = getBenifitClass(benifits);
                for (cas c : ScreensFramework.cases) {
                    if (benf.equals(c.getBenifit())) {
                        cases.add(c);

                    }
                }

                actionindividual = IndividualList.getSameAsIndividuals(actions);
                if (!actionindividual.isEmpty() && !cases.isEmpty()) {

                    for (String act : actionindividual) {

                        for (cas cc : cases) {

                            if (act.equals(cc.getAction())) {

                                //findbenifit = true;
                                cslk.add(cc);
                            }
                        }
                    }
                }
            }
            if (!cslk.isEmpty()) {
                caseLike.setVisible(true);
                tableView.setItems(null);
                data = FXCollections.observableArrayList();
                data.addAll(cslk);
                action.setCellValueFactory(new PropertyValueFactory<cas, String>("action"));
                benifit.setCellValueFactory(new PropertyValueFactory<cas, String>("benifit"));
                transaction.setCellValueFactory(new PropertyValueFactory<cas, String>("transaction"));

                tableView.setItems(data);

            } else {
                Dialogue d = new Dialogue();
                d.displayDialog("لا يوجد حالات مشابهة");
            }

        }
    }

    public void getEvidence() {
        evidence.setVisible(true);
        quren.setText(ftw.getQuren());
        sunah.setText(ftw.getSuna());
        ijtihad.setText(ftw.getIjtihad());
    }

    public static boolean isInListOfAction(String action) {

        boolean find = false;
        if (!actionInstance.isEmpty()) {
            if (!action.equals("")) {
                for (String act : actionInstance) {
                    if (act.equals(action)) {
                        find = true;
                        break;
                    }
                }
            }
        }
        return find;
    }

    public boolean isInListOfBenifit(String ben) {
        boolean find = false;
        if (!ben.equals("")) {
            try {
                find = SuperClass.isIndividual(ben);

            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(Screen6Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return find;
    }

    public String getBenifitClass(String ben) {
        String benifit = null;
        if (!ben.equals("")) {
            try {
                benifit = SuperClass.getSubClass(ben);
            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(Screen6Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return benifit;
    }

    public void getFatwa() {

        general.setVisible(true);
    }

    public boolean checkChange() {
        if ((boolAction && boolGoodsCombox && boolQuestionComobox && boolTransaction)) {

            return true;
        } else {

            return false;
        }

    }

    @FXML
    public void editCase() {

        gotToSceen8();
    }

    @FXML
    public void changeScene() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen1.fxml");

        } catch (IOException ex) {
        }
    }

    public void gotToSceen2() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen.fxml");

        } catch (IOException ex) {
        }
    }

    public void gotToSceen3() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen3.fxml");

        } catch (IOException ex) {
        }
    }

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
    public void gotToLogin() {
        try {
            login.setLogout();
            ScreensFramework.changeScene("login.fxml");
        } catch (IOException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotToSceen6() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen6.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen7() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen7.fxml");

        } catch (IOException ex) {

        }
    }

    public void gotToSceen8() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen8.fxml");

        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }
}
