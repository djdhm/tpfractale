package org.view;

import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.controller.ConfigController;
import org.fractale.ColorPalette;
import org.fractale.ColorPaletteEnum;
import org.fractale.Fractale;
import org.fractale.FractaleConfig;


public class PaletteChoice extends HBox {

    private FractaleConfig fractaleConfig;
    private ConfigController configController;
    public PaletteChoice(ConfigController configController, FractaleConfig fractaleConfig){
        this.fractaleConfig = fractaleConfig;
        this.configController = configController;
        setPadding(new Insets(20,10,10,10));
        setSpacing(10);
        Label label = new Label("Palette de couleur ");
        ChoiceBox choiceBox =new ChoiceBox();
        for(ColorPaletteEnum s: ColorPaletteEnum.values()){
            choiceBox.getItems().add(s);
        }
        choiceBox.setValue(fractaleConfig.paletteChoice);
        choiceBox.valueProperty().addListener(configController::updatePalette);
        getChildren().add(label);
        getChildren().add(choiceBox);
    }


}
