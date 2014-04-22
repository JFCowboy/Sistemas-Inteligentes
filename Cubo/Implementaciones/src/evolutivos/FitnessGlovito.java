package evolutivos;

import unalcol.optimization.OptimizationFunction;

/**
 *
 * @author JuanFelipe
 */
public class FitnessGlovito extends OptimizationFunction<Glovito>{

    int []env;

    public FitnessGlovito(int []env) {
        this.env = env;
    }
    
    @Override
    public Double apply(Glovito s) {
        double f = 0.0;
        int[]out = s.simulate(env);
        
        for( int i=0; i<env.length-1; i++){
            if( out[i]==env[i+1] ){
                f++;
            }
        }
        
        return f;
    }
    
}
