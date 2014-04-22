package cubo;

import java.util.ArrayList;

/**
 *
 * @author Estudiantes
 */
public class Giros {

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
        //System.out.println("->>>"+dir+" "+id);
        nuevo.get(3)[0][col] = (id == 2) ? actual.get(id)[2][(col + 2) % 2] : actual.get(id)[0][col];
        nuevo.get(3)[1][col] = (id == 2) ? actual.get(id)[1][(col + 2) % 2] : actual.get(id)[1][col];
        nuevo.get(3)[2][col] = (id == 2) ? actual.get(id)[0][(col + 2) % 2] : actual.get(id)[2][col];;
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
