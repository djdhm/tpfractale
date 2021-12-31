package org.controller;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.fractale.ColorPaletteEnum;
import org.fractale.Fractale;
import org.fractale.FractaleConfig;
import org.view.*;

public class ConfigController {

    private FractaleConfig config;
    private Fractale fractale;

    private ResolutionInput resolutionInput;
    private BoundsInput boundsInput;
    private SetCheckbox setCheckbox;
    private ThreadInput threadInput;
    private PaletteChoice paletteChoice;
    private VBox vbox;

    public ConfigController(FractaleConfig fractaleConfig){
        vbox = new VBox();
        this.setCheckbox =  new SetCheckbox(this, fractaleConfig);
        this.config = fractaleConfig;
        this.boundsInput = new BoundsInput(this, fractaleConfig);
        this.resolutionInput=new ResolutionInput(this, fractaleConfig);
        this.threadInput = new ThreadInput( fractaleConfig);

        this.paletteChoice = new PaletteChoice(this, fractaleConfig);

        vbox.getChildren().add(setCheckbox);
        vbox.getChildren().add(boundsInput);
        vbox.getChildren().add(resolutionInput);
        vbox.getChildren().add(threadInput);
        vbox.getChildren().add(paletteChoice);
    }
    

    public boolean updateMinX(double value){
        if(value<config.maxX) this.config.minX=value;
        else return false;
        return true;
    }

    public boolean updateMinY(double value){
        if(value<config.maxY) this.config.minY=value;
        else return false;
        return true;
    }
    public boolean updateMaxX(double value){
        if(value>config.minX) this.config.maxX=value;
        else return false;
        return true;
    }
    public boolean updateMaxY(double value){
        if(value>config.minY) this.config.maxY=value;
        else return false;
        return true;
    }

    public boolean updateConstantRe(double re){
        this.config.cx=re;
        return true;
    }
    public boolean updateConstanteIm(double im){
        this.config.cy=im;
        return true;
    }



    public Boolean updateWidth(int width) {
        System.out.println("New Height : " + width);
        this.config.width=width;
        return true;
    }

    public Boolean updateHeight(Integer height) {
        System.out.println("New Height : " + height);
        this.config.height=height;
        return true;
    }

    public VBox getView(){
        return this.vbox;
    }

    public void updatePalette(ObservableValue observableValue, Object o, Object o1) {
        this.config.paletteChoice = (ColorPaletteEnum) o1;
    }


    public void updateSetChoice(String setChoice) {
        System.out.println(setChoice);
        this.config.setChoice = setChoice;
        System.out.println(config.setChoice);
    }

    public Boolean updateMaxIter(Integer integer) {
        if(integer<1) {
            System.out.println("Max iter > 0");
            return false;
        }else{
            this.config.maxIter = integer;
            return true;
        }
    }
}

