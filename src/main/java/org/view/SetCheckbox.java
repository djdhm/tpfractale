package org.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.controller.ConfigController;
import org.fractale.FractaleConfig;

import java.util.Locale;


public class SetCheckbox extends HBox {

    ConfigController configController;
    FractaleConfig fractaleConfig;
    public SetCheckbox(ConfigController configController, FractaleConfig fractaleConfig){
        super();

        ToggleGroup setChoice = new ToggleGroup();
        RadioButton juliaButton = new RadioButton("Julia");
        RadioButton mandelbrotButton = new RadioButton("Mandelbrot");
        Label label = new Label("Ensemble : ");
        label.setTextFill(Color.BURLYWOOD);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC,14));
        juliaButton.setSelected(fractaleConfig.setChoice == "julia");
        juliaButton.setToggleGroup(setChoice);
        mandelbrotButton.setToggleGroup(setChoice);
        setSpacing(10);
        setPadding(new Insets(15,20, 10,10));
        getChildren().add(label);
        getChildren().add(juliaButton);
        getChildren().add(mandelbrotButton);
        setChoice.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue ov, Toggle old_toggle, Toggle new_toggle) {

                if (setChoice.getSelectedToggle() != null) {
                    RadioButton radioButton = (RadioButton) setChoice.getSelectedToggle();
                    configController.updateSetChoice(radioButton.getText().toLowerCase(Locale.ROOT));

                }

            }
        });

    }

}
