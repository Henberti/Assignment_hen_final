package games;
import players.SelfPlayer;
import players.UserPlayer;

public class UserGame extends Game{
    Thread cpu, user;

    public UserGame(){
        super();
    }

    @Override
    public synchronized void start() {
        int coin = (int) (Math.random()+1);
        String cpuType = coin==0?TYPE.X.toString():TYPE.O.toString();
        String userType = coin==0?TYPE.O.toString():TYPE.X.toString();

        cpu = new Thread(new SelfPlayer(this, cpuType));
        user = new Thread(new UserPlayer(this, userType));
        cpu.setName("cpu");
        user.setName("user");
        super.start();
    }

    @Override
    public void run() {
        String winner=" No one";

        printBoard();
        cpu.start();
        user.start();

        while(!isGameOver()){
            synchronized(this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized(this){
            printBoard();
            if(freeSize<=4){
               if(checkWin(getTurn())){
                    winner = getTurn();
                    break;
               }
            }
            switchTurn();
            gameTurn.set(false);
        }
        }

        cpu.interrupt();
        user.interrupt();
        if(freeSize==0){
            System.out.println("Boaed is full");
        }
        System.out.println("the winner is "+winner);
    

        
        
    }
    
}
