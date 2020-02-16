import java.util.ArrayList;
import java.util.Random;

public class RandomAgent extends Agent{
    public static Board random(Board board, String computer){
        String human;
        if (computer == "black") {
            human = "white";
        }else{
            human = "black";
        }
        long startTime = System.currentTimeMillis();
        ArrayList<String> actions =  actions(board, computer);
        Random a=new Random();
        int rand = a.nextInt(actions.size());
        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("best move:" + actions.get(rand));
        System.out.println("Elapsed time: " + elapsedTime*1.0/1000 + "secs");


        return Board.checkKing(result(board,actions.get(rand)));
    }
}
