package org.controller;

import org.fractale.Fractale;
import org.fractale.FractaleGenerator;
import org.view.FractaleView;
import org.view.MainView;

public class DrawController {

    private MainController mainController;
    Fractale fractale;
    FractaleGenerator fractaleGenerator;
    FractaleView fractaleView;
    Thread thread;
    public DrawController(Fractale fractale, MainController mainController){
        this.mainController = mainController;
        this.fractale = fractale;
        this.fractaleGenerator=new FractaleGenerator(fractale);
        this.fractaleView = new FractaleView(this);
    }



    public void run(){
        System.out.println("Running Worker");
        System.out.println("this is the fractale gen "+fractaleGenerator);
        this.fractaleGenerator= new FractaleGenerator(fractale);
        this.fractaleView.updateFractaleGenerator(fractaleGenerator);
        this.thread = new Thread(fractaleGenerator);
        thread.start();

    }
    public void stop(){
        System.out.println("Stopping workers");
        for( Thread t:this.fractaleGenerator.getThreads()){
            t.stop();
        }
        this.thread.stop();
    }


    public FractaleView getFractaleView(){
        return this.fractaleView;
    }
    public FractaleGenerator getFractaleGenerator() {
        return fractaleGenerator;
    }

    public Fractale getFractale() {
        return fractale;
    }


    public void setFractale(Fractale fractale) {
        this.fractale = fractale;
        this.fractaleGenerator.setFractale(fractale);
        this.fractaleView.setFractale(fractale);
    }
    public void setFinished(){
        this.mainController.setRunning(false);
    }
}
