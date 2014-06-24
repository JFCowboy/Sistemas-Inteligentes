package cubo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author JuanFelipe
 */
public class Busqueda {
    
    ArrayList<Cubo> sol;
    Cubo objetivo;
    int cnt;
    static Heuristica heuristica;
    String colorDef = "012345";
    /**
     *
     */
    public static Comparator<Cubo> compCubo = new Comparator<Cubo>(){

        @Override
        public int compare(Cubo o1, Cubo o2) {
            int cost1 = heuristica.evaluar(o1)+o1.dist;
            int cost2 = heuristica.evaluar(o2)+o2.dist;
            return (int) (cost1-cost2);
        }
        
    };
    
    
    public Busqueda( String color ) {
        sol = new ArrayList<>();
        cnt = 0;
        heuristica = new HeuristicaEsquinas();
        colorDef = color;
        init( colorDef );
    }
    
    public Busqueda() {
        this( "012345" );
    }
    
    public Busqueda( Heuristica heuristica1 ) {
        this();
        heuristica = heuristica1;
    }
    
    public boolean AStar( Cubo inicial ){
        PriorityQueue<Cubo> cola = new PriorityQueue<>(26, compCubo);
        Map<Cubo, Integer>mapa = new TreeMap<>(compCubo);
        ArrayList<Cubo> vecinos;
        Cubo tope;
        cola.add(inicial);
        //Scanner in = new Scanner( System.in );
        
        while( cola.size()!=0 ){
            
            tope = cola.poll();
            if( tope.equals(objetivo) ){
                cnt = tope.dist;
                return true;
            }
            if( mapa.containsKey(tope) )
                continue;
            mapa.put(tope, 1);
            //System.out.println("->OBJ"+objetivo+" "+heuristica.evaluar(objetivo));
            System.out.println("->->->"+tope+" "+tope.dist);
            //in.next();
            vecinos = tope.vecinos();
            for ( Cubo vecino : vecinos) {
                //System.out.println("Vecino: "+vecino+" Cost: "+heuristica.evaluar(vecino));
                cola.add( vecino );
            }
            
        }
        
        return false;
    }
    
    public ArrayList<Cubo> getSol() {
        return sol;
    }

    public void setSol(ArrayList<Cubo> sol) {
        this.sol = sol;
    }

    public Cubo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Cubo objetivo) {
        this.objetivo = objetivo;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    private void init( String color ) {
        ArrayList<int[][]> finall = new ArrayList<>(6);
        int idx[] = {2,0,4,5,3,1};
        for(int i = 0; i < 6; i++){
            int[][] cara = new int[3][3];
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    cara[j][k] = idx[i];                    
                }
            }
            finall.add(cara);
        }       
        //"RBOGYW";
        objetivo = new Cubo( finall , color, 0);
    }
    
    
    
}
