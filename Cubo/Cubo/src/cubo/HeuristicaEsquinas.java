package cubo;

/**
 *
 * @author JuanFelipe
 */
public class HeuristicaEsquinas implements Heuristica{

    @Override
    public int evaluar(Cubo actual) {
        int cost = 0, esqUbicadas = 0;
        int NESQ = 8; // Numero de esquinas
        int NCORD = 3; // Numero de esquinas
        //Los valores que tenemos en cada esquina
        int[][] esquinas = { {2,0,1}, {2,0,3}, {2,5,1}, {2,5,3}, {0,4,3}
                            ,{0,4,1}, {4,5,3}, {4,5,1} };
                            /*{ {0,1,5}, {0,1,4}, {0,3,5}, {0,3,4}, {1,2,4}
                            ,{1,2,5}, {2,3,4}, {2,3,5} };*/
        //Las posiciones donde se debe encontrar cada valor de cada esquina
        int[][][] posEsquinas = { { {0,0,0},{1,2,0}, {5,0,2} }
                                   ,{ {0,0,2},{1,2,2}, {4,0,0} }
                                   ,{ {0,2,0},{3,0,0}, {5,2,2} }
                                   ,{ {0,2,2},{3,0,2}, {4,2,0} }
                                   ,{ {1,0,2},{2,0,0}, {4,0,2} }
                                   ,{ {1,0,0},{2,0,2}, {5,0,0} }
                                   ,{ {2,2,0},{3,2,2}, {4,2,2} }
                                   ,{ {2,2,2},{3,2,0}, {5,2,0} }
                                  };
        
        for( int i = 0; i < NESQ ; i++){
            for (int j = 0; j < NCORD; j++) {
                int idx = i;
                if( actual.cubo
                        .get( posEsquinas[idx][j][0] )
                            [ posEsquinas[idx][j][1] ] 
                            [ posEsquinas[idx][j][2] ]== esquinas[i][j] ){
                       esqUbicadas++;
                } else {
//                    System.out.println("i:"+i+" j:"+j+" val:"+actual.cubo
//                        .get( posEsquinas[idx][j][0] )
//                            [ posEsquinas[idx][j][1] ] 
//                            [ posEsquinas[idx][j][2] ] +" OBJ: "+esquinas[i][j]);
                    cost += 4;
                }
            }
        }
        
        cost += cost*esqUbicadas;
//        if( cost==0 )
//            cost = evaluarCentro( actual );
            
        return cost;
    }

    private int evaluarCentro(Cubo actual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
