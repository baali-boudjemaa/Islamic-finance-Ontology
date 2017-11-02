/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.runtime.regexp.joni.ast.AnchorNode;

/**
 *
 * @author fathi
 */
public class MyScreen extends Parent {

    AnchorPane v = new AnchorPane();
    Button b1 = new Button("ok");
   Scene sc=new Scene(this);
    MyScreen() {
        super();
        b1.setMaxHeight(25);
        v.setMaxWidth(400);
        v.setMinHeight(500);
        getChildren().addAll(v, b1);
        
    }
}
