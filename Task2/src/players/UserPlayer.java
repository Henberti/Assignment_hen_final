package players;
import java.util.ArrayList;
import java.util.Scanner;

import games.Game;

public class UserPlayer extends Player{//HEN BERTI 201381407 && ELIRAN BALAISH 207598467
    Scanner s = new Scanner(System.in);

    public UserPlayer(Game game, String sign) {
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
            //get the free ceils and prints them, then the player can choose where to put the sign
            ArrayList<Integer[]> freeceils = game.getFreeCeils();
            StringBuffer sb = new StringBuffer();
            freeceils.forEach(ceil->{
                sb.append(" [");
                for (Integer integers : ceil) {
                    sb.append(integers);
                  
                }
                sb.append("] ");
                });
                System.out.println("user 3");
            System.out.println(sb.toString());
            for(int i=0; i<freeceils.size(); i++)
                System.out.print("   "+i+"  ");
            System.out.println("\nchoose ceil");


            int choose = s.nextInt();
        
            game.setCeil(freeceils.get(choose), sign);

            synchronized(game){
                game.gameTurn.set(true);
                 //return turn to game and notify
                game.notify();
            }

        }
    }

    
}
