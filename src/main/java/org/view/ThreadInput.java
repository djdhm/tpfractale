package org.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import org.controller.ConfigController;
import org.fractale.FractaleConfig;

public class ThreadInput extends HBox {


    private Slider slider;
    private FractaleConfig fractaleConfig;
    public ThreadInput(FractaleConfig fractaleConfig){
        this.fractaleConfig = fractaleConfig;
        slider = new Slider();
        Label label = new Label("Nb Thread : ");
        setPadding(new Insets(20,10,10,10));
        setSpacing(10);
        slider.setMin(1);
        slider.setMax(8);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setValue(fractaleConfig.nbThreads);
        slider.valueProperty().addListener((o,oldValue,newValue)->{
            System.out.println(newValue);
            System.out.println(newValue.intValue());
            this.fractaleConfig.nbThreads = newValue.intValue();
        });
        getChildren().add(label);
        getChildren().add(slider);
    }

}
