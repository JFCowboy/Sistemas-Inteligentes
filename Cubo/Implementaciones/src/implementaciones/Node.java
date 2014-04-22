package implementaciones;

/**
 *
 * @author JuanFelipe
 */
public abstract class Node implements Comparable<Node> {
    //public int[] valores; // elementos del cuadro, row-order
    //private Object valores;
    //int espacio;          // posicion donde se encuentra el espacio en el arreglo valores
    State estado;        // representa el arreglo de valores a través de un string
    int costo_total;      // costo actual
    int costo_hasta_aqui; // costo de llegar hasta n
    
    public Node(int costo_aqui,  int costo) {
        
        //for(int i = 0; i < valores.length; i++) estado+=""+valores[i];
        costo_hasta_aqui = costo_aqui;
        costo_total = costo;
    }
    
    @Override
    public int compareTo(Node o) {
        return costo_total -  o.costo_total  ;
    }
    
    public abstract Node createGoalState();
    
    /**
     * Devuelve El valor o elemento que representa el Nodo/estado
     * @return 
     */
    public abstract Object getValue();
}
