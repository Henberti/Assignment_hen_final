package players;

import java.util.ArrayList;

import games.Game;

public class SelfPlayer extends Player {

    public SelfPlayer(Game game, String sign) {
        super(game, sign);
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){

            while(!game.getTurn().equals(sign)|| game.gameTurn.get()==true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }

            }
            ArrayList<Integer[]> freeCeil = game.getFreeCeils();
            int choose = (int)(Math.random()*freeCeil.size());

            synchronized(game){
                if(!Thread.currentThread().isInterrupted()){
                    if(!freeCeil.isEmpty()){
                        game.setCeil(freeCeil.get(choose), sign);
                    }
                    else{
                        System.out.println("Board is full");
                        game.interrupt();
                        
                        
                    }
                }
                game.gameTurn.set(true);
                game.notify();
            }
        }
        
        
    }
   
        
    
    
}
