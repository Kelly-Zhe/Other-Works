import java.util.ArrayList;
import java.util.Random;

public class Agent {


    public static int[] stringToCor(String a)
    {

        char[] temp =a.toCharArray();
        int y=0;
        switch(temp[0]) {
            case 'A':
                y=0;
                break;
            case 'B':
                y=1;
                break;
            case 'C':
                y=2;
                break;
            case 'D':
                y=3;
                break;
            case 'E':
                y=4;
                break;
            case 'F':
                y=5;
                break;
            case 'G':
                y=6;
                break;
            case 'H':
                y=7;
                break;
        }
        int x=Character.getNumericValue(temp[1])-1;
        int x1=Character.getNumericValue(temp[4])-1;
        int y1=0;
        switch(temp[3]) {
            case 'A':
                y1=0;
                break;
            case 'B':
                y1=1;
                break;
            case 'C':
                y1=2;
                break;
            case 'D':
                y1=3;
                break;
            case 'E':
                y1=4;
                break;
            case 'F':
                y1=5;
                break;
            case 'G':
                y1=6;
                break;
            case 'H':
                y1=7;
                break;
        }
        int n=0;
        if(temp[2]=='-'){
            n=1;
        }else if(temp[2]=='x'){
            n=2;
        }
        int cor[]={x,y,x1,y1,n};
        return cor;
    }

