package screensframework;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import login.login;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class Screen1Controller implements Initializable, ControlledScreen {

    ScreensController myController;
    boolean m;
    @FXML
    private AnchorPane vBoxScene1;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    Pane pane;
    Double w1, w2;

    ;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        boolean admin = false;
        admin = login.isLogin();
        if (!admin) {
            button1.setDisable(true);
            button2.setDisable(true);
            button3.setDisable(true);
            System.out.println("////////////");
        }
        ScreensFramework.stage.setMinWidth(178);
        vBoxScene1.setMaxHeight(600);
        w1 = vBoxScene1.getPrefWidth();
        w2 = pane.getLayoutX();
        String fileName = location.getFile().substring(location.getFile().lastIndexOf('/') + 1, location.getFile().length());
        fadeTransition(vBoxScene1);
        vBoxScene1.widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (vBoxScene1.getWidth() > 178) {
                    pane.setLayoutX((375 - ((w1 - vBoxScene1.getWidth())
                            / 2)));
                } else if (vBoxScene1.getWidth() < 178) {

                }

                {

                }
            }

        });

    }

    @Override

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public ScreensController getMyController() {
        return myController;
    }

    private void goToScreen2(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen2ID);
        setSecreen(ScreensFramework.screen2ID);

    }

    private void goToScreen3(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen3ID);
        setSecreen(ScreensFramework.screen3ID);
    }

    private void goToScreen4(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen4ID);
        setSecreen(ScreensFramework.screen4ID);
    }

    private void goToScreen5(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen4ID);
        setSecreen(ScreensFramework.screen4ID);
    }

    public void setSecreen(String s) {
        m = ScreensFramework.screen.add(s);
    }

    @FXML
    public void changeScene(ActionEvent actionEvent) {
        //get the button object on which user clicks and change scene acoording to that
        if (actionEvent.getSource().equals(button1)) {
            try {
                ScreensFramework.changeScene("Screen.fxml");

            } catch (IOException e) {
            }
        } else if (actionEvent.getSource().equals(button2)) {
            try {
                ScreensFramework.changeScene("Screen3.fxml");
            } catch (IOException e) {
                System.out.println(""+e);
            }
        } else if (actionEvent.getSource().equals(button3)) {
            try {
                ScreensFramework.changeScene("Screen4.fxml");
            } catch (IOException e) {
                System.out.println(""+e);
            }
        } else if (actionEvent.getSource().equals(button5)) {
            try {
                ScreensFramework.changeScene("Screen5.fxml");
            } catch (IOException e) {
                System.out.println(""+e);
            }
        } else if (actionEvent.getSource().equals(button6)) {
            try {
                System.out.println("123");
                ScreensFramework.changeScene("Screen6.fxml");
            } catch (IOException e) {
                System.out.println(""+e);
            }
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

    private void fadeTransition(Node e) {
        FadeTransition x = new FadeTransition(new Duration(2000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }
}
