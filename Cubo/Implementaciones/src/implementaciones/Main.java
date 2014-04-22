package implementaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 *
 * @author JuanFelipe
 */
public class Main {
    
    public static class Cubo {
    
    ArrayList<int[][]>cubo;
    String colors;
    
    public Cubo() {
    }

    public Cubo(ArrayList<int[][]> cubo, String colors) {
        this.cubo = cubo;
        this.colors = colors;
    }

    public ArrayList<int[][]> getCubo() {
        return cubo;
    }

    public void setCubo(ArrayList<int[][]> cubo) {
        this.cubo = cubo;
    }

    @Override
    public String toString() {
        return "Cubo{" + "cubo=" + stringCubo() + '}';
    }

    private String stringCubo() {
        StringBuilder aux, out = new StringBuilder("\n");
        String space;
        int[] ord = {5, 0, 4, 2};
        int idx = 0, idxAux;
        
        aux = new StringBuilder("");
        for(int i = 0; i < 3; i++) {
            aux.append("  ");
        }
        
        //Agregar Parte de Arriba 
        idxAux = 1;
        for(int i = 0; i < 3; i++) {
            out.append(aux.toString());
            for(int j = 0; j < 3; j++) {
                out.append(" ");
                out.append( colors.charAt( cubo.get( idxAux )[ i ][ j ] ) );
            }
            out.append("\n");
        }
        
        //Agregar parte del centro
        for(int i = 0; i < 3; i++){
            for(int k = 0; k < 4; k++) {
                idxAux = ord[ idx ];
                idx = (idx + 1) % 4;
                for(int j = 0; j < 3; j++) {
                    out.append(" ");
                    out.append( colors.charAt( cubo.get( idxAux )[i][j] ) );
                }
                out.append("|");
            }
            out.append("\n");
        }
        
        //Agregar Parte de Abajo
        idxAux = 3;
        for(int i = 0; i < 3; i++) {
            out.append( aux.toString() );
            for(int j = 0; j < 3; j++) {
                out.append(" ");
                out.append( colors.charAt( cubo.get( idxAux )[ i ][ j ] ) );
            }
            out.append("\n");
        }
        
        return out.toString();
    }

    public String getColors() {
        return this.colors;
    }
    
    public void setColors(String string){
        this.colors = string;
    }

        @Override
        public int hashCode() {
            int hash = 5;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Cubo other = (Cubo) obj;
            for(int i=0; i<6; i++){
                for(int j=0; j<3; j++){
                    for(int k=0; k<3; k++){
                        if( this.cubo.get(i)[j][k]!= other.cubo.get(i)[j][k])
                            return false;
                    }
                }
            }
            return true;
        }
    
}

    
    public static class Giros {

    /**
     * Gira el cubo segun la direccion que se elija <p/>* 0 cara de arriba a la
     * Derecha <p/>* 1 cara de arriba a la Izquierda <p/>* 2 cara de abajo a la Derecha
     * <p/>* 3 cara de abajo a la Izquierda <p/>* 4 cara de Derecha a la Derecha <p/>* 5
     * cara de Derecha a la Izquierda <p/>* 6 cara de Izquierda a la Derecha <p/>* 7
     * cara de Izquierda a la Izquierda <p/>* 8 cara de Frente a la Derecha <p/>* 9 cara
     * de Frente a la Izquierda <p/>* 10 cara de Atras a la Derecha <p/>* 11 cara de
     * Atras a la Izquierda en caso que la direccion no sea valida devuelve null
     *
     * @param actual
     * @param dir
     * @return
     */
    public ArrayList<int[][]> girar(ArrayList<int[][]> actual, int dir) {
        //Puede ser mejor devolver el arreglo original si la direccion no es valida
        //System.out.println("ACA "+ dir);
        ArrayList<int[][]> ret = null;
        switch (dir) {
            case 0:
                ret = AR_AB(actual, "arriba", 1);
                break;
            case 1:
                ret = AR_AB(actual, "arriba", -1);
                break;
            case 2:
                ret = AR_AB(actual, "abajo", 1);
                break;
            case 3:
                ret = AR_AB(actual, "abajo", -1);
                break;
            case 4:
                ret = LD_LI(actual, "derecha", 1);
                break;
            case 5:
                ret = LD_LI(actual, "derecha", -1);
                break;
            case 6:
                ret = LD_LI(actual, "izquierda", 1);
                break;
            case 7:
                ret = LD_LI(actual, "izquierda", -1);
                break;
            case 8:
                ret = F_A(actual, "frente", 1);
                break;
            case 9:
                ret = F_A(actual, "frente", -1);
                break;
            case 10:
                ret = F_A(actual, "atras", 1);
                break;
            case 11:
                ret = F_A(actual, "atras", -1);
                break;
        }

        return ret;
    }

