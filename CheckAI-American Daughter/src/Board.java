import java.util.ArrayList;


public class Board {

    enum types  {empty, white, black, whiteKing, blackKing};
    ArrayList<ArrayList<types>> board = new ArrayList<ArrayList<types>>();



    public Board(int size){
        //making empty board
        for (int i = 0; i < size; i++){
            ArrayList<types> temp = new ArrayList<>();
            for (int j = 0; j < size; j++){
                temp.add(types.empty);
            }
            board.add(temp);
        }

        //marking the spaces as occupied
        if (size==4) {
            for (int i = 1; i < size; i += 2) {
                board.get(0).set(i, types.black);
                board.get(size - 1).set(i - 1, types.white);
            }
        }else if(size==8){
            for (int i=1;i<size;i+=2){
                board.get(0).set(i,types.black);
                board.get(1).set(i-1,types.black);
                board.get(2).set(i,types.black);
                board.get(5).set(i-1,types.white);
                board.get(6).set(i,types.white);
                board.get(7).set(i-1,types.white);
            }
        }

    }
    public static Board checkKing(Board chessboard) {
        for (int i = 1; i < chessboard.board.get(0).size(); i += 2) {
            if (chessboard.board.get(0).get(i) == types.white) {
                chessboard.board.get(0).set(i, types.whiteKing);
            }
            if (chessboard.board.get(chessboard.board.size() - 1).get(i - 1) == types.black) {
                chessboard.board.get(chessboard.board.size() - 1).set(i - 1, types.blackKing);
            }
        }
        return chessboard;
    }


    public void printBoard(){
        int n = board.size();
        System.out.print("     ");
        for (int i = 0; i < n ; i++) {
            System.out.print(i + 1 + "   ");
        }
        System.out.println();
        for (int i = 0 ; i < n; i++) {
            System.out.print("   ");
            for (int j = 0; j < n ; j++){
                System.out.print("+---");
            }
            System.out.println("+");
            System.out.print(Agent.getCharForNumber(i+1) + "  ");
            for (int j = 0; j < n ; j++) {
                switch (board.get(i).get(j)){
                    case empty:
                        System.out.print("|   ");
                        break;
                    case black:
                        System.out.print("| b ");
                        break;
                    case white:
                        System.out.print("| w ");
                        break;
                    case blackKing:
                        System.out.print("| B ");
                        break;
                    case whiteKing:
                        System.out.print("| W ");
                        break;
                }
            }
            System.out.println("|");
        }
        System.out.print("   ");
        for (int j = 0; j < n ; j++){
            System.out.print("+---");
        }
        System.out.println("+");
    }



    public static boolean LegalMove(String move, Board chessboard) {
        int[] cor =Agent.stringToCor(move);
        if (cor[3] > chessboard.board.size() - 1 || cor[2] > chessboard.board.size() - 1 || cor[3] < 0 || cor[2] < 0){
            return false;
        }
        types type=chessboard.board.get(cor[1]).get(cor[0]);
        if(type==types.white) {
            if(cor[3]!=cor[1]-1 || (cor[2]!=cor[0]+1 && cor[2]!=cor[0]-1)) {
                return  false;
            }
        }
        if(type==types.black) {
            if(cor[3]!=cor[1]+1 || (cor[2]!=cor[0]+1 && cor[2]!=cor[0]-1)) {
                return  false;
            }
        }
        if(type==types.whiteKing||type==types.blackKing) {
            if((cor[3]!=cor[1]-1 && cor[3]!=cor[1]+1) || (cor[2]!=cor[0]+1 && cor[2]!=cor[0]-1)) {
                return false;
            }
        }
        if(chessboard.board.get(cor[3]).get(cor[2])!=types.empty) {
            return false;
        }
        return true;
    }


    public static boolean Legalcapture(String capture, Board chessboard){


        int cor[]=Agent.stringToCor(capture);
        if (cor[3] > chessboard.board.size() - 1 || cor[2] > chessboard.board.size() - 1 || cor[3] < 0 || cor[2] < 0){
            return false;
        }
        types type=chessboard.board.get(cor[1]).get(cor[0]);
        if (type==types.white){
            if(cor[3]!=cor[1]-2 || (cor[2]!=cor[0]-2 && cor[2]!=cor[0]+2)){
                return false;
            }else if(chessboard.board.get((cor[1]+cor[3])/2).get((cor[0]+cor[2])/2) != types.black && chessboard.board.get((cor[1]+cor[3])/2).get((cor[0]+cor[2])/2) != types.blackKing){
                return  false;
            }else if(chessboard.board.get(cor[3]).get(cor[2])!=types.empty){
                return false;
            }
        }
        if (type==types.black){

            if(cor[3]!=cor[1]+2 || (cor[2]!=cor[0]-2 && cor[2]!=cor[0]+2)){
                return false;
            }else if(chessboard.board.get((cor[1]+cor[3])/2).get((cor[0]+cor[2])/2) != types.white && chessboard.board.get((cor[1]+cor[3])/2).get((cor[0]+cor[2])/2) != types.whiteKing){
                return false;
            }else if(chessboard.board.get(cor[3]).get(cor[2])!=types.empty){
                return false;
            }
        }
        if (type==types.whiteKing) {
            if ((cor[3] != cor[1] - 2 && cor[3] != cor[1]+2 ) || (cor[2] != cor[0] - 2 && cor[2] != cor[0] + 2)) {
                return false;
            } else if (chessboard.board.get((cor[1]+cor[3])/2).get((cor[0]+cor[2])/2) != types.black && chessboard.board.get((cor[1]+cor[3])/2).get((cor[0]+cor[2])/2) != types.blackKing) {
                return false;
            } else if (chessboard.board.get(cor[3]).get(cor[2]) != types.empty) {
                return false;
            }
        }

        if (type == types.blackKing) {
            if ((cor[3] != cor[1] - 2 && cor[3] != cor[1]+2 ) || (cor[2] != cor[0] - 2 && cor[2] != cor[0] + 2)){
                return false;
            } else if (chessboard.board.get((cor[1]+cor[3])/2).get((cor[0]+cor[2])/2) != types.white && chessboard.board.get((cor[1]+cor[3])/2).get((cor[0]+cor[2])/2) != types.whiteKing) {
                return false;
            } else if (chessboard.board.get(cor[3]).get(cor[2]) != types.empty) {
                return false;
            }
        }
        return true;
    }


}