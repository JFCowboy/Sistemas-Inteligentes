package unalcol.agents.examples.reversi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;

/**
 *
 * @author Juan
 */
public class ElPerlaReversiAgentProgram implements AgentProgram {

    protected String color;
    protected int tam;
    protected int fvez = 0;
    protected Tablero tablero;
    protected int K = 10;
    Map<String, Integer> valor = new HashMap<>();

    public ElPerlaReversiAgentProgram(String color) {
        this.color = color;
        valor.put("space", 0);
        valor.put("white", 1);
        valor.put("black", -1);
    }

    @Override
    public Action compute(Percept p) {
        if (fvez == 0) {
            fvez = 1;
            tam = Integer.parseInt(p.getAttribute(Reversi.SIZE).toString());
            tablero = new Tablero(tam);
        }

        if (p.getAttribute(Reversi.TURN).equals(color)) {
            leerTablero(p);                                                     // Lee el tablero actual
            tablero.identificarFichas();                                        // Identifica posiciones fichas negras y blancas
            ArrayList<Integer> posibleMov = tablero.movimientos(color);         // Movimientos válidos a partir de mis posiciones actuales        
            // No se puede ejecutar movimiento alguno, pasa turno
            if (posibleMov.size() == 0) {
                return new Action(Reversi.PASS);
            }
            System.out.println("Movimiento:" + color);
            System.out.println("->X:" + posibleMov.get(0) + " Y:" + posibleMov.get(1));
            System.out.println("--------------------------");
            return new Action(posibleMov.get(0) + ":" + posibleMov.get(1) + ":" + color);
        }

        System.out.println("Stealing turn " + color);
        return new Action(Reversi.PASS);
    }

    private void leerTablero(Percept p) {
        String pos, casilla;
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                pos = i + ":" + j;
                casilla = p.getAttribute(pos).toString();
                tablero.t[i][j] = valor.get(casilla);
            }
        }
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
