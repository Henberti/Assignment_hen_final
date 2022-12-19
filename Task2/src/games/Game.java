package games;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Game extends Thread{//HEN BERTI 201381407 && ELIRAN BALAISH 207598467
    Thread player1, player2;
    private Ceil gameBoard[][] = new Ceil[3][3];
    public static enum TYPE{X,O};
    int freeSize;
    AtomicInteger turn = new AtomicInteger(0);
    public AtomicBoolean gameTurn = new AtomicBoolean(false);
    
    //the ctor initialize the game bord 3*3 of ceils 
    //and initialize the free ceil size with 9
    public Game(){
        for(int i=0; i<gameBoard.length; i++)
            for(int j=0; j<gameBoard[i].length; j++)
                gameBoard[i][j] = new Ceil(i,j);

        this.freeSize = 9;

    }

    public void printBoard(){
        StringBuffer sb = new StringBuffer();
        sb.append(" ___  ___  ___\n");
        for (Ceil[] ceils : gameBoard) {
            for (Ceil ceil : ceils) {
                sb.append("|_"+ceil+"_|");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    //the method return the sign that now playes
    //the turn variable is atomic integer and all his oparations are atimic
    //but the method is syncronized becuse 2 or more thrades can currently use it 
    public synchronized String getTurn(){
        return turn.get() == 0? TYPE.X.toString(): TYPE.O.toString();
    }

    
    //switch turn use the getTurn lock to prevent from thrades to check if his turn will arraive
    public void switchTurn(){
        synchronized(getTurn()){
        if(turn.get()==0)
            turn.set(1);
        else
            turn.set(0);
            System.out.println("switch to "+turn.get());
        }
    }
    //check if there is free ceils left
    //return true if not
    public synchronized boolean isGameOver(){
        return freeSize == 0;
    }
    //return all the ceils that currently free using the ceil method isFree
    public ArrayList<Integer[]> getFreeCeils(){
        ArrayList<Integer[]> temp = new ArrayList<>();

        for(int i=0; i<gameBoard.length; i++)
            for(int j=0; j<gameBoard[i].length; j++)
                if(gameBoard[i][j].isFree()){
                    Integer cordination[] = {i,j};
                    temp.add(cordination);
                }

        return temp;
    }
    //the method change the ceil stat from free to aquired by changing _ to or x or 0  
    public synchronized void setCeil(Integer[] ceilCordinate, String type){
        gameBoard[ceilCordinate[0]][ceilCordinate[1]].setSign(type);
        freeSize--;
    }
    //the game check if any of all possiable win patterns is true and if it is the game will
    //stop and winner will be declare 
    public boolean checkWin(String type){
        
        if(existIn(0, 0, type)){
            if(existIn(1, 1, type)&&existIn(2, 2, type))
                    return true;
            if(existIn(1, 0, type)&&existIn(2, 0, type))
                return true;
            if(existIn(0, 1, type)&&existIn(0, 2, type))
                return true;
        }
        if(existIn(0, 1, type))
            if(existIn(1, 1, type)&&existIn(2, 1, type))
                return true;

        if(existIn(0, 2, type)){
            if(existIn(1, 2, type)&&existIn(2, 2, type))
                return true;
            if(existIn(1, 1, type)&&existIn(2, 0, type))
                return true;
        }
        if(existIn(1, 0, type))
            if(existIn(1, 1, type)&&existIn(1, 2, type))
                return true;
        if(existIn(2, 0, type))
            if(existIn(2, 1, type)&&existIn(2, 2, type))
                return true;
    
        return false;
    }
    //helper method of checkwin
    public boolean existIn(int row, int col, String type){
        return gameBoard[row][col].getSign().equals(type);
    }
    @Override
    public void run() {
        String winner=" No one";

        printBoard();
        player1.start();
        player2.start();

        while(!isGameOver()){//check if can still play another iteration
            synchronized(this){//wait antil one of the player finish its turn and notify 
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //printing the board and check if someone 
        synchronized(this){
            printBoard();
            if(freeSize<=4){
               if(checkWin(getTurn())){
                    winner = getTurn();
                    break;
               }
            }
            //if no one win
            //the game will switch the turn to the next player
            switchTurn();
            gameTurn.set(false);
        }
        }
        //if the game notice that the game is over 
        //he interrupt the two threads of players to tell them to stop
        player1.interrupt();
        player2.interrupt();
        if(freeSize==0){
            //if the game stopes beacuse that the board is full the game will print it
            System.out.println("Boaed is full");
        }
        //print the winner if exist else print no one
        System.out.println("the winner is "+winner);
        
    }
    




}