    public static ArrayList<int[][]> AR_AB(ArrayList<int[][]> actual, String op, int dir) {
        ArrayList<int[][]> nuevo = copia(actual);
        int fila = (op.equals("arriba")) ? 0 : 2;
        // FRENTE
        int id = (dir == 1) ? 5 : 4;
        nuevo.get(0)[fila][0] = actual.get(id)[fila][0];
        nuevo.get(0)[fila][1] = actual.get(id)[fila][1];
        nuevo.get(0)[fila][2] = actual.get(id)[fila][2];
        // DERECHO
        id = (dir == 1) ? 0 : 2;
        nuevo.get(4)[fila][0] = actual.get(id)[fila][0];
        nuevo.get(4)[fila][1] = actual.get(id)[fila][1];
        nuevo.get(4)[fila][2] = actual.get(id)[fila][2];
        // ATRAS
        id = (dir == 1) ? 4 : 5;
        nuevo.get(2)[fila][0] = actual.get(id)[fila][0];
        nuevo.get(2)[fila][1] = actual.get(id)[fila][1];
        nuevo.get(2)[fila][2] = actual.get(id)[fila][2];
        // IZQUIERDA
        id = (dir == 1) ? 2 : 0;
        nuevo.get(5)[fila][0] = actual.get(id)[fila][0];
        nuevo.get(5)[fila][1] = actual.get(id)[fila][1];
        nuevo.get(5)[fila][2] = actual.get(id)[fila][2];
        // SUP/INF
        int cara = (op.equals("arriba")) ? 1 : 3;
        if( cara==1 ){
            if (dir == 1) {
                nuevo.get(cara)[0][0] = actual.get(cara)[0][2];
                nuevo.get(cara)[0][1] = actual.get(cara)[1][2];
                nuevo.get(cara)[0][2] = actual.get(cara)[2][2];
                nuevo.get(cara)[1][0] = actual.get(cara)[0][1];
                nuevo.get(cara)[1][2] = actual.get(cara)[2][1];
                nuevo.get(cara)[2][0] = actual.get(cara)[0][0];
                nuevo.get(cara)[2][1] = actual.get(cara)[1][0];
                nuevo.get(cara)[2][2] = actual.get(cara)[2][0];            
            } else {                        
                nuevo.get(cara)[0][0] = actual.get(cara)[2][0];
                nuevo.get(cara)[0][1] = actual.get(cara)[1][0];
                nuevo.get(cara)[0][2] = actual.get(cara)[0][0];
                nuevo.get(cara)[1][0] = actual.get(cara)[2][1];
                nuevo.get(cara)[1][2] = actual.get(cara)[0][1];
                nuevo.get(cara)[2][0] = actual.get(cara)[2][2];
                nuevo.get(cara)[2][1] = actual.get(cara)[1][2];
                nuevo.get(cara)[2][2] = actual.get(cara)[0][2];                        
            }
        }else{
            if (dir != 1) {
                nuevo.get(cara)[0][0] = actual.get(cara)[0][2];
                nuevo.get(cara)[0][1] = actual.get(cara)[1][2];
                nuevo.get(cara)[0][2] = actual.get(cara)[2][2];
                nuevo.get(cara)[1][0] = actual.get(cara)[0][1];
                nuevo.get(cara)[1][2] = actual.get(cara)[2][1];
                nuevo.get(cara)[2][0] = actual.get(cara)[0][0];
                nuevo.get(cara)[2][1] = actual.get(cara)[1][0];
                nuevo.get(cara)[2][2] = actual.get(cara)[2][0];            
            } else {                        
                nuevo.get(cara)[0][0] = actual.get(cara)[2][0];
                nuevo.get(cara)[0][1] = actual.get(cara)[1][0];
                nuevo.get(cara)[0][2] = actual.get(cara)[0][0];
                nuevo.get(cara)[1][0] = actual.get(cara)[2][1];
                nuevo.get(cara)[1][2] = actual.get(cara)[0][1];
                nuevo.get(cara)[2][0] = actual.get(cara)[2][2];
                nuevo.get(cara)[2][1] = actual.get(cara)[1][2];
                nuevo.get(cara)[2][2] = actual.get(cara)[0][2];                        
            }
        }
        return nuevo;
    }

