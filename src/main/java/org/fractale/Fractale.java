package org.fractale;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class Fractale {

    private int maxIter =1000;
    private static final int RADIUS =2;


    private Polynomial polynomial;
    private double xMax,xMin,yMax,yMin;
    private double xStep,yStep;
    private int width,height,zoom;
    private ColorPaletteEnum colorPalette;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private String filename;

    public Fractale(Polynomial p){
        this.polynomial=p;
    }
    public Polynomial getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(Polynomial polynomial) {
        this.polynomial = polynomial;
    }

    public double getxMax() {
        return xMax;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    public double getxMin() {
        return xMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }
    public void setXBounds(double xmin,double xmax){
        if(xmin>xmax) System.out.println("Error :Xmin>Xmax ");
        else{
            this.xMin=xmin;
            this.xMax=xmax;
            this.xStep = (xmax-xmin)/width;
        }
    }

    public void setYBounds(double ymin,double ymax){
        if(ymin>ymax) System.out.println("Error :Xmin>Xmax ");
        else{
            this.yMin=ymin;
            this.yMax=ymax;
            this.yStep = (ymax-ymin)/height;
        }
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getXStep() {
        return xStep;
    }
    public double getYStep() {
        return yStep;
    }

    public void setStep(double step) {
        this.xStep = step;
    }

    public void setZoom(int zoom){
        this.zoom = zoom;
    }



    abstract Complexe getConstant(int x, int y);
    abstract Complexe getStartingPoint(int x,int y);

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        this.xStep = (xMax-xMin)/width;

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {

        this.height = height;
        this.yStep = (yMax-yMin)/height;

    }

    public final Complexe nextTerm(Complexe z, Complexe constant){
        Complexe c = polynomial.evaluate(z);
        c.add(constant);
        return c;
    }

    int divergenceIndex(Complexe z, Complexe constant){
        int iter=0;
        Complexe z0=z;
        while(iter< maxIter -1 && z0.mod()<RADIUS){
            Complexe temp = nextTerm(z0,constant);
            z0.copy(temp);
            iter++;
        }
        return iter;
    }

    public void createImage()  {

        var img = new BufferedImage(width, height+40, BufferedImage.TYPE_INT_RGB);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y <height; y++) {
                Complexe z = getStartingPoint(x,y);
                Complexe constant = getConstant(x,y);
                int indice = divergenceIndex(z,constant);
                Color c = this.colorPalette.function.apply(indice, maxIter);
                img.setRGB(x,y,c.getRGB());
            }
        }

        File f = new File(filename+".png");
        try {
            Graphics2D g = (Graphics2D) img.getGraphics();

            RenderingHints rh =
                    new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g.setRenderingHints(rh);

            g.setFont(new Font("Purisa", Font.PLAIN, 13));
            g.setFont(new Font("TimesRoman", Font.ITALIC, 15));
            g.setColor(Color.white);
            g.fillRect(0,height,width,height+40);
            g.setColor(Color.pink);
            g.drawString("f(Zn+1) = "+polynomial.toString()+"+"+getConstant(0,0), 10, height+15);
            addMetaInformation(img);
            ImageIO.write(img, "PNG", f);
            System.out.println("Image written succefully");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error happened while saving the image");
        }
    }

    private void addMetaInformation(BufferedImage image) throws IOException {

        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(30f));
        g.dispose();

    }

    public Integer getMaxIter(){
        return this.maxIter;
    };

    public void setMaxIter(int maxIter) {
        this.maxIter = maxIter;
    }

    public void setPalette(ColorPaletteEnum paletteChoice) {
        this.colorPalette = paletteChoice;
    }

    public ColorPaletteEnum getPalette() {
        return this.colorPalette;
    }

    public int getNbThreads() {
        return this.zoom;
    }

    public void setNbThreads(int nbThreads) {
        this.zoom = nbThreads;
    }
}
