package players;
import java.util.ArrayList;
import java.util.Scanner;

import games.Game;

public class UserPlayer extends Player{
    Scanner s = new Scanner(System.in);

    public UserPlayer(Game game, String sign) {
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
                
                game.notify();
            }

        }
    }

    
}
