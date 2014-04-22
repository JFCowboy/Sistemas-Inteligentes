package implementaciones;

import cubo.Cubo;
import java.util.ArrayList;

/**
 *
 * @author JuanFelipe
 */
class CubeState implements State {

    ArrayList<Integer> states;
    static final int TAM = 3;

    public CubeState(Cubo cubo) {
        states = new ArrayList<Integer>();
        init(cubo);
    }

    @Override
    public boolean equals(State state) {
        CubeState cubeState = (CubeState) state;
        return states.equals(cubeState.states);

    }

    private void init(Cubo cubo) {
        ArrayList<int[][]> pepe = cubo.getCubo();
        int sz = pepe.size();
        int aux = 0;

        for (int k = 0; k < sz; k++) {
            aux = 0;
            for (int j = 0; j < TAM; j++) {
                for (int g = 0; g < TAM; g++) {
                    aux = aux << 3;
                    aux += pepe.get(k)[j][g];
                }
            }
            states.add(aux);
        }
    }

    @Override
    public String toString() {
        return "CubeState{" + "states=" + statesToString() + '}';
    }

    private String statesToString() {
        StringBuilder sb = new StringBuilder("[");
        
        for(int i=0; i<states.size(); i++){
            sb.append(" ");
            sb.append(states.get(i));
        }
        sb.append(" ]");
        return sb.toString();
    }
    
}
