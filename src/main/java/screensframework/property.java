/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import com.sun.javafx.binding.BidirectionalBinding;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author fathi
 */
public class property {

    private static DoubleProperty p1 = new SimpleDoubleProperty();
    int i = 0;
    private DoubleProperty p2;

    public property() {

        p1.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });
    }

    public Double getP1() {
        return p1.get();
    }

    public void setP1(Double p1) {
        this.p1.set(p1);
    }

    public DoubleProperty getP2() {
        return p2;
    }

    public void setP2(Double p2) {
        this.p2.set(p2);
    }

}
