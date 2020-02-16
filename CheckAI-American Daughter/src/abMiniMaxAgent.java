import java.util.ArrayList;

public class abMiniMaxAgent extends Agent {
    private static int stateVisited;

    public static String abMiniDecision(Board board,String computer){

        String human;
        if (computer == "black"){
            human  = "white";
        } else{
            human = "black";
        }
        stateVisited = 0;
        long startTime = System.currentTimeMillis();

        String move=" ";
        board= Board.checkKing(board);
        int max=Integer.MIN_VALUE;
        for (String action:actions(board, computer)) {
            int v = abminvalue(result(board,action),Integer.MIN_VALUE,Integer.MAX_VALUE,human);
            if (v>max){
                max=v;
                move = action;
            }
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("visited " + stateVisited + " states");
        System.out.println("best move:" + move);
        System.out.println("Elapsed time: " + elapsedTime*1.0/1000 + "secs");

        return move;
    }

    public static int abmaxvalue(Board board, int alpha, int beta,String computer){
        stateVisited++;
        board = Board.checkKing(board);
        int v=Integer.MIN_VALUE;
        if(terminalTest(board)){
            return utilityTest(board.board);
        }
        for(String action:actions(board, computer)){
            v=Math.max(v,abminvalue(result(board,action),alpha,beta,computer));
            if(v >= beta){return v;}
            else alpha=Math.max(alpha,v);
        }
        return v;
    }

    public static int abminvalue(Board board, int alpha, int beta, String computer){
        String human;
        if (computer == "black"){
             human  = "white";
        } else{
             human = "black";
        }
        stateVisited++;
        board= Board.checkKing(board);
        int v=Integer.MAX_VALUE;
        if(terminalTest(board)){
            return utilityTest(board.board);
        }
        for(String action:actions(board, human)){
            v=Math.min(v,abmaxvalue(result(board,action),alpha,beta,human));
        }
        return v;
    }

}
