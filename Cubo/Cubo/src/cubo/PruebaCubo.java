package cubo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author JuanFelipe
 */
public class PruebaCubo {
    
    private static Cubo lectura(BufferedReader br) throws IOException {
        ArrayList<int[][]>aux = new ArrayList<>();
        ArrayList<char[][]>auxS = new ArrayList<>();
        StringTokenizer st;
        int[][] cara = null;
        char[][] caraCol = null;
        char[][][] mCaraCol = null;
        
        String in;
        StringBuilder color = new StringBuilder("");
        
        //Leer primer cara
        caraCol = new char[3][3];
        for (int i = 0; i < 3; i++) {
            in = br.readLine().trim();
            st = new StringTokenizer(in);
            
            for(int k = 0; k < 3; k++){
                String s= st.nextToken();
                caraCol[i][k] = s.charAt(0);

                if( i==1 && k==1 ){
                    color.append(caraCol[i][k]);
                }
            }
            
        }
        
        mCaraCol = new char[4][3][3];
        //Leer parte del centro
        for (int i = 0; i < 3; i++) {
            in = br.readLine().trim();
            st = new StringTokenizer(in);
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 3; k++){
                    String s= st.nextToken();
                    mCaraCol[j][i][k] = s.charAt(0);

                    if( i==1 && k==1 ){
                        color.append(mCaraCol[j][i][k]);
                    }
                }
            }
        }
        //Ingresa cara del frente
        auxS.add(mCaraCol[1]);
        //Ingresa cara de arriba
        auxS.add(caraCol);
        //Ingresa cara de Atras
        auxS.add(mCaraCol[3]);
        
        //Leer cara de abajo
        caraCol = new char[3][3];
        for (int i = 0; i < 3; i++) {
            in = br.readLine().trim();
            st = new StringTokenizer(in);
            
            for(int k = 0; k < 3; k++){
                String s= st.nextToken();
                caraCol[i][k] = s.charAt(0);

                if( i==1 && k==1 ){
                    color.append(caraCol[i][k]);
                }
            }
        }
        //Ingresa cara de Abajo
        auxS.add(caraCol);
        //Ingresa cara de derecha
        auxS.add(mCaraCol[2]);
        //Ingresa cara de Izquierda
        auxS.add(mCaraCol[0]);
        
        for (int i = 0; i < 6; i++) {
            
            cara = new int[3][3];
            caraCol = auxS.get(i);
            
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    cara[j][k] = color.indexOf(caraCol[j][k]+"");
                }
            }
            
            aux.add(cara);
        }
        return new Cubo(aux, color.toString(), 0);
    }
    
    public static void main(String args[]) throws IOException {
        
        File _= new File("input0");
        BufferedReader br = new BufferedReader( _.exists()
                                    ?new FileReader(_)
                                    :new InputStreamReader(System.in) );
        StringTokenizer st;
        String line = "";
        Cubo cubo;
        Busqueda busqueda;
        
        // Numero de Tableros
        int n = Integer.parseInt(br.readLine());

        for (int casos = 0; casos < n ; casos++) {
            cubo = lectura(br);
            StringBuilder aux = new StringBuilder( cubo.colors );
//            aux.append(cubo.colors.charAt(2));
//            aux.append(cubo.colors.charAt(0));
//            aux.append(cubo.colors.charAt(4));
//            aux.append(cubo.colors.charAt(5));
//            aux.append(cubo.colors.charAt(3));
//            aux.append(cubo.colors.charAt(1));
            
            busqueda = new Busqueda(aux.toString());
            
            if( busqueda.AStar(cubo) ){
                System.out.println("Se encontro Respuesta: "+busqueda.getCnt());
            } else {
                System.out.println(":(");
            }
            
        }

     }
    
    
}
