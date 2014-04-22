package implementaciones;

import cubo.Cubo;
import cubo.Giros;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author Naty
 */
public class AstarPuzzle {

    static PriorityQueue<Node> Q;
    static int n;         // tamaño del tablero
    static Node inicial;  // estado inicial del tablero        
    static Node finall ;  //creación del Nodo-estado final
    
    public static void swap(int a[], int b[], int id1, int id2) {
        for (int i = 0; i < n * n; i++) {
            if (i == id1 - 1) {
                a[i] = b[id2 - 1];
            } else if (i == id2 - 1) {
                a[i] = b[id1 - 1];
            } else {
                a[i] = b[i];
            }
        }
    }
    
    // Busqueda usando A*
    public static int search() {
        HashSet<State> v = new HashSet<>();
        Giros mano = new Giros();
        ArrayList<int[][]>aux;
        
        finall = inicial.createGoalState();
        Q.add(inicial);
        Node actual;
        int cont = 0;
        
        while ( !(actual = Q.remove()).estado.equals(finall.estado) ) {
            
            if(v.contains(actual.estado)) 
                continue;
            System.out.println(actual.estado+" "+actual.costo_hasta_aqui+" "+actual.costo_total);
            System.out.println("->\n"+((NodeCubo)actual).getValue() );
            
            v.add(actual.estado);
            int ca = actual.costo_hasta_aqui + 1;
            
            //Realiza Movimientos
            for( int i=0; i<12; i++ ){
                aux = mano.girar( ((Cubo)actual.getValue()).getCubo(), i);
                Node nuevo = new NodeCubo(ca, ca+Heuristica.h1((Cubo)actual.getValue()), new Cubo( aux, ((Cubo)inicial.getValue()).getColors() ) );
                Q.add( (Node)nuevo );
            }
            cont++;
        }
        return cont;
    }

    public static void main(String args[]) throws IOException {
        
        File _= new File("input0");
        BufferedReader br = new BufferedReader( _.exists()?new FileReader(_):new InputStreamReader(System.in) );
        StringTokenizer st;
        String line = "";

        // Numero de Tableros
        n = Integer.parseInt(br.readLine());

        for (int casos = 0; casos < n ; casos++) {
            
            Cubo cubo = lectura(br);
            //Descomentar para ver como queda el Cubo
            //System.out.println("***CUBO***"+cubo);
            inicial = new NodeCubo(0, Heuristica.h1(cubo), cubo);
            System.out.println(cubo);
            Giros mano = new Giros();
            //Cubo caux = new Cubo(mano.girar(cubo.getCubo(), 4), cubo.getColors());
            //System.out.println(caux);
            //caux = new Cubo(mano.girar(cubo.getCubo(), 6), cubo.getColors());
            //System.out.println(caux);
            Cubo caux = new Cubo(mano.girar(cubo.getCubo(), 2), cubo.getColors());
            System.out.println(caux);
            /*Q = new PriorityQueue<>();         
            long time_init = System.currentTimeMillis();
            int pasos = search();
            long time_fin = System.currentTimeMillis();
            System.out.println("Caso "+casos+": Pasos-> "+pasos+" Estado inicial-> "+inicial.estado+" Tiempo t-> "+(time_fin-time_init));
            */
        }

    }

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
        
        return new Cubo(aux, color.toString());
    }

}
