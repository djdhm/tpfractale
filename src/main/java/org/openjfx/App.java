package org.openjfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controller.ConfigController;
import org.controller.DrawController;
import org.controller.MainController;
import org.fractale.*;
import org.view.*;

import java.nio.IntBuffer;
import java.time.Duration;
import java.time.Instant;

/**
 * JavaFX App
 */
public class App extends Application {
    static FractaleConfig config;
    @Override
    public void start(Stage stage) {

        MainController mainController=new MainController(config);
        var scene = new Scene(mainController.getMainView(), 1200, 1200);
        stage.setTitle("Fractale TP");
        System.out.println("Width "+mainController.getMainView().getWidth());
        System.out.println("Height "+mainController.getMainView().getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public static void commandLine(FractaleConfig args) {
        Instant start = Instant.now();
        System.out.println("Running only command line version...");
        Complexe c = new Complexe(args.cx ,args.cy);
        Fractale fractaleSet;
        FractaleFactory fractaleFactory=new FractaleFactory();
        fractaleSet = fractaleFactory.getFractale(config);
        fractaleSet.createImage();
        Instant finish = Instant.now();
        System.out.println("Execution time "+ Duration.between(start,finish).toMillis()+"Ms");


    }

    public static void main(String[] args) {
        config = new FractaleConfig();
        ArgumentsParser argumentsParser= new ArgumentsParser(config);
        argumentsParser.parse(args);
        if (!config.gui ) {
            commandLine(config);
            Platform.exit();
        } else {
            launch(args);
        }



    }

}