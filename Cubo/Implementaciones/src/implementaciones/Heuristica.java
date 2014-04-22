package implementaciones;

import cubo.Cubo;

/**
 *
 * @author Naty
 */
public class Heuristica {
    
    // number of misplaced tiles
    static int h1(int a[]){
        int cont = 0;
        for(int i = 0; i < a.length; i++ )
            if(a[i]!=9 && a[i]!= (i+1)) cont++;
        return cont;
    }
    
    // total Manhattan distance
    static int h2(int a[]){
        int cont = 0;
        for(int i = 0; i < a.length; i++ )
            if(a[i]!=9 && a[i]!= (i+1)){
                
                // fila , columna actual
                int fila = i/3;
                int columna = i%3;
                
                //fila , columna donde debería estar el num
                int filanum = (a[i]-1)/3;
                int colnum  = (a[i]-1)%3;
                
                cont+= Math.abs(fila-filanum) + Math.abs(columna - colnum);
                
            }           
        
        return cont;
    }
    
    // se pueden realizar a nivel de filas y columnas, corrimientos     
    static int h3(int a[]){
        int cont = 0;
        for(int i = 0; i < a.length; i++ )
            if(a[i]!=9 && a[i]!= (i+1)){
                
                // fila , columna actual
                int fila = i/3;
                int columna = i%3;
                
                //fila , columna donde debería estar el num
                int filanum = (a[i]-1)/3;
                int colnum  = (a[i]-1)%3;
                
                cont+= (Math.abs(fila-filanum)>=1?1:0) + (Math.abs(columna - colnum)>=1?1:0);
                
            }           
        
        return cont;
    }

    static int h1(Cubo cubo) {
        return 1;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
