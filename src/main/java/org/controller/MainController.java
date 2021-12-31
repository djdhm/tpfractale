package org.controller;

import javafx.scene.image.*;
import org.fractale.Fractale;
import org.fractale.FractaleConfig;
import org.fractale.FractaleFactory;
import org.fractale.FractaleGenerator;
import org.view.MainView;


import java.io.File;


public class MainController {


    private ConfigController configController;
    private DrawController drawController;
    private Fractale fractale;
    private FractaleGenerator fractaleGenerator;
    private FractaleConfig fractaleConfig;
    private FractaleFactory fractaleFactory;
    private MainView mainView;

    public MainController(FractaleConfig fractaleConfig){
        this.fractaleConfig = fractaleConfig;
        this.fractaleFactory = new FractaleFactory();
        this.fractale = fractaleFactory.getFractale(fractaleConfig);
        this.configController = new ConfigController(fractaleConfig);
        this.fractaleGenerator = new FractaleGenerator(fractale);
        this.drawController = new DrawController(fractale,this);
        this.mainView = new MainView(this);
        fractaleGenerator.valueProperty().addListener(((o,old,value)->{
            this.setRunning(false);
        }));


    }

    public ConfigController getConfigController() {
        return configController;
    }

    public DrawController getDrawController() {
        return drawController;
    }

    public Fractale getFractale() {
        return fractale;
    }

    public FractaleGenerator getFractaleGenerator() {
        return fractaleGenerator;
    }

    public FractaleConfig getFractaleConfig() {
        return fractaleConfig;
    }

    public FractaleFactory getFractaleFactory() {
        return fractaleFactory;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void draw(){
        Fractale fractale = fractaleFactory.getFractale(fractaleConfig);
        this.drawController.setFractale(fractale);
        setRunning(true);
        this.drawController.run();
        this.fractaleGenerator=this.drawController.getFractaleGenerator();
    }

    public void setRunning(boolean runnning){
        this.mainView.setWorking(runnning);
    }

    public void stopDrawing() {
        this.drawController.stop();
        setRunning(false);
    }
    public void saveImage(){
        this.fractaleGenerator.saveImage(fractaleConfig);
    }

    public void saveConfig() {
        File file = new File("config.txt");

        

    }

    public void chargerConfig() {
        System.out.println("Charger config");
    }
}
