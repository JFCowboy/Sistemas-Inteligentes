package unalcol.agents.examples.labyrinth.teseo;
import com.cowboy.TeseoCowboyAgent;
import com.cowboy.TeseoPandaAgent;
import com.cowboy.TeseoPhanterAgent;
import unalcol.agents.Agent;

import unalcol.agents.examples.labyrinth.*;
import unalcol.agents.simulate.util.*;

public class TeseoMain {
  private static SimpleLanguage getLanguage(){
    return  new SimpleLanguage( new String[]{"front", "right", "back", "left"
                                    , "exit", "afront", "arigth", "aback", "aleft"},
                                   new String[]{"no_op", "die", "advance", "rotate"}
                                   );
  }

  public static void main( String[] argv ) {
    LabyrinthDrawer.DRAW_AREA_SIZE = 600;
    LabyrinthDrawer.CELL_SIZE = 40;
    Labyrinth.DEFAULT_SIZE = 15;
    Agent agent = new Agent( new TeseoPhanterAgent( getLanguage() ) );
//    Agent agent = new Agent( new TeseoPandaAgent( getLanguage() ) );
//    Agent agent = new Agent( new TeseoCowboyAgent( getLanguage() ) );
//    Agent agent = new Agent( new InteractiveAgentProgram( getLanguage() ) );
//    Agent agent = new Agent( new RandomReflexTeseoAgentProgram( getLanguage() ) );
    TeseoMainFrame frame = new TeseoMainFrame( agent, getLanguage() );
    frame.setVisible( true );
  }
}