    public static ArrayList<int[][]> LD_LI(ArrayList<int[][]> actual, String op, int dir) {
        ArrayList<int[][]> nuevo = copia(actual);
        //System.out.println("ACA2 " +op+" "+dir);
        int col = (op.equals("derecha")) ? 2 : 0;
        // FRENTE
        int id = (dir == 1) ? 3 : 1;
        nuevo.get(0)[0][col] = (id == 3) ? actual.get(id)[0][col] : actual.get(id)[0][col];
        nuevo.get(0)[1][col] = (id == 3) ? actual.get(id)[1][col] : actual.get(id)[1][col];
        nuevo.get(0)[2][col] = (id == 3) ? actual.get(id)[2][col] : actual.get(id)[2][col];
        // ARRIBA
        id = (dir == 1) ? 0 : 2;
        nuevo.get(1)[0][col] = (id == 0) ? actual.get(id)[0][col] : actual.get(id)[2][(col + 2) % 4];
        nuevo.get(1)[1][col] = (id == 0) ? actual.get(id)[1][col] : actual.get(id)[1][(col + 2) % 4];
        nuevo.get(1)[2][col] = (id == 0) ? actual.get(id)[2][col] : actual.get(id)[0][(col + 2) % 4];
        // ATRAS
        id = (dir == 1) ? 1 : 3;
        nuevo.get(2)[0][(col + 2) % 4] = actual.get(id)[2][col];
        nuevo.get(2)[1][(col + 2) % 4] = actual.get(id)[1][col];
        nuevo.get(2)[2][(col + 2) % 4] = actual.get(id)[0][col];
        // ABAJO
        id = (dir == 1) ? 2 : 0;
        //System.out.println("->>>>"+id+" "+dir+" "+col);
        nuevo.get(3)[0][col] = (id == 2) ? actual.get(id)[2][(col + 2) % 4] : actual.get(id)[0][col];
        nuevo.get(3)[1][col] = (id == 2) ? actual.get(id)[1][(col + 2) % 4] : actual.get(id)[1][col];
        nuevo.get(3)[2][col] = (id == 2) ? actual.get(id)[0][(col + 2) % 4] : actual.get(id)[2][col];
        // LADOS
        int cara = (op.equals("derecha")) ? 4 : 5;
        if ((dir == 1 && cara == 4) || (dir == -1 && cara == 5)) {
            nuevo.get(cara)[0][0] = actual.get(cara)[2][0];
            nuevo.get(cara)[0][1] = actual.get(cara)[1][0];
            nuevo.get(cara)[0][2] = actual.get(cara)[0][0];
            nuevo.get(cara)[1][0] = actual.get(cara)[2][1];
            nuevo.get(cara)[1][2] = actual.get(cara)[0][1];
            nuevo.get(cara)[2][0] = actual.get(cara)[2][2];
            nuevo.get(cara)[2][1] = actual.get(cara)[1][2];
            nuevo.get(cara)[2][2] = actual.get(cara)[0][2];
        }
        if ((dir == 1 && cara == 5) || (dir == -1 && cara == 4)) {
            nuevo.get(cara)[0][0] = actual.get(cara)[0][2];
            nuevo.get(cara)[0][1] = actual.get(cara)[1][2];
            nuevo.get(cara)[0][2] = actual.get(cara)[2][2];
            nuevo.get(cara)[1][0] = actual.get(cara)[0][1];
            nuevo.get(cara)[1][2] = actual.get(cara)[2][1];
            nuevo.get(cara)[2][0] = actual.get(cara)[0][0];
            nuevo.get(cara)[2][1] = actual.get(cara)[1][0];
            nuevo.get(cara)[2][2] = actual.get(cara)[2][0];
        }
        return nuevo;
    }

