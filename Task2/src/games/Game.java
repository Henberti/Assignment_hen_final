package Games;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Game extends Thread{
    private Ceil gameBoard[][] = new Ceil[3][3];
    public static enum TYPE{X,O};
    int freeSize;
    AtomicInteger turn = new AtomicInteger(0);
    public AtomicBoolean gameTurn = new AtomicBoolean(false);
    

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
    public synchronized String getTurn(){
        return turn.get() == 0? TYPE.X.toString(): TYPE.O.toString();
    }

    

    public void switchTurn(){
        synchronized(getTurn()){
        if(turn.get()==0)
            turn.set(1);
        else
            turn.set(0);
            System.out.println("switch to "+turn.get());
        }
    }

    public synchronized boolean isGameOver(){
        return freeSize == 0;
    }

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

    public synchronized void setCeil(Integer[] ceilCordinate, String type){
        gameBoard[ceilCordinate[0]][ceilCordinate[1]].setSign(type);
        freeSize--;
    }
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
    
    public boolean existIn(int row, int col, String type){
        return gameBoard[row][col].getSign().equals(type);
    }
    




}
