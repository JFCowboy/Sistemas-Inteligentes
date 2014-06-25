package unalcol.agents.examples.reversi;

import java.util.ArrayList;

/**
 *
 * @author JuanFelipe
 */
class Tablero {

    static final int movX[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int movY[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    int[][] t;
    int tam;
    ArrayList<Integer> posicionBlancas;
    ArrayList<Integer> posicionNegras;

    public Tablero(int tam) {
        this(new int[tam][tam], tam);
    }

    public Tablero(int[][] tablero, int tam) {
        this.t = tablero;
        this.tam = tam;
        posicionBlancas = new ArrayList<>();
        posicionNegras = new ArrayList<>();
    }

    public void identificarFichas() {
        posicionBlancas.clear();
        posicionNegras.clear();
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (t[i][j] == 1) {
                    posicionBlancas.add(i);
                    posicionBlancas.add(j);
                } else if (t[i][j] == -1) {
                    posicionNegras.add(i);
                    posicionNegras.add(j);
                }
            }
        }
    }

    /**
     *
     * @param colorS
     * @return
     */
    ArrayList<Integer> movimientos(String colorS) {
        int color = colorS.equals(Reversi.WHITE) ? 1 : -1;
        ArrayList<Integer> posiciones = (color == 1) ? posicionBlancas : posicionNegras;
        ArrayList<Integer> movimientos = new ArrayList<>();

        int x, y;
        int posMov[];
        for (int i = 0; i < posiciones.size(); i += 2) {
            x = posiciones.get(i);
            y = posiciones.get(i + 1);
            for (int idx = 0; idx < 8; idx++) {
                posMov = mov(x + movX[idx], y + movY[idx], movX[idx], movY[idx], color);
                if (posMov[0] == 1) {
                    movimientos.add(posMov[1]);
                    movimientos.add(posMov[2]);
                }
            }

        }

        return movimientos;
    }

    /**
     * Devuelve 3 valores,
     * <p/>
     * el 1 si es valido ese movimiento
     * <p/>
     * el 2 la posicion x valida
     * <p/>
     * el 3 la posicion y valida
     *
     * @param x
     * @param y
     * @param i
     * @param i0
     * @return
     */
    private int[] mov(int x, int y, int movx, int movy, int color) {
        int[] ret = {0, -1, -1};
        int z = 0;
        int xo, yo;
        xo = x;
        yo = y;
        
        while (isValid(x, y) && t[x][y] == -color) {
            x += movx;
            y += movy;
            z = 1;
        }
        
        if (isValid(x, y) && t[x][y] == 0 && z == 1) {
            ret[0] = 1;
            ret[1] = x;
            ret[2] = y;
        }
        
        z = 0;
        return ret;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < tam && y >= 0 && y < tam;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n***\n");
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                sb.append("|" + t[i][j] + "|");
            }
            sb.append("\n");
        }
        sb.append("***\n\n");
        return sb.toString();
    }
}