    public static ArrayList<int[][]> F_A(ArrayList<int[][]> actual, String op, int dir) {
        ArrayList<int[][]> nuevo = copia(actual);
        int fila = (op.equals("frente")) ? 2 : 0;
        // ARRIBA
        int id = (dir == 1) ? 5 : 4;
        nuevo.get(1)[fila][0] = (id == 5) ? actual.get(id)[2][fila] : actual.get(id)[0][(fila + 2) % 4];
        nuevo.get(1)[fila][1] = (id == 5) ? actual.get(id)[1][fila] : actual.get(id)[1][(fila + 2) % 4];
        nuevo.get(1)[fila][2] = (id == 5) ? actual.get(id)[0][fila] : actual.get(id)[2][(fila + 2) % 4];
        // ABAJO
        id = (dir == 1) ? 4 : 5;
        nuevo.get(3)[(fila + 2) % 4][0] = (id == 4) ? actual.get(id)[2][(fila + 2) % 4] : actual.get(id)[0][fila];
        nuevo.get(3)[(fila + 2) % 4][1] = (id == 4) ? actual.get(id)[1][(fila + 2) % 4] : actual.get(id)[1][fila];
        nuevo.get(3)[(fila + 2) % 4][2] = (id == 4) ? actual.get(id)[0][(fila + 2) % 4] : actual.get(id)[2][fila];
        // DERECHA
        id = (dir == 1) ? 1 : 3;
        nuevo.get(4)[0][(fila + 2) % 4] = (id == 1) ? actual.get(id)[fila][0] : actual.get(id)[(fila + 2) % 4][2];
        nuevo.get(4)[1][(fila + 2) % 4] = (id == 1) ? actual.get(id)[fila][1] : actual.get(id)[(fila + 2) % 4][1];
        nuevo.get(4)[2][(fila + 2) % 4] = (id == 1) ? actual.get(id)[fila][2] : actual.get(id)[(fila + 2) % 4][0];
        // IZQUIERDA
        id = (dir == 1) ? 3 : 1;
        
        nuevo.get(5)[0][fila] = (id == 3) ? actual.get(id)[(fila + 2) % 4][0] : actual.get(id)[fila][2];
        nuevo.get(5)[1][fila] = (id == 3) ? actual.get(id)[(fila + 2) % 4][1] : actual.get(id)[fila][1];
        nuevo.get(5)[2][fila] = (id == 3) ? actual.get(id)[(fila + 2) % 4][2] : actual.get(id)[fila][0];
        // FRENTE/ATRAS
        //System.out.println("->>>>"+dir+" "+id);
        int cara = (op.equals("frente")) ? 0 : 2;
        if ((dir == 1 && cara == 0) || (dir == -1 && cara == 2)) {
            nuevo.get(cara)[0][0] = actual.get(cara)[2][0];
            nuevo.get(cara)[0][1] = actual.get(cara)[1][0];
            nuevo.get(cara)[0][2] = actual.get(cara)[0][0];
            nuevo.get(cara)[1][0] = actual.get(cara)[2][1];
            nuevo.get(cara)[1][2] = actual.get(cara)[0][1];
            nuevo.get(cara)[2][0] = actual.get(cara)[2][2];
            nuevo.get(cara)[2][1] = actual.get(cara)[1][2];
            nuevo.get(cara)[2][2] = actual.get(cara)[0][2];
        }
        if ((dir == 1 && cara == 2) || (dir == -1 && cara == 0)) {
            nuevo.get(cara)[0][0] = actual.get(cara)[0][2];
            nuevo.get(cara)[0][1] = actual.get(cara)[1][2];
            nuevo.get(cara)[0][2] = actual.get(cara)[2][2];
            nuevo.get(cara)[1][0] = actual.get(cara)[0][1];
            nuevo.get(cara)[1][2] = actual.get(cara)[2][1];
            nuevo.get(cara)[2][0] = actual.get(cara)[0][0];
            nuevo.get(cara)[2][1] = actual.get(cara)[1][0];
            nuevo.get(cara)[2][2] = actual.get(cara)[2][0];
        }
        return nuevo;
    }

