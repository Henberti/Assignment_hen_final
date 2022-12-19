package games;
import players.*;

public class SelfGame extends Game{//HEN BERTI 201381407 && ELIRAN BALAISH 207598467
  
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
