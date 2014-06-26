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

    ArrayList<Move> movimientos(String colorS) {
        int color = colorS.equals(Reversi.WHITE) ? 1 : -1;
        ArrayList<Integer> posiciones = (color == 1) ? posicionBlancas : posicionNegras;
        ArrayList<Move> movimientos = new ArrayList<>();
        int x, y;
        int posMov[];
        for (int i = 0; i < posiciones.size(); i += 2) {
            x = posiciones.get(i);
            y = posiciones.get(i + 1);
            for (int idx = 0; idx < 8; idx++) {
                posMov = mov(x + movX[idx], y + movY[idx], movX[idx], movY[idx], color);
                if (posMov[0] == 1) {
                    movimientos.add(new Move(x, y, posMov[1], posMov[2], idx));
                }
            }

        }
        return movimientos;
    }

    /**
     * Devuelve 3 valores, el 1 si es valido ese movimiento el 2 la posicion x
     * valida el 3 la posicion y valida
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
    
    
    Tablero makeMove(Move m, String color) {
        int[][] tablero = makeCopy();
        flipCoins(tablero, m, (color.equals(Reversi.WHITE))?1:-1);
        return new Tablero(tablero, tablero.length); 
    }  
    
     private int[][] makeCopy() {
        int newT[][] = new int[tam][tam];
        for(int i = 0; i < t.length; i++){
            for(int j = 0; j < t.length; j++){
                newT[i][j] = t[i][j];
            }
        }
        return newT;
    }  
     
     public void flipCoins(int[][] t, Move m, int v){
         int x = m.desde[0];
         int y = m.desde[1];
         while( (x!= m.hasta[0] && y!=m.hasta[1]) || (x== m.hasta[0] && y!=m.hasta[1]) || (x!= m.hasta[0] && y==m.hasta[1])) {
             t[x][y] = v;
             x+= movX[m.direction];
             y+= movY[m.direction];
         }    
         t[x][y] = v;
     }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                sb.append("|" + t[i][j] + "|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
