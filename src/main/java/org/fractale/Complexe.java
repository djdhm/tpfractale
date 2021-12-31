package org.fractale;
/**
 * Class Complexe .
 * Classe representant un nombre Complexe
 * cette classe permet de faire les operations sur des nombre complexe
 */
public class Complexe {

    private double real;
    private double imaginary;
    /**
     * Constructeur de Complexe .
     *
     * @param real  partie réel de nombre complexe
     * @param imaginary partie imaginaire de nombre complexe
     * @return the baz content
     */
    public Complexe(double real, double imaginary){
        this.real=real;
        this.imaginary=imaginary;
    }

    /**
     * Constructeur par défaut .
     * permet de créer un nombre complxe = 0
     */
    public Complexe() {
        this.real = 0;
        this.imaginary = 0;
    }

    public Complexe conjugate(){
        return new Complexe(this.real, -this.imaginary);
    }
    public double mod()
    {
        return Math.sqrt(Math.pow(this.real,2) + Math.pow(this.imaginary,2));
    }

    public Complexe square()
    {
        double real = this.real*this.real - this.imaginary*this.imaginary;
        double imaginary = 2*this.real*this.imaginary;
        return new Complexe(real,imaginary);
    }

    public static Complexe add(Complexe z1, Complexe z2){
        return new Complexe(z1.getReal()+z2.getReal(),z1.getImaginary()+z2.getImaginary());
    }
    public static Complexe substract(Complexe z1, Complexe z2){
        return new Complexe(z1.getReal()-z2.getReal(), z1.getImaginary()-z2.getImaginary());
    }
    public static Complexe multiply(Complexe z1, Complexe z2){
        double real = z1.getReal()*z2.getReal() - z1.getImaginary()*z2.getImaginary();
        double imaginary = z1.getReal()*z2.getImaginary()+z1.getImaginary()*z2.getReal();
        return new Complexe(real,imaginary);
    }
    public static Complexe divide(Complexe z1, Complexe z2){
        Complexe temp = multiply(z1,z2);
        double div = Math.pow(z2.mod(),2);
        return new Complexe(temp.getReal()/div, temp.getImaginary()/div);
    }
    public static Complexe pow(Complexe z, int power)
    {
        if(power == 0) return new Complexe(1,0);
        Complexe result = new Complexe(z.getReal(),z.getImaginary());
        for(int i = 1; i < power; i++)
        {
            double real = result.real*z.real - result.imaginary*z.imaginary;
            double imaginary = result.real*z.imaginary + result.imaginary*z.real;
            result = new Complexe(real,imaginary);
        }
        return result;
    }
    /**
     * Copie d'un nombre complexe
     *
     * @param z Nombre complexe a copier
     */
    public void copy(Complexe z){
        this.real = z.getReal();
        this.imaginary = z.getImaginary();
    }
    public void add(Complexe z){
        this.copy(add(this,z));
    }
    public void substract(Complexe z){
        this.copy(substract(this,z));
    }
    public void multiply(Complexe z){
        this.copy(multiply(this,z));
    }
    public void multiply(double x){
        this.real=this.real*x;
        this.imaginary=this.imaginary*x;
    }
    public void divide(Complexe z){
        this.copy(divide(this,z));
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public String toString(){
        String sign = this.imaginary<0? "-":"+";
        return this.real+sign+Math.abs(this.imaginary)+"i ";
    }
}
