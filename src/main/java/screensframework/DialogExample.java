package screensframework;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.util.Callback;

public class DialogExample extends Application {

    private Text actionStatus;
    private static final String titleTxt = "حذف معاملة معاملة";

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) {

        displayDialog();
    }

    boolean displayDialog() {
        boolean evet = false;

        // Custom dialog
        Dialog dialog = new Dialog<>();
        dialog.setTitle(titleTxt);
        dialog.setHeaderText(" هل تريد حذف هذه المعاملة");
        dialog.setResizable(true);
        dialog.setResizable(false);
        // Widgets
        Label label1 = new Label();

        Image image = new Image("/icon/ad.png");

        label1.setGraphic(new ImageView(image));
        // Create layout and addto dialog
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 35, 20, 35));
        grid.add(label1, 1, 2); // col=1, row=1

        dialog.getDialogPane().setContent(grid);

        // Add button to dialog
        ButtonType buttonTypeOk = new ButtonType("حذف", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("إلغاء", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(cancel);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == buttonTypeOk) {
            evet = true;
        }
        // Result converter for dialog
        // Show dialog
        return evet;
    }

}
