package unalcol.agents.examples.reversi;

import java.util.ArrayList;
import java.;

/**
 *
 * @author Juan
 */
public class MiniMax {
    
    // turn impar es para jugadas del max
    public int bestMove(Tablero t, String color, int turn){
        ArrayList<Integer> moves = t.movimientos(color);
        int best = (turn%2==1)?0:Integer.MAX_VALUE;
        for(int i = 0; i < moves.size(); i+=2){
            Tablero thijo = makeMove(t, moves.get(i), moves.get(i+1), turn);
            int tScore = Score()
            best = (turn%2==1)?max(best,)()
        }
        return 0;
    }

    private Tablero makeMove(Tablero ta, int x, int y) {
        int[][] tablero = makeCopy(ta.t);
        
    }  

    private int[][] makeCopy(int[][] t) {
        int newT[][] = new int[t.length][t.length];
        for(int i = 0; i < t.length; i++){
            for(int j = 0; j < t.length; j++){
                newT[i][j] = t[i][j];
            }
        }
        return newT;
    }
    
     

    private int max(int a , int b) {
        if(a >= b) return a;
        else return b;
    }
    
}
