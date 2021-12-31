package org.fractale;

import javafx.concurrent.Task;

import java.awt.*;
import java.nio.IntBuffer;
/**
 * class ComputeTask .
 * class qui permet de lancer des calculs partiels sur l'ensemble Fractale
 * cette classe a été utilisé pour permettre de lancer plusieurs threads de calcul
 * dans le meme fractaleGenerator
 *
 */
public class ComputeTask extends Task<IntBuffer> {
    private final int xStart;
    private final int nbRows;
    private Fractale fractale;
    private IntBuffer pixelsBuffer;
    /**
     * Constructeur de ComputeTask .
     *
     * @param fractale L'ensemble fractale sur lequelle le calcul sera fait
     * @param xStart la ligne de debut de calcul pour ce thread
     * @param nbRows nombre de ligne a calculer
     */
    public ComputeTask(Fractale fractale, int xStart,int nbRows){
        this.fractale=fractale;
        this.pixelsBuffer = IntBuffer.allocate(fractale.getHeight()*nbRows);
        this.xStart = xStart;
        this.nbRows = nbRows;
    }
    @Override
    protected IntBuffer call() throws Exception {
        pixelsBuffer = IntBuffer.allocate(nbRows*fractale.getHeight());
        for(int i=xStart;i<xStart+nbRows;i++){

            for(int j=0;j<fractale.getHeight();j++){
                Complexe z = fractale.getStartingPoint(i,j);
                Complexe constant = fractale.getConstant(i,j);
                int indice = fractale.divergenceIndex(z,constant);
                Color c = fractale.getPalette().function.apply(indice,fractale.getMaxIter());
                pixelsBuffer.put(c.getRGB());
            }

        }
        System.out.println("Finished working for "+xStart+" to "+(xStart+nbRows));
        pixelsBuffer.flip();
        return pixelsBuffer;
    }

    public IntBuffer getIntBuffer() {
        return this.pixelsBuffer;
    }
}
