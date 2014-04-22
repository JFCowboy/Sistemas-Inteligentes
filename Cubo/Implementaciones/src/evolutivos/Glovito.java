package evolutivos;

import java.util.ArrayList;
import unalcol.types.collection.bitarray.BitArray;

/**
 *
 * @author JuanFelipe
 */
public class Glovito {
    private int [][] path;
    private int [][] out;
    
    public Glovito(int[][] path, int[][]out) {
        this.path = path;
        this.out = out;
    }

    public int[][] getPath() {
        return path;
    }

    public void setPath(int[][] path) {
        this.path = path;
    }
    
    public int[][] getT() {
        return path;
    }

    public void setT(int[][] path) {
        this.path = path;
    }
    
    public int[][] getOut() {
        return out;
    }

    public void setOut(int[][] out) {
        this.out = out;
    }
    
    public int[] simulate( int[] in ){
        
        int idx = 0, tam = in.length;
        String aux;
        int[] produccion = new int[tam-1];
        
        for (int i : in) {
            produccion[idx] = path[idx][i];
        }
        
        System.out.println("Produccion: ");
        for(int i: produccion){
            System.out.print(" "+i);
        }
        
        return produccion;
    }
    
}
