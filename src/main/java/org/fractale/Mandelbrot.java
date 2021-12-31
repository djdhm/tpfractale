package org.fractale;

public class Mandelbrot extends Fractale {

    /**
     * Constructeur de Mandelbrot.
     * Permet de créer un ensemble Fractale de Mandelbrot associé au polynome p
     * @param p Polynome associé
     */
    public Mandelbrot(Polynomial p){
        super(p);
    }

    @Override
    Complexe getConstant(int x, int y) {
        return new Complexe(this.getxMin()+this.getXStep()*x, this.getyMin()+this.getYStep()*y);
    }

    @Override
    Complexe getStartingPoint(int x, int y) {
        return new Complexe();
    }
}
