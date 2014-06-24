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
        String color = "RBOGYW";//"012345";
        Cubo cubo = new Cubo( finall , color, 0);
        System.out.println("***\n"+cubo);
        
        //Generar los diferentes giros que se le aplicaran al cubo
        int nGiros = 30;
        int moves[] = new int[nGiros];
        Random ran = new Random();
        
        for(int i = 0 ; i < nGiros ; i++){
            moves[ i ] =  ran.nextInt(12);
        }
        
        Giros mano = new Giros();
        
        for(int i=0; i<nGiros; i++){
            int giro = moves[i];
            //System.out.println("Se hizo un giro "+giro);
            finall = mano.girar(cubo.getCubo(), giro);
            Cubo cuboAux = new Cubo( finall, color, cubo.dist+1 );
            cubo = cuboAux;
            //System.out.println("******************"+cubo);
            
        }
        System.out.println(cubo);
        
    }   
}
