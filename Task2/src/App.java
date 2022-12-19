import java.util.Scanner;

import games.Game;
import games.SelfGame;
import games.UserGame;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

    System.out.println("For self game press->1");
    System.out.println("For user play press->2");
    int choose = s.nextInt();

    Game game;
    
    switch(choose){
        case 1:
            game = new SelfGame();
            break;
        case 2:
            game = new UserGame();
            break;
        default:
            game = new SelfGame();
    }
       game.start();
     
    }

    
}