    public static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }

    public static Board result(Board board, String action){
        if (action.length()==0){
            return board;
        }
        int[] cor = Agent.stringToCor(action);
        Board.types currType = board.board.get(cor[1]).get(cor[0]);
        board.board.get(cor[1]).set(cor[0], Board.types.empty);
        board.board.get(cor[3]).set(cor[2],currType);
        if (cor[4] == 2) {
            board.board.get((cor[1] + cor[3])/2).set((cor[0] + cor[2])/2, Board.types.empty);
        }
        return board;
    }

    public static ArrayList<String> actions(Board board, String computer){

        ArrayList<String> captures = new ArrayList<>();
        ArrayList<String> moves = new ArrayList<>();
        switch (computer){
            case "black":
                for (int i = 0; i < board.board.size() ; i++) {
                    for (int j = 0; j < board.board.size() ; j++) {
                        if (board.board.get(i).get(j) == Board.types.black){
                            String actionOne = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j);
                            String actionTwo = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j+2);
                            String actionThree = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j-1);
                            String actionFour = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j+3);

                            if (Board.LegalMove(actionOne,board)){
                                moves.add(actionOne);

                            }
                            if (Board.LegalMove(actionTwo,board)){
                                moves.add(actionTwo);

                            }
                            if (Board.Legalcapture(actionThree,board)){
                                captures.add(actionThree);

                            }
                            if (Board.Legalcapture(actionFour,board)){
                                captures.add(actionFour);

                            }
                        }
                    }
                }

                for (int i = 0; i < board.board.size() ; i++) {
                    for (int j = 0; j < board.board.size() ; j++) {
                        if (board.board.get(i).get(j) == Board.types.blackKing){
                            String actionOne = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j);
                            String actionTwo = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j+2);
                            String actionThree = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j-1);
                            String actionFour = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j+3);
                            String actionFive = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j);
                            String actionSix = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j+2);
                            String actionSeven = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i-1) + (j+3);
                            String actionEight = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i-1) + (j-1);
                            if (Board.LegalMove(actionOne,board)){
                                moves.add(actionOne);

                            }
                            if (Board.LegalMove(actionTwo,board)){
                                moves.add(actionTwo);

                            }
                            if (Board.Legalcapture(actionThree,board)){
                                captures.add(actionThree);

                            }
                            if (Board.Legalcapture(actionFour,board)){
                                captures.add(actionFour);

                            }
                            if (Board.LegalMove(actionFive,board)){
                                moves.add(actionFive);

                            }
                            if (Board.LegalMove(actionSix,board)){
                                moves.add(actionSix);

                            }
                            if (Board.Legalcapture(actionSeven,board)){
                                captures.add(actionSeven);

                            }
                            if (Board.Legalcapture(actionEight,board)){
                                captures.add(actionEight);

                            }
                        }
                    }
                }
                if (captures.size() != 0){
                    return captures;
                }else if (moves.size() != 0){
                    return moves;
                }else return captures;
            case "white":
                for (int i = 0; i < board.board.size() ; i++) {
                    for (int j = 0; j < board.board.size() ; j++) {
                        if (board.board.get(i).get(j) == Board.types.white){
                            String actionOne = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j);
                            String actionTwo = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j+2);
                            String actionThree = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+1) + (j-1);
                            String actionFour = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+1) + (j+3);
                            if (Board.LegalMove(actionOne,board)){
                                moves.add(actionOne);
                            }
                            if (Board.LegalMove(actionTwo,board)){
                                moves.add(actionTwo);
                            }
                            if (Board.Legalcapture(actionThree,board)){
                                captures.add(actionThree);
                            }
                            if (Board.Legalcapture(actionFour,board)){
                                captures.add(actionFour);
                            }
                        }
                    }
                }

                for (int i = 0; i < board.board.size() ; i++) {
                    for (int j = 0; j < board.board.size() ; j++) {
                        if (board.board.get(i).get(j) == Board.types.whiteKing){
                            String actionOne = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j);
                            String actionTwo = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i+2) + (j+2);
                            String actionThree = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j-1);
                            String actionFour = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i+3) + (j+3);
                            String actionFive = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j);
                            String actionSix = getCharForNumber(i+1) + (j+1) + "-" + getCharForNumber(i) + (j+2);
                            String actionSeven = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i-1) + (j+3);
                            String actionEight = getCharForNumber(i+1) + (j+1) + "x" + getCharForNumber(i-1) + (j-1);
                            if (Board.LegalMove(actionOne,board)){
                                moves.add(actionOne);
                            }
                            if (Board.LegalMove(actionTwo,board)){
                                moves.add(actionTwo);
                            }
                            if (Board.Legalcapture(actionThree,board)){
                                captures.add(actionThree);
                            }
                            if (Board.Legalcapture(actionFour,board)){
                                captures.add(actionFour);
                            }
                            if (Board.LegalMove(actionFive,board)){
                                moves.add(actionFive);
                            }
                            if (Board.LegalMove(actionSix,board)){
                                moves.add(actionSix);
                            }
                            if (Board.Legalcapture(actionSeven,board)){
                                captures.add(actionSeven);
                            }
                            if (Board.Legalcapture(actionEight,board)){
                                captures.add(actionEight);
                            }
                        }
                    }
                }
                if (captures.size() != 0){
                    return captures;
                }else if (moves.size() != 0){
                    return moves;
                }else return captures;
        }

        return null;
    }

    public static int utilityTest(ArrayList<ArrayList<Board.types>> board){
        int whiteNum = 0;
        int blackNum = 0;
        for (ArrayList<Board.types> row:board) {
            for (Board.types piece:row) {
                if (piece == Board.types.white || piece == Board.types.whiteKing){
                    whiteNum++;
                }
                if (piece == Board.types.black || piece == Board.types.blackKing){
                    blackNum++;
                }
            }
        }
        if (whiteNum > blackNum){
            return 0;
        }
        if (whiteNum == blackNum){
            return 1;
        }
        else {
            return 2;
        }
    }

    public static boolean terminalTest(Board board){
        int white = 0;
        int black = 0;
        for (ArrayList<Board.types> row:board.board) {
            for (Board.types piece:row) {
                if (piece == Board.types.white || piece == Board.types.whiteKing){
                    white++;
                }
                if (piece == Board.types.black || piece == Board.types.blackKing){
                    black++;
                }
            }
        }
        if (white == 0 || black == 0){
            return true;
        }
        else if (white == 1 && black == 1){
            return true;
        }
        else if (actions(board, "black").size() == 0 || actions(board, "white").size() == 0){
            return true;
        }
        return false;
    }


    public static int eval(Board board){
        int result = 0;
        for (ArrayList<Board.types> row:board.board) {
            for (Board.types piece:row) {
                if (piece == Board.types.white){
                    result-=1;
                }
                else if (piece == Board.types.whiteKing){
                    result-=2;
                }
                else if (piece == Board.types.black){
                    result+=1;
                }
                else if (piece == Board.types.blackKing){
                    result+=2;
                }
            }
        }
        return result;
    }

    public static String checkWinner(Board board){
        int black=0;
        int white=0;
        for (ArrayList<Board.types> row:board.board) {
            for (Board.types piece:row) {
                if (piece == Board.types.white){
                    white++;
                }
                else if (piece == Board.types.whiteKing){
                    white++;
                }
                else if (piece == Board.types.black){
                    black++;
                }
                else if (piece == Board.types.blackKing){
                    black++;
                }
            }
        }
        if (white>black) {
            return "white";
        }
        else {
            return "balck";
        }
    }
//    public static int eval(Board board){
//        int blackPoint = 0;
//        int whitePoint = 0;
//        for (ArrayList<Board.types> row:board.board) {
//            for (Board.types piece:row) {
//                if (piece == Board.types.white){
//                    whitePoint+=1;
//                }
//                else if (piece == Board.types.whiteKing){
//                    whitePoint+=2;
//                }
//                else if (piece == Board.types.black){
//                    blackPoint+=1;
//                }
//                else if (piece == Board.types.blackKing){
//                    blackPoint+=2;
//                }
//            }
//        }
//        if(blackPoint == 0) return Integer.MIN_VALUE;
//        else if(whitePoint == 0) return Integer.MAX_VALUE;
//        else return blackPoint - whitePoint;
//    }


}
