package org.fractale;

import javafx.concurrent.Task;
import javafx.scene.image.PixelReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.IntBuffer;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class FractaleGenerator extends Task<IntBuffer>{

    Fractale fractale;
    IntBuffer pixelsBuffer;
    private ArrayList<Thread> threads;
    private ArrayList<ComputeTask> computeTasks;
    long executionTime;
    /**
     * Constructeur de Fracatale Generator .
     *
     * @param fractale l'ensemble fractale a calculer
     * @param drawController Le controlleur de l'affichage de l'ensemble
     */
    public FractaleGenerator(Fractale fractale){
        this.fractale=fractale;
        this.threads = new ArrayList<>();
        this.computeTasks = new ArrayList<>();

    }


    /**
     * Cette fonction permet de lancer une tache de generation
     * qui peut etre lancé dans un thread separé
     * @return intbuffer qui contient les coleurs des pixels de l'ensemble a dessiner
     */
    @Override
    protected IntBuffer call() throws Exception {
        System.out.println("Working from Generator noooow"+fractale.getNbThreads());
        Instant start = Instant.now();
        pixelsBuffer = IntBuffer.allocate(fractale.getWidth()*fractale.getHeight());
        int nbRows = fractale.getWidth()/fractale.getNbThreads();
        int firstRowsToAdd = fractale.getWidth()%fractale.getNbThreads();

        System.out.println("Each Thread will do "+nbRows);
        for(int i=0;i<fractale.getNbThreads();i++){
            ComputeTask computeTask = new ComputeTask(fractale, nbRows*i,nbRows+firstRowsToAdd);
            firstRowsToAdd=0;
            Thread t = new Thread(computeTask);
            System.out.println(fractale.getNbThreads());
            System.out.println("Launching "+t);
            threads.add(t);
            computeTasks.add(computeTask);
            t.start();
        }
        for(int i=0; i<threads.size();i++){
            System.out.println("waiting "+threads.get(i));
            threads.get(i).join();
            pixelsBuffer.put(computeTasks.get(i).getIntBuffer());
        }
        pixelsBuffer.flip();
        Instant finish = Instant.now();
        executionTime = Duration.between(start,finish).toMillis();
        return pixelsBuffer;

    }

    public void setFractale(Fractale fractale){
        this.fractale = fractale;
    }

    public ArrayList<Thread> getThreads() {
        return this.threads;
    }

    public IntBuffer getPixelBuffer() {
        return pixelsBuffer;
    }

    /**
     * Sauvegarder une image lié a un ensemble dans un fichier .
     * Et aussi un fichier de configuration qui cotient les parametres de l'ensemble
     * @param fractaleConfig  la configuration specifié pour le calcul de fractale
     */
    public void saveImage(FractaleConfig fractaleConfig) {
        try{

            System.out.println("Begin saving image "+fractaleConfig.filename);
            System.out.println("Buffer "+pixelsBuffer);
            int width = fractaleConfig.width;
            int height = fractaleConfig.height;
            BufferedImage bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
            for(int i=0;i<width;i++){
                for(int j=0;j<height;j++){
                    bufferedImage.setRGB(i,j,pixelsBuffer.get(i*height+j));
                }
            }
            ImageIO.write(bufferedImage, "png", new File(fractaleConfig.filename+".png"));
            File configFile = new File(fractaleConfig.filename+".config");
            FileOutputStream fileOutputStream = new FileOutputStream(configFile);
            fileOutputStream.write(fractaleConfig.toString().getBytes());
            fileOutputStream.write(("\nExecution Time :"+executionTime+"ms\n").getBytes());
            System.out.println("Saving finished");
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Error while saving the image");
        }
    }
}
