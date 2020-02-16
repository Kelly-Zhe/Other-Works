import java.util.ArrayList;

public class HMiniMaxAgent extends Agent{
    private static int stateVisited;


    public static String hMinMaxDecision(Board board, int depth, String computer){
        String human;
        if (computer == "black") {
            human = "white" ;
        }else{
            human = "black";
        }
        stateVisited = 0;
        long startTime = System.currentTimeMillis();
        String bestAction = "";
        board = Board.checkKing(board);
        int maxUtility = Integer.MIN_VALUE;
        for (String action:actions(board, computer)) {
            int temp = hMinValue(Agent.result(board,action),0,depth,computer);

            if ((temp) > maxUtility){
                maxUtility = temp;
                bestAction = action;
            }
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("visited " + stateVisited + " states");
        System.out.println("best move:" + bestAction);
        System.out.println("Elapsed time: " + elapsedTime*1.0/1000 + "secs");

        return bestAction;
    }
    public static int hMaxValue(Board board, int currentDepth, int maxDepth,String computer){
        stateVisited++;
        currentDepth++;
        if (currentDepth >= maxDepth){
            return eval(board);
        }
        board = Board.checkKing(board);
        int tempValue = Integer.MIN_VALUE;
        for (String action:actions(board, computer)) {
            tempValue = Math.max(tempValue,hMinValue(Agent.result(board,action),currentDepth,maxDepth,computer));
        }
        return tempValue;
    }

    public static int hMinValue(Board board, int currentDepth, int maxDepth,String computer){
        String human;
        if (computer == "black"){
            human  = "white";
        } else{
            human = "black";
        }
        stateVisited++;
        currentDepth++;

        if (currentDepth >= maxDepth){
            return eval(board);
        }
        board = Board.checkKing(board);
        int tempValue = Integer.MAX_VALUE;

        for (String action:actions(board, human)) {
            tempValue = Math.min(tempValue,hMaxValue(Agent.result(board,action),currentDepth,maxDepth,computer));
        }
        return tempValue;
    }
}
