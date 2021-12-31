package org.openjfx;

import org.fractale.Fractale;
import org.fractale.FractaleConfig;
import org.fractale.FractaleFactory;
import org.fractale.FractaleGenerator;

import java.nio.IntBuffer;

public class CommandLineApp {

    public static void main(FractaleConfig fractaleConfig){
        FractaleFactory fractaleFactory= new FractaleFactory();
        Fractale fractale = fractaleFactory.getFractale(fractaleConfig);
        FractaleGenerator fractaleGenerator=new FractaleGenerator(fractale);
        fractaleGenerator.run();
        fractaleGenerator.valueProperty().addListener((o,v,n)->{
            System.out.println(n);
        });
        Thread t = new Thread(fractaleGenerator);
        t.start();
        while(t.isAlive()){
            System.out.println("Waiting");
        }
        IntBuffer intBuffer=fractaleGenerator.getPixelBuffer();
        for(int i=0;i<fractaleConfig.width;i++){
            System.out.println(intBuffer.get());
        }

    }
}
