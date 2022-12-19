package players;

import java.util.ArrayList;

import games.Game;

public class SelfPlayer extends Player {//HEN BERTI 201381407 && ELIRAN BALAISH 207598467

    public SelfPlayer(Game game, String sign) {
        super(game, sign);
    }
    
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            //while game is running without recieving interuption 

            while(!game.getTurn().equals(sign)|| game.gameTurn.get()==true){
                //checks if it is its turn
                //if not - sleeps for 500millis
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }

            }
            ArrayList<Integer[]> freeCeil = game.getFreeCeils();
            int choose = (int)(Math.random()*freeCeil.size());
            //choose a random cell to asign the relevent sign from the freeCeils

            synchronized(game){
                if(!Thread.currentThread().isInterrupted()){
                    //if not interrupted
                    if(!freeCeil.isEmpty()){
                        game.setCeil(freeCeil.get(choose), sign);
                        //updates ceil
                    }
                    else{
                        System.out.println("Board is full");
                        game.interrupt();
                        //if the board is full, update game
                        
                        
                    }
                }
                game.gameTurn.set(true);
                //return turn to game and notify
                game.notify();
            }
        }
        
        
    }
   
        
    
    
}
