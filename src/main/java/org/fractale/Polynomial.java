package org.fractale;

import java.util.Arrays;

public class Polynomial {
    private int degree;
    private double coeff[];

    /**
     * Constructeur de Polynomial .
     *
     * @param degree  Degree du polynome
     * @param coeff Tableau contenant les coefficients de polynome

     */
    public Polynomial(int degree,double coeff[]) throws Exception{
        if(degree>coeff.length) throw new Exception();
        this.degree=degree;
        this.coeff=coeff;
    }

    /**
     * Constructeur de Polynome .
     * Ce constructeur permet de parser une expression d'un polynome
     * pour extraire les coefficients
     * @param expression Expression de polynome
     */
    public Polynomial(String expression){
       try{
           String[] terms = expression.split("(-|\\+)");
           degree = terms.length;
           for (String term : terms) {
               String[] parts = term.split("\\^");
               int exp = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
               if(degree<exp) degree=exp;
           }
           coeff=new double[degree+1];
           for(String term:terms){
               String[] parts = term.split("\\^");
               int exp = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
               coeff[exp]+=Double.parseDouble(parts[0].split("x|X|Z")[0]);

           }
       }catch (Exception e){
           System.out.println(e);
           System.out.println("Erreur dans l'initialisation de polynome");
           throw new IllegalArgumentException();
       }

    }

    /**
     * Evaluation d'un point complexe par le polynome .
     *
     * @param x Le nombre complexe
     * @return l'image de x par dans la fonciton associé au polynome f(x)
     */
    public Complexe evaluate(Complexe x){
        Complexe evaluation= new Complexe();
        for(int i=0;i<=degree;i++){
            Complexe temp = Complexe.pow(x,i);
            temp.multiply(coeff[i]);
            evaluation.add(temp);
        }
        return evaluation;
    }


    /**
     * toString.
     *
     * @return l'expression de polynome
     */
    @Override
    public String toString() {
        String polynome = coeff[0]>0 ? ""+coeff[0] : "";
        for(int i=1;i<=degree;i++){
            if(coeff[i]!=0){

                String signe = coeff[i]>0 && polynome!="" ? "+":"";
                polynome+= signe+(coeff[i]!=1 ? coeff[i] : "")+"Zn^"+i;
            }
        }
        return polynome;
    }


}
