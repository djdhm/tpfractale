package org.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.controller.ConfigController;
import org.fractale.FractaleConfig;

import java.util.function.Function;

public class BoundsInput extends GridPane  {

    public BoundsInput(ConfigController controller, FractaleConfig config){
        super();
        setPadding(new Insets(10));
        setHgap(25);
        setVgap(15);
        Label labelTitle = new Label("Limites de l'ensemble ");
        add(labelTitle,0,0,2,1);
        Label xMinLabel = new Label("Xmin ");
        DecimalInput xMinInput = new DecimalInput(config.minX);
        xMinInput.bindEvent(controller::updateMinX);
        Label yMinLabel = new Label("Ymin ");
        DecimalInput yMinInput = new DecimalInput(config.minY);
        yMinInput.bindEvent(controller::updateMinY);
        Label xMaxLabel = new Label("Xmax ");
        DecimalInput xMaxInput = new DecimalInput(config.maxX);
        xMaxInput.bindEvent(controller::updateMaxX);
        Label yMaxLabel = new Label("Ymax ");
        DecimalInput yMaxInput = new DecimalInput(config.maxY);
        yMaxInput.bindEvent(controller::updateMaxY);
        Label maxIterLabel = new Label("Max Iterations ");
        IntegerInput maxIterInput = new IntegerInput(config.maxIter);
        add(xMinLabel,0,1);
        add(xMinInput,1,1);
        add(xMaxLabel,0,2);
        add(xMaxInput,1,2);
        add(yMinLabel,0,3);
        add(yMinInput,1,3);
        add(yMaxLabel,0,4);
        add(yMaxInput,1,4);

        Label constantLabel = new Label("Constante ");
        Label realLabel = new Label("Re ");
        DecimalInput realInput = new DecimalInput(config.cx);
        Label imaginaryLabel= new Label("Im ");
        DecimalInput imaginaryInput = new DecimalInput(config.cy);
        realInput.bindEvent(controller::updateConstantRe);
        imaginaryInput.bindEvent(controller::updateConstanteIm);
        maxIterInput.bindEvent(controller::updateMaxIter);
        add(constantLabel,0,5,2,1);
        add(realLabel,0,6);
        add(realInput,1,6);
        add(imaginaryLabel,0,7);
        add(imaginaryInput,1,7);
        add(maxIterLabel,0,8);
        add(maxIterInput,1,8);



    }


}
