package implementaciones;

import java.util.Arrays;

/**
 *
 * @author Naty
 */
public class Nodo implements Comparable<Nodo>{
    public int[] valores; // elementos del cuadro, row-order
    int espacio;          // posicion donde se encuentra el espacio en el arreglo valores
    String estado;        // representa el arreglo de valores a través de un string
    int costo_total;      // costo actual
    int costo_hasta_aqui; // costo de llegar hasta n
    
    public Nodo(int v[], int e, int costo_aqui,  int costo) {
        valores = v;
        espacio = e;
        estado = "";
        for(int i = 0; i < valores.length; i++) estado+=""+valores[i];
        costo_hasta_aqui = costo_aqui;        
        costo_total = costo;        
    }

    @Override
    public int compareTo(Nodo o) {
        return costo_total -  o.costo_total  ;        
    }
}
