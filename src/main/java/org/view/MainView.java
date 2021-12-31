package org.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controller.MainController;

import java.io.File;

public class MainView extends BorderPane {


    private MainController controller;

    private FractaleView fractaleView;
    private Button start;
    private Button stop;
    private Button saveConfig;
    private Button saveImage;
    private Button loadConfig;
    public MainView(MainController controller){
        this.controller = controller;
        this.fractaleView = controller.getDrawController().getFractaleView();
        this.start = new Button("Draw");



        this.saveImage = new Button("Sauvegarder Image");

        stop = new Button("Stop");
        start.setOnAction(e ->{
            controller.draw();
        });
        stop.setOnAction(e->{
            controller.stopDrawing();
        });
        saveImage.setOnAction(e->{
            controller.saveImage();
        });
        saveImage.setDisable(true);

        VBox configVbox = new VBox();
        configVbox.setPadding(new Insets(20));
        configVbox.setSpacing(20);
        configVbox.getChildren().add(controller.getConfigController().getView());


        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.getChildren().add(start);
        stop.setDisable(true);
        configVbox.getChildren().add(saveImage);
        configVbox.getChildren().add(hbox);

        hbox.getChildren().add(stop);

        setLeft(configVbox);
        setCenter(fractaleView.getImageView());

    }

    public MainController getController() {
        return controller;
    }



    public FractaleView getFractaleView() {
        return fractaleView;
    }


    public void setWorking(boolean runnning) {
        System.out.println("Should stop ....");
        this.saveImage.setDisable(runnning);
        this.start.setDisable(runnning);
        this.stop.setDisable(!runnning);
    }


}
