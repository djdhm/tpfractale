package org.view;

import javafx.beans.Observable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.controller.DrawController;
import org.fractale.Fractale;
import org.fractale.FractaleGenerator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.IntBuffer;

public class FractaleView extends Canvas {
    Fractale fractale;
    FractaleGenerator fractaleGenerator;
    DrawController drawController;
    PixelWriter pixelWriter;
    ImageView imageView;
    public FractaleView(DrawController drawController){

        this.fractale=drawController.getFractale();
        this.fractaleGenerator=drawController.getFractaleGenerator();
        this.drawController = drawController;
        var gc= getGraphicsContext2D();

        pixelWriter = gc.getPixelWriter();
        System.out.println(getBoundsInParent());
        setWidth(640);
        setHeight(640);

        this.fractaleGenerator.valueProperty().addListener(this::handleChange);
        this.imageView = new ImageView();
        gc.setImageSmoothing(true);
    }


    public void updateFractaleGenerator(FractaleGenerator fractaleGenerator){
        this.fractaleGenerator = fractaleGenerator;
        fractaleGenerator.valueProperty().addListener((o, oldValue, pixels) ->
        pixelWriter.setPixels(0, 0, fractale.getWidth(), fractale.getHeight(),
        PixelFormat.getIntArgbInstance(), pixels, fractale.getWidth()));
        this.fractaleGenerator.valueProperty().addListener(this::handleChange);
    }

    private void handleChange(Observable observable, Object oldValue, IntBuffer newValue) {
        System.out.println("I am inside the handle change "+newValue);
        getGraphicsContext2D().clearRect(0, 0, 640, 640);
        WritableImage imageWriter=new  WritableImage(fractale.getWidth(),fractale.getHeight());

        PixelWriter pixelWriter = imageWriter.getPixelWriter();
        for (int i =0; i < fractale.getWidth(); i++){
            for(int j=0;j<fractale.getHeight();j++){
                pixelWriter.setArgb(i,j, newValue.get());
            }
        }

        this.imageView.setImage(imageWriter);
        this.imageView.setFitHeight(1000);
        this.imageView.setFitWidth(1000);
        this.drawController.setFinished();

    }

    public ImageView getImageView(){
        return this.imageView;
    }
    public void setFractale(Fractale fractale) {
        this.fractale=fractale;
        this.setWidth(fractale.getWidth());
        this.setHeight(fractale.getHeight());
    }
}
