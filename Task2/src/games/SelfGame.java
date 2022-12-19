package games;
import players.*;

public class SelfGame extends Game{
  
   
    

    public SelfGame() {
        super();
    }

    @Override
    //the game will start 2 threads both are self players
    public synchronized void start() {
        player1 = new Thread(new SelfPlayer(this, TYPE.X.toString()));
        player2 = new Thread(new SelfPlayer(this, TYPE.O.toString()));
        super.start();
    }
    
}