    public static ArrayList<int[][]> copia(ArrayList<int[][]> actual) {
        ArrayList<int[][]> nuevo = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int[][] cara = new int[3][3];
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cara[j][k] = actual.get(i)[j][k];
                }
            }
            nuevo.add(cara);
        }
        return nuevo;
    }
}

    public static void main(String args[]) throws IOException {
        
        File _= new File("input0");
        BufferedReader br = new BufferedReader( _.exists()?new FileReader(_):new InputStreamReader(System.in) );
        StringTokenizer st;
        String line = "";
        Cubo objetivo;

        // Numero de Tableros
        int n = Integer.parseInt(br.readLine());

        for (int casos = 0; casos < n ; casos++) {
            
            Cubo cubo = lectura(br);
            objetivo = createGoalState( cubo.colors );
            
            Giros mano = new Giros();
            st = new StringTokenizer( br.readLine() );
            
            while( st.hasMoreTokens() ){
                line = st.nextToken();
                int num = Integer.parseInt(line);
                if( num==0 )
                    break;
                int aux;
                
                switch( num ){
                    case -1:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 6), cubo.getColors());
                        break;
                    case 2:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 8), cubo.getColors());
                        break;
                    case 3:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 4), cubo.getColors());
                        break;
                    case -4:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 10), cubo.getColors());
                        break;
                    case -5:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 0), cubo.getColors());
                        break;
                    case 6:
                        cubo = new Cubo(mano.girar(cubo.getCubo(),2), cubo.getColors());
                        break;
                    case 1:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 7), cubo.getColors());
                        break;
                    case -2:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 9), cubo.getColors());
                        break;
                    case -3:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 5), cubo.getColors());
                        break;
                    case 4:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 11), cubo.getColors());
                        break;
                    case 5:
                        cubo = new Cubo(mano.girar(cubo.getCubo(), 1), cubo.getColors());
                        break;
                    case -6:
                        cubo = new Cubo(mano.girar(cubo.getCubo(),3), cubo.getColors());
                        break;
                }
                //System.out.println("MOV: "+num);
                //System.out.println(cubo);
            }
            //System.out.println(objetivo);
            if (cubo.equals(objetivo) )
                System.out.println("Yes, grandpa!");
            else
                System.out.println("No, you are wrong!");
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
    
    public static Cubo createGoalState( String colors ) {
        
        ArrayList<int[][]> finall = new ArrayList<>();
        int[] idx = {2, 0, 4, 5, 3, 1};
        //System.out.println("->>"+colors);
        for(int i = 0; i < 6; i++){
            int[][] cara = new int[3][3];
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    cara[j][k] = idx[i];                    
                }
            }
            finall.add(cara);
        }
                       
        return new  Cubo(finall, colors);
    }
}
