package implementaciones;

import cubo.Cubo;
import java.util.ArrayList;

/**
 *
 * @author JuanFelipe
 */
public class NodeCubo extends Node{
    
    Cubo cubo;
    
    public NodeCubo(int costo_aqui, int costo, Cubo cubo) {
        super(costo_aqui, costo);
        this.cubo = cubo;
        this.estado = new CubeState(cubo);
    }

    @Override
    public Node createGoalState() {
        
        ArrayList<int[][]> finall = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            int[][] cara = new int[3][3];
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    cara[j][k] = i;                    
                }
            }
            finall.add(cara);
        }
                       
        return new NodeCubo(0, 0, new Cubo(finall,cubo.getColors()));
    }

    @Override
    public Cubo getValue() {
        return cubo;
    }
    
}
