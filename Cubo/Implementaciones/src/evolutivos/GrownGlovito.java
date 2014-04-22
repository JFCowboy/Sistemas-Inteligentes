package evolutivos;

import unalcol.evolution.GrowingFunction;
import unalcol.types.collection.bitarray.BitArray;

/**
 *
 * @author JuanFelipe
 */
public class GrownGlovito extends GrowingFunction<BitArray, Glovito> {
    
    public Glovito get( BitArray genome ){
        int k, s = genome.size()/3;
        int[][] t = new int[s/2][2];
        int[][] out = new int[s/2][2];
        
        k = 0;
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                int h = genome.get(k)?2:0;
                k++;
                int l = genome.get(k)?1:0;
                k++;
                t[i][j] = h+l;
                boolean symbol = genome.get(k);
                out[i][j] = genome.get(k)?1:0;
                k++;
            }
        }
        
        return new Glovito(t, out);
    }
    
    public BitArray set( Glovito glovito ){
        int fil = glovito.getT().length;
        int col = glovito.getT()[0].length;
        BitArray res = new BitArray(fil*col*3, true);
        int idx = 0;
        boolean aux;
        
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                //boolean h
                aux = (glovito.getT()[i][j]&2)>0;
                res.set(idx, aux);
                idx++;
                //boolean l
                aux = (glovito.getT()[i][j]&1)>0;
                res.set(idx, aux);
                idx++;
                //boolean symbol 
                aux = glovito.getOut()[i][j]>0;
                res.set(idx, aux);
                idx++;
            }
        }
            
        return res;
    }
}
