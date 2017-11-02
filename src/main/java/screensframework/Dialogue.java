package screensframework;

import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Dialogue extends Application {

    private Text actionStatus;
    private static final String titleTxt = "حذف معاملة معاملة";

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) {

        //displayDialog();
    }

   public  boolean displayDialog(String txt) {
        boolean evet = false;

        // Custom dialog
        Dialog dialog = new Dialog();
        dialog.setTitle("بحث عن فتوى");
        
        dialog.setResizable(true);
        dialog.setResizable(false);
        // Widgets
        Label label1 = new Label();
        label1.setText(txt);
     

       
        // Create layout and addto dialog
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 35, 20, 35));
        grid.add(label1, 1, 2); // col=1, row=1

        dialog.getDialogPane().setContent(grid);

        // Add button to dialog
       
        ButtonType cancel = new ButtonType("أغلق", ButtonData.CANCEL_CLOSE);
      
        dialog.getDialogPane().getButtonTypes().add(cancel);
        Optional<ButtonType> result = dialog.showAndWait();
       
        // Result converter for dialog
        // Show dialog
        return evet;
    }

  

}
