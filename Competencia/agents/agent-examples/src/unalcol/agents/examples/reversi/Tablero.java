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
        //flipCoins(tablero, m, (color.equals(Reversi.WHITE))?1:-1);
        play(tablero, m, (color.equals(Reversi.WHITE))?1:-1);
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

    /**
     * Propuesta para remplazar el flipCoins, 
     * este evalua todos los posibles cambios
     * @param t
     * @param m
     * @param val
     * @return 
     */
    public boolean play( int[][]t, Move m, int val ){
        int x = m.desde[0];
        int y = m.desde[1];
        if( t[x][y] != 0 ) return false;
        boolean flag1 = l_play(t, x, y, val);
        boolean flag2 = u_play(t, x, y, val);
        boolean flag3 = r_play(t, x, y, val);
        boolean flag4 = d_play(t, x, y, val);
        boolean flag5 = lu_play(t, x, y, val);
        boolean flag6 = ld_play(t, x, y, val);
        boolean flag7 = ru_play(t, x, y, val);
        boolean flag8 = rd_play(t, x, y, val);
        boolean flag = flag1 || flag2 || flag3 || flag4 || flag5 || flag6 || flag7 || flag8;
        if( flag ){
            t[x][y] = val;
        }
        return flag;
    }
    
    public boolean l_play( int[][]t, int i, int j, int val ){
        int nval = -val;
        int j1=j-1;
        while( j1>=0 && t[i][j1] == nval ){
            j1--;
        }
        if( j1>=0 && j1+1 < j && t[i][j1] == val ){
            // Valid play
            for( int k=j1+1; k<j; k++ ){
                t[i][k] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean u_play( int[][]t, int i, int j, int val ){
        int nval = -val;
        int i1 = i-1;
        while( i1>=0 && t[i1][j] == nval ){
            i1--;
        }
        if( i1>=0 && i1+1 < i && t[i1][j] == val ){
            // Valid play
            for( int k=i1+1; k<i; k++ ){
                t[k][j] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean r_play(int[][]t, int i, int j, int val ){
        int nval = -val;
        int j1=j+1;
        while( j1<t[0].length && t[i][j1] == nval ){
            j1++;
        }
        if( j1<t[0].length && j+1 < j1 && t[i][j1] == val ){
            // Valid play
            for( int k=j+1; k<j1; k++ ){
                t[i][k] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean d_play( int[][]t, int i, int j, int val ){
        int nval = -val;
        int i1 = i+1;
        while( i1<t.length && t[i1][j] == nval ){
            i1++;
        }
        if( i1<t.length && i+1<i1 && t[i1][j] == val ){
            // Valid play
            for( int k=i+1; k<i1; k++ ){
                t[k][j] = val;
            }
            return true;
        }
        return false;        
    }
    
    public boolean lu_play( int[][]t, int i, int j, int val ){
        int nval = -val;
        int i1 = i-1;
        int j1=j-1;
        while( i1>=0 && j1>=0 && t[i1][j1] == nval ){
            i1--;
            j1--;
        }
        if( i1>=0 && j1>=0 && i1+1 < i && t[i1][j1] == val ){
            // Valid play
            for( int k=i1+1; k<i; k++ ){
                j--;
                t[k][j] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean ru_play( int[][]t, int i, int j, int val ){
        int nval = -val;
        int i1 = i-1;
        int j1=j+1;
        while( i1>=0 && j1<t[0].length && t[i1][j1] == nval ){
            i1--;
            j1++;
        }
        if( i1>=0 && i1+1 < i && j1<t[0].length && t[i1][j1] == val ){
            // Valid play
            for( int k=i1+1; k<i; k++ ){
                j++;
                t[k][j] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean ld_play( int[][]t, int i, int j, int val ){
        int nval = -val;
        int i1 = i+1;
        int j1=j-1;
        while( i1<t.length && j1>=0 && t[i1][j1] == nval ){
            i1++;
            j1--;
        }
        if( i1<t.length && j1>=0 && i+1 < i1 && t[i1][j1] == val ){
            // Valid play
            for( int k=i+1; k<i1; k++ ){
                j--;
                t[k][j] = val;
            }
            return true;
        }
        return false;        
    }
    
    public boolean rd_play( int[][]t, int i, int j, int val ){
        int nval = -val;
        int i1 = i+1;
        int j1=j+1;
        while( i1<t.length && j1<t[0].length && t[i1][j1] == nval ){
            i1++;
            j1++;
        }
        if( i1<t.length && j1<t[0].length && i+1 < i1 && t[i1][j1] == val ){
            // Valid play
            for( int k=i+1; k<i1; k++ ){
                j++;
                t[k][j] = val;
            }
            return true;
        }
        return false;        
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
