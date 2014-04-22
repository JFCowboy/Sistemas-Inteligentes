package cubo;

import java.util.ArrayList;

/**
 *
 * @author Estudiantes
 */
public class Cubo {
    
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
    
}
