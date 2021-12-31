package org.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.controller.ConfigController;
import org.controller.DrawController;
import org.fractale.Fractale;
import org.fractale.FractaleConfig;

public class ResolutionInput extends GridPane {
    ConfigController configController;
    FractaleConfig config;
    public ResolutionInput(ConfigController configController, FractaleConfig config){
        this.configController = this.configController;
        this.config = config;
        setPadding(new Insets(10));
        setHgap(25);
        setVgap(15);
        Label labelTitle = new Label("Resolution de l'image  ");
        add(labelTitle,0,0,2,1);
        Label widthLabel = new Label("Width");
        IntegerInput widthInput = new IntegerInput(config.width);
        widthInput.bindEvent(configController::updateWidth);
        Label heightLabel = new Label("Height");
        IntegerInput heightInput = new IntegerInput(config.height);
        heightInput.bindEvent(configController::updateHeight);
        add(widthLabel,0,1);
        add(widthInput,1,1);
        add(heightLabel,0,2);
        add(heightInput,1,2);

    }

}
