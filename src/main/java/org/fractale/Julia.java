package org.fractale;

public class Julia extends  Fractale{

    Complexe constant;
    /**
     * Constructeur d'un ensemble de Julia
     * @param p : Fonction utilisé pour le calcul de l'ensemble
     * @param constant : Constante associé a la fonction de calcul
     */
    public Julia(Polynomial p,Complexe constant){
        super(p);
        this.constant=constant;
    }


    @Override
    Complexe getConstant(int x, int y) {
        return this.constant;
    }

    @Override
    Complexe getStartingPoint(int x, int y) {
        return new Complexe(this.getxMin()+this.getXStep()*x, this.getyMin()+this.getYStep()*y);
    }


}
