package org.fractale;

public class FractaleFactory {
    /**
     * Créer un fractale .
     * a partir d'une configuration specifié
     * @param config La configuration specifié
     * @return Fractale une instance de fractale ayant la configuration specificié
     */
    public Fractale getFractale(FractaleConfig config){
        Fractale fractale=null;
        Polynomial p = new Polynomial(config.polynome);

        if(config.setChoice.compareTo("julia")==0){
            Complexe constant = new Complexe(config.cx,config.cy);
            fractale = new Julia(p,constant);
        }else{
            System.out.println("New mandelbrot choice");
            fractale = new Mandelbrot(p);
        }
        fractale.setFilename(config.filename);
        fractale.setWidth(config.width);
        fractale.setHeight(config.height);
        fractale.setXBounds(config.minX, config.maxX);
        fractale.setYBounds(config.minY,config.maxY);
        fractale.setMaxIter(config.maxIter);
        fractale.setPalette(config.paletteChoice);
        fractale.setNbThreads(config.nbThreads);
        return fractale;
    }
}
