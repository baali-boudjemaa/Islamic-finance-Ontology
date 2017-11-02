package screensframework;

import Case.cas;
import Case.GetCase;
import Fatw.GetFatwa;
import Fatw.fatwa;
import file.CopyFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author fathi
 */
public class ScreensFramework extends Application {

    static Stage stage;
    public static String screen1ID = "main";
    public static String screen1File = "Screen1.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "Screen2.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "Screen3.fxml";
    public static String screen4ID = "screen4";
    public static String screen4File = "Screen4.fxml";
    public static String screen5ID = "screen5";
    public static String screen5File = "Screen5.fxml";
    public static ArrayList<String> screen = new ArrayList<>();
    public static Parent parent, p1, p4, p2, p3, p5, p6, p7, login, p8;
    public static List<cas> cases = new ArrayList<cas>();
    static List<String> actionInstance = new ArrayList<>();
    static URL path;

    static GetCase c = new GetCase();
    GetFatwa f = new GetFatwa();
    static boolean admin = false;
    static cas ca = new cas();
    static fatwa fatwa = new fatwa();
    String dire = null;
    static ResourceBundle bundle;
    

    @Override
    public void start(final Stage primaryStage) throws IOException {
        System.setProperty("prism.text", "t2k");

        System.setProperty("prism.lcdtext", "false");
        bundle  = ResourceBundle.getBundle("title");
        //Insert.insert();
        // String url=getClass().getResource("/ontofile/islamic_finance.owl").toString();
        // System.out.println(""+url);
        // checkFile(url); 
        ScreensFramework.stage = primaryStage;
        InputStream in=getClass().getResourceAsStream("/icon/myicon1.png");
        stage.getIcons().add(new Image(in));
        Loader n = new Loader();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                GetCase.close();
                GetFatwa.close();
                Factory.close();
                Platform.exit();

            }

        });
        //  mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        parent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        n.afterLogin();
        //parent = FXMLLoader.load(getClass().getResource("/fxml/Screen1.fxml"));
        Thread t = new Thread(new Runnable() {
            String dire = System.getProperty("user.dir");

            @Override
            public void run() {
                try {
                    SuperClass.setConnection(dire + "/AidFatwa2.owl");
                    OntoConnect.setConnection(dire + "/islamic_finance.owl");
                    actionInstance = SuperClass.getListActionFromOntology();
                    c = new GetCase();
                    cases = c.getCases();
                    GetFatwa f = new GetFatwa();
                    f.getFatwa();
                    if (cases.isEmpty()) {
                        System.out.println("baali");
                    } else {
                        for (cas cv : cases) {
                            System.out.println("aa;:" + cv.toString());
                        }
                    }
                } catch (Exception e) {
                    System.err.println("error" + e);
                }

                /*   try {
                 mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
                 mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
                 mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
                 mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);


                 } catch (IOException ex) {
                 Logger.getLogger(ScreensFramework.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
            }

        });

        // mainContainer.setScreen(ScreensFramework.screen1ID);
        Scene scene = new Scene(parent);
        stage.onCloseRequestProperty().addListener(new ChangeListener<EventHandler<WindowEvent>>() {
            @Override
            public void changed(ObservableValue<? extends EventHandler<WindowEvent>> observable, EventHandler<WindowEvent> oldValue, EventHandler<WindowEvent> newValue) {

                try {
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");

                    /*stop();*/
                } catch (Exception ex) {
                    Logger.getLogger(ScreensFramework.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        stage.setScene(scene);
        stage.setTitle(bundle.getString("login"));
        stage.setResizable(false);
        stage.show();

        //Platform.exit();
        // primaryStage.setResizable(false);
        stage.setOnShown(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
            }
        });

        t.start();

    }

    public static void refreshCase() {

        cases = c.getCases();
    }

    public static void refrechOntology() {
        actionInstance = SuperClass.getListActionFromOntology();
    }

    public static void changeScene(String sceneName) throws IOException {
        if (sceneName.equals("Screen.fxml")) {

            try {
                //add Contract
                p2 = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/Screen.fxml"));
                stage.setScene(new Scene(p2));
                stage.setTitle(bundle.getString("AddContract"));
                setCenter();
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
            }
        } else if (sceneName.equals("Screen3.fxml")) {
            //edit Contract
            try {
                p3 = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/Screen3.fxml"));
                stage.setScene(new Scene(p3));
                stage.setTitle(bundle.getString("EditContract"));
                setCenter();
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                System.out.println("eee" + e);
            }
        } else if (sceneName.equals("Screen4.fxml")) {
            try {
                p4 = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/Screen4.fxml"));
                stage.setScene(new Scene(p4));
                stage.setTitle(bundle.getString("DeleteContract"));
                setCenter();
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                System.out.println("eee" + e);
            }
        } else if (sceneName.equals("Screen1.fxml")) {
            //home screen
            try {
                p1 = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/Screen1.fxml"));
                stage.setScene(new Scene(p1));
                stage.setTitle(bundle.getString("MainMenu"));
                setCenter();
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                System.out.println("eee" + e);
            }

        } else if (sceneName.equals("Screen5.fxml")) {
            //set get Contract 
            try {
                p5 = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/Screen5.fxml"));
                stage.setScene(new Scene(p5));
                stage.setTitle(bundle.getString("ListContract"));
                setCenter();
                stage.setResizable(false);
                stage.show();

            } catch (Exception e) {
                System.out.println("eee" + e);
            }
        } else if (sceneName.equals("Screen6.fxml")) {
            //set get Contract 
            try {
                p6 = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/Screen6.fxml"));
                stage.setScene(new Scene(p6));
                stage.setTitle(bundle.getString("QuestionAnswer"));
                setCenter();
                stage.setResizable(false);
                stage.show();

            } catch (Exception e) {
                System.out.println("eee" + e);
            }
        } else if (sceneName.equals("Screen7.fxml")) {
            //set get Contract 
            try {
                p7 = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/Screen7.fxml"));
                stage.setScene(new Scene(p7));
                stage.setTitle(bundle.getString("addcase"));
                setCenter();
                stage.setResizable(false);
                stage.show();

            } catch (Exception e) {
                System.out.println("eee" + e);
            }
        } else if (sceneName.equals("login.fxml")) {
            ///fxml/login.fxml
            login = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/login.fxml"));
            stage.setScene(new Scene(login));
            stage.setTitle(bundle.getString("login"));
            setCenter();
            stage.setResizable(false);
            stage.show();
        } else if (sceneName.equals("Screen8.fxml")) {
            ///fxml/login.fxml
            p8 = FXMLLoader.load(ScreensFramework.class.getResource("/fxml/Screen8.fxml"));
            stage.setTitle(bundle.getString("EditCase"));
            stage.setScene(new Scene(p8));
            setCenter();
            stage.setResizable(false);
            stage.show();
        }

        // parent = FXMLLoader.load(ScreensFramework.class.getResource(sceneName));
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static boolean checkFile(String url) {
        boolean find = false;
        final String dir = System.getProperty("user.dir");
        File file = new File(dir + "/islamic_finance.owl");

        if (!file.exists()) {
            try {

                File f = new File(url);
                if (f.exists()) {
                    System.out.println("error");
                    CopyOntology.copyFile(f, file);
                }
                if (!f.exists()) {
                    Dialogue d = new Dialogue();
                    d.displayDialog("file not found");
                    find = false;
                } else {
                    find = true;
                }
            } catch (IOException ex) {
                Logger.getLogger(ScreensFramework.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return find;
    }

    public static void load() {

    }

    /*public static void copyfile(InputStream is) {

     try {
     final String dir = System.getProperty("user.dir");

     OutputStream os = new FileOutputStream(dir + "/islamic_finance.owl");
     byte[] buffer = new byte[32768];
     int bytesRead;
     //read from is to buffer
     while ((bytesRead = is.read(buffer)) != -1) {
     os.write(buffer, 0, bytesRead);
     }
     is.close();
     //flush OutputStream to write any buffered data to file
     os.flush();
     os.close();

     } catch (Exception ex) {
     Logger.getLogger(ScreensFramework.class.getName()).log(Level.SEVERE, null, ex);
     }

     }*/
    public static void stream2file(InputStream in, String dir) throws IOException {
        final File tempFile = new File(dir + "/islamic_finance.owl");

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }

    }

    static void setCenter() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

}
