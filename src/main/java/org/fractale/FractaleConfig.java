package org.fractale;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * class FractaleConfig.
 * cette class contient la configuration d'un fractale
 */
public class FractaleConfig implements Serializable {

    public String setChoice;
    public double minX,maxX,minY,maxY,cx,cy;
    public int width,height;
    public int nbThreads;
    public int maxIter;
    public boolean gui;
    public String filename;
    public String polynome;
    public ColorPaletteEnum paletteChoice;


    public String toString(){
        StringJoiner stringJoiner=new StringJoiner("\n");

        stringJoiner.add("Type D'ensemble:"+setChoice);
        stringJoiner.add("Polynome:"+polynome);
        stringJoiner.add("Resolution :"+ width+","+height);
        stringJoiner.add("Carré: X <- ["+ minX+","+maxX+"] Y <- ["+minY+","+maxY+"]");
        stringJoiner.add("Max iteraitons :"+maxIter);
        stringJoiner.add("Nombre de Thread:"+nbThreads);
        return stringJoiner.toString();
    }

}
