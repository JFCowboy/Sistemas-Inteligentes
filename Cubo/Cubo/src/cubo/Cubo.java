package cubo;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Estudiantes
 */
public class Cubo {
    
    static final int NCaras = 6;
    static final int DIM = 3;
    ArrayList<int[][]>cubo;
    String colors;
    int dist;
    
    public Cubo() {
    }

    public Cubo(ArrayList<int[][]> cubo, String colors, int dist) {
        this.cubo = cubo;
        this.colors = colors;
        this.dist = dist;
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
        int idx = 0, idxAux, DIM = 3;
        
        aux = new StringBuilder("");
        for(int i = 0; i < DIM; i++) {
            aux.append("  ");
        }
        
        //Agregar Parte de Arriba 
        idxAux = 1;
        for(int i = 0; i < DIM; i++) {
            out.append(aux.toString());
            for(int j = 0; j < DIM; j++) {
                out.append(" ");
                out.append( colors.charAt( cubo.get( idxAux )[ i ][ j ] ) );
            }
            out.append("\n");
        }
        
        //Agregar parte del centro
        for(int i = 0; i < DIM; i++){
            for(int k = 0; k < DIM+1; k++) {
                idxAux = ord[ idx ];
                idx = (idx + 1) % 4;
                for(int j = 0; j < DIM; j++) {
                    out.append(" ");
                    out.append( colors.charAt( cubo.get( idxAux )[i][j] ) );
                }
                out.append("|");
            }
            out.append("\n");
        }
        
        //Agregar Parte de Abajo
        idxAux = 3;
        for(int i = 0; i < DIM; i++) {
            out.append( aux.toString() );
            for(int j = 0; j < DIM; j++) {
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
    
    public ArrayList<Cubo> vecinos(){
        ArrayList<Cubo> cubos = new ArrayList<>();
        ArrayList<int[][]> cuboAux;
        int nGiros = 12;
        int moves[] = {0,1,2,3,4,5,6,7,8,9,10,11};
        Giros mano = new Giros();
        
        for(int i=0; i<nGiros; i++){
            int giro = moves[i];
            cuboAux = mano.girar(this.getCubo(), giro);
            cubos.add( new Cubo( cuboAux, this.colors, this.dist+1 ) );
        }
        
        return cubos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cubo other = (Cubo) obj;
        if ( !CompararCubos(this.cubo, other.cubo) ) {
            return false;
        }
        return true;
    }

    private boolean CompararCubos(ArrayList<int[][]> cubo, ArrayList<int[][]> cubo0) {
        
        for(int i = 0; i<NCaras; i++){
            for (int j = 0; j < DIM; j++) {
                for (int k = 0; k < DIM; k++) {
                    if( cubo.get(i)[j][k]!=cubo0.get(i)[j][k] )
                        return false;
                }
            }
        }
        return true;
    }
    
    
}
