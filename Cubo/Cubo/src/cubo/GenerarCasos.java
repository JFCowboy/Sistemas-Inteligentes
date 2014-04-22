package cubo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Estudiantes
 */
public class GenerarCasos {
    
    
    public static void main(String args[]){
        
        //construcción cubo armado
        ArrayList<int[][]> finall = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            int[][] cara = new int[3][3];
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    cara[j][k] = i;                    
                }
            }
            finall.add(cara);
        }       
        String color = "RBOGYW";
        Cubo cubo = new Cubo( finall , color);
        System.out.println("***\n"+cubo);
        
        int nGiros = 7;
        int moves[] = {0,4,5,6,4,0,0};
        Giros mano = new Giros();
        
        for(int i=0; i<nGiros; i++){
            int giro = moves[i];
            System.out.println("Se hizo un giro "+giro);
            finall = mano.girar(cubo.getCubo(), giro);
            Cubo cuboAux = new Cubo( finall, color );
            cubo = cuboAux;
            System.out.println("******************"+cubo);
            
        }
        
    }   
}
