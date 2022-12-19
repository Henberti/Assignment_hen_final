package games;
import players.SelfPlayer;
import players.UserPlayer;

public class UserGame extends Game{//HEN BERTI 201381407 && ELIRAN BALAISH 207598467

    public UserGame(){
        super();
    }

    @Override
    //the method spin two threads one is a self player thread and one is user thread
    public synchronized void start() {
        int coin = (int) (Math.random()+1);
        String cpuType = coin==0?TYPE.X.toString():TYPE.O.toString();
        String userType = coin==0?TYPE.O.toString():TYPE.X.toString();

        player1 = new Thread(new SelfPlayer(this, cpuType));
        player2 = new Thread(new UserPlayer(this, userType));
        player1.setName("cpu");
        player2.setName("user");
        super.start();
    }
    
}
