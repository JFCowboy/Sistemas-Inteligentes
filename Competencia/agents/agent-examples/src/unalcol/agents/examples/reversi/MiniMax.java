package unalcol.agents.examples.reversi;

import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class MiniMax {

    // turn impar es para jugadas del max
    // retorna indice del movimiento a hacer y su score según la heuristica
    public int[] bestMove(Tablero t, String color, int turn, int K) {

        if (turn > K || t.posicionBlancas.size() == 0 || t.posicionNegras.size() == 0) {
            int score = Score(t, color);
            return new int[]{0,score};

        }
        ArrayList<Move> moves = t.movimientos(color);
        int best = (turn % 2 == 1) ? 0 : Integer.MAX_VALUE;
        int bestMoveIndex = -1;
        int id = 0;
        for (int i = 0; i < moves.size(); i++) {
            if(id>4) break;
            id++;
            Tablero tHijo = t.makeMove(moves.get(i), (turn%2==1)?color:invert(color));
            tHijo.identificarFichas();
            int score[] = bestMove(tHijo, color, turn + 1, K);
            
            
            // MAX play
            if (turn % 2 == 1) {
                if (best < score[1]) {
                    best = score[1];
                    bestMoveIndex = i;
                }
            } //MIN play
            else {
                if (best > score[1]) {
                    best = score[1];
                    bestMoveIndex = i;
                }
            }
        }
        
        return new int[]{bestMoveIndex,best};
    }

    private String invert(String color) {
        if (color.equals(Reversi.WHITE)) {
            return Reversi.BLACK;
        } else {
            return Reversi.WHITE;
        }
    }

    private int Score(Tablero tHijo, String color) {
        if (color.equals(Reversi.WHITE)) {
            return tHijo.posicionBlancas.size() / 2;
        } else {
            return tHijo.posicionNegras.size() / 2;
        }
    }

}
