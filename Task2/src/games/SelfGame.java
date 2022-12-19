package Games;
import .Players

public class SelfGame extends Game{
    Thread player1, player2;
   
    

    public SelfGame() {
        super();
    }

    @Override
    public synchronized void start() {
        player1 = new Thread(new SelfPlayer(this, TYPE.X.toString()));
        player2 = new Thread(new SelfPlayer(this, TYPE.O.toString()));
        super.start();
    }

    @Override
    public void run() {
        String winner=" No one";

        printBoard();
        player1.start();
        player2.start();

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

        player1.interrupt();
        player2.interrupt();
        if(freeSize==0){
            System.out.println("Boaed is full");
        }
        System.out.println("the winner is "+winner);
    }


    



    
    
}
