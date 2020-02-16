public class MiniMaxAgent extends Agent{
    private static int stateVisited;

    public static String minMaxDecision(Board board,String computer){
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
        int maxUtility = - 1;
        for (String action:actions(board, computer)) {
            int temp = minValue(Agent.result(board,action),computer);
            if (minValue(Agent.result(board,action),computer) > maxUtility){
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
    public static int maxValue(Board board, String computer){
        stateVisited++;
        board = Board.checkKing(board);
        int tempValue = -1;
        if (terminalTest(board)){
            return utilityTest(board.board);
        }
        else for (String action:actions(board, computer)) {
            tempValue = Math.max(tempValue,minValue(Agent.result(board,action),computer));
        }
        return tempValue;
    }

    public static int minValue(Board board,String computer){
        String human;
        if (computer == "black") {
            human = "white" ;
        }else{
            human = "black";
        }
        stateVisited++;
        board = Board.checkKing(board);
        int tempValue = Integer.MAX_VALUE;
        if (terminalTest(board)){
            return utilityTest(board.board);
        }
        else
            for (String action:actions(board, human)) {
                tempValue = Math.min(tempValue,maxValue(Agent.result(board,action),computer));
            }
        return tempValue;
    }
}
