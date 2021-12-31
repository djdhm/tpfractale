package org.openjfx;

import javafx.application.Application;
import org.fractale.FractaleConfig;

public class Main {

    public static void main(String[] args){
        FractaleConfig fractaleConfig= new FractaleConfig();
        ArgumentsParser argumentsParser = new ArgumentsParser(fractaleConfig);
        argumentsParser.parse(args);
        App.main(args);
    }
}
