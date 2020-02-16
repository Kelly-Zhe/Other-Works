import java.util.Scanner;

public class GamePlaying {
    public static String color = "white";
    public static String computer = "black";
    public static String human = "white";
//    public static String first = "black";
//    public static String Next = "white";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your game:");
        System.out.println("Your move input would be of the form D1-C2, captures moves would be of the form D1xB3");
        System.out.println("1. Small 4x4 Checkers\n" + "2. Standard 8x8 Checkers");
        System.out.println("Your choice?");
        int boardOption = scanner.nextInt();
        System.out.println("Do you want to play black(b) or white(w)?");
        color  = scanner.next();
        if (color.equals("w")){
             computer = "black";
             human = "white";
        }else{
             computer = "white";
             human = "black";
        }
        while (true){
            if (boardOption!=1 && boardOption!=2){
                System.out.println("We only support size 4 or 8");
                boardOption = scanner.nextInt();
            }
            else break;
        }

        if(computer == "black"){
            if (boardOption == 1) {
                System.out.println("Choose your opponent:");

                System.out.println("1. An agent that plays randomly");
                System.out.println("2. An agent that uses MINIMAX");
                System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");
                System.out.println("4. An agent that uses H-MINIMAX with a fixed depth cutoff");
                System.out.print("Your choice?");
                int option = scanner.nextInt();
                switch(option){
                    case 1:
                        Board board1 = new Board(4);
                        while (true) {

                            board1 = board1.checkKing(board1);
                            board1.printBoard();
                            System.out.println(computer +" turn");
                            System.out.println("I am thinking...");
                            board1 = RandomAgent.random(board1,computer);
                            board1.printBoard();
                            if (Agent.terminalTest(board1)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board1));
                                break;
                            }
                            System.out.println(human + " turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board1)&&!Board.LegalMove(move,board1)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }

                            board1 = Agent.result(board1, move);
                            System.out.println(Agent.actions(board1, computer));
                            if (Agent.terminalTest(board1)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board1));
                                break;
                            }

                        }
                        break;
                    case 2:
                        Board board2 = new Board(4);
                        while (true) {
                            board2 = board2.checkKing(board2);
                            board2.printBoard();
                            System.out.println(computer + " turn");
                            System.out.println("I am thinking...");
                            Board copy = new Board(4);
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    copy.board.get(i).set(j, board2.board.get(i).get(j));
                                }
                            }
                            String aiMove = MiniMaxAgent.minMaxDecision(board2,computer);
                            try{
                                aiMove = Agent.actions(copy, computer).get(0);
                            }catch(Exception e){
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board2));
                                break;
                            }
                            System.out.println(human+" turn");
                            System.out.println("Your opponent made the move " + aiMove);
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            copy.printBoard();
                            board2 = copy;
                            if (Agent.terminalTest(board2)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board2));
                                break;
                            }
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board2)&&!Board.LegalMove(move,board2)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board2 = Agent.result(board2, move);
                            System.out.println(Agent.actions(board2, computer));

                        }
                        break;
                    case 3:
                        Board board3 = new Board(4);
                        while (true) {
                            board3 = board3.checkKing(board3);
                            board3.printBoard();
                            Board copy = new Board(4);
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    copy.board.get(i).set(j, board3.board.get(i).get(j));
                                }
                            }
                            System.out.println(computer+" turn");
                            String aiMove = abMiniMaxAgent.abMiniDecision(board3,computer);
                            try{
                                aiMove = Agent.actions(copy, computer).get(0);
                            }catch(Exception e){
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board3));
                                break;
                            }
                            System.out.println("Your opponent made the move " + aiMove);
                            System.out.println(human+" turn");
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            copy.printBoard();
                            board3 = copy;
                            if (Agent.terminalTest(board3)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board3));
                                break;
                            }
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board3)&&!Board.LegalMove(move,board3)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board3 = Agent.result(board3, move);
                            System.out.println(Agent.actions(board3, computer));

                        }
                        break;
                    case 4:
                        System.out.println("Depth limit:");
                        int depth = scanner.nextInt();
                        Board board4 = new Board(4);
                        while (true) {
                            board4 = board4.checkKing(board4);
                            board4.printBoard();
                            System.out.println(computer+" turn");
                            Board copy = new Board(4);
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    copy.board.get(i).set(j, board4.board.get(i).get(j));
                                }
                            }
                            String aiMove = HMiniMaxAgent.hMinMaxDecision(board4, depth, computer);

                            if (aiMove.equals("")){
                                try{
                                    aiMove = Agent.actions(copy, computer).get(0);
                                }catch(Exception e){
                                    System.out.println("Game finished");
                                    System.out.println("Winner: "+Agent.checkWinner(board4));
                                    break;
                                }
                            }
                            System.out.println("Your opponent made the move " + aiMove);
                            System.out.println(human+" turn");
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            copy.printBoard();
                            board4 = copy;
                            if (Agent.terminalTest(board4)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board4));
                                break;
                            }
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board4)&&!Board.LegalMove(move,board4)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board4 = Agent.result(board4, move);
                            System.out.println(Agent.actions(board4, computer));

                        }
                        break;
                    default:
                        break;

                }
            }
            else if (boardOption == 2){
                // System.out.println("You have the choice of H-MINIMAX(1) RANDOM(2) which opponent would you play?");
                System.out.println("Choose your opponent:");
                System.out.println("1. An agent that plays randomly");
                System.out.println("2. An agent that uses MINIMAX");
                System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");
                System.out.println("4. An agent that uses H-MINIMAX with a fixed depth cutoff");
                System.out.print("Your choice?");
                int option = scanner.nextInt();
                if (option == 2){
                    Board board2 = new Board(8);
                    while (true) {
                        board2 = board2.checkKing(board2);
                        board2.printBoard();
                        System.out.println(computer+" turn");
                        System.out.println("I am thinking...");
                        Board copy = new Board(8);
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                copy.board.get(i).set(j, board2.board.get(i).get(j));
                            }
                        }
                        String aiMove = MiniMaxAgent.minMaxDecision(board2, computer);
                        if (aiMove.equals("")){
                            aiMove = Agent.actions(copy, computer).get(0);

                        }
                        System.out.println(human+" turn");
                        System.out.println("Your opponent made the move " + aiMove);
                        copy = Agent.result(copy, aiMove);
                        copy = Board.checkKing(copy);
                        copy.printBoard();
                        board2 = copy;
                        if (Agent.terminalTest(board2)) {
                            System.out.println("Game finished");
                            System.out.println("Winner: "+Agent.checkWinner(board2));
                            break;
                        }
                        System.out.println("Your move (? for help):");
                        String move = scanner.next();
                        while(!Board.Legalcapture(move,board2)&&!Board.LegalMove(move,board2)){
                            System.out.println("Illegal move");
                            System.out.println("Try again");
                            move = scanner.next();
                        }
                        board2 = Agent.result(board2, move);
                        System.out.println(Agent.actions(board2, computer));

                    }
                }
                if (option == 3){
                    Board board3 = new Board(8);
                    while (true) {
                        board3 = board3.checkKing(board3);
                        board3.printBoard();
                        Board copy = new Board(8);
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                copy.board.get(i).set(j, board3.board.get(i).get(j));
                            }
                        }
                        System.out.println(computer+" turn");
                        String aiMove = abMiniMaxAgent.abMiniDecision(board3,computer);
                        try{
                            aiMove = Agent.actions(copy, computer).get(0);
                        }catch(Exception e){
                            System.out.println("Game finished");
                            System.out.println("Winner: "+Agent.checkWinner(board3));
                            break;
                        }
                        System.out.println("Your opponent made the move " + aiMove);
                        System.out.println(human+" turn");
                        copy = Agent.result(copy, aiMove);
                        copy = Board.checkKing(copy);
                        copy.printBoard();
                        board3 = copy;
                        if (Agent.terminalTest(board3)) {
                            System.out.println("Game finished");
                            System.out.println("Winner: "+Agent.checkWinner(board3));
                            break;
                        }
                        System.out.println("Your move (? for help):");
                        String move = scanner.next();
                        while(!Board.Legalcapture(move,board3)&&!Board.LegalMove(move,board3)){
                            System.out.println("Illegal move");
                            System.out.println("Try again");
                            move = scanner.next();
                        }
                        board3 = Agent.result(board3, move);
                        System.out.println(Agent.actions(board3, computer));

                    }

                }
                if (option == 4){
                    System.out.println("We would use H-MINIMAX to test the moves, the search depth in range of 1 to 10");
                    System.out.println("Depth limit:");
                    int depth = scanner.nextInt();
                    Board board = new Board(8);
                    while (true) {
                        board = board.checkKing(board);

                        Board copy = new Board(8);
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                copy.board.get(i).set(j, board.board.get(i).get(j));
                            }
                        }
                        String aiMove = HMiniMaxAgent.hMinMaxDecision(board, depth,computer);
                        if (aiMove.equals("")){
                            aiMove = Agent.actions(copy, computer).get(0);
                        }
                        System.out.println("Your opponent made the move " + aiMove);
                        copy = Agent.result(copy, aiMove);
                        copy = Board.checkKing(copy);
                        copy.printBoard();
                        board = copy;
                        if (Agent.terminalTest(board)) {
                            System.out.println("The game finished");
                            System.out.println("Winner: "+Agent.checkWinner(board));
                            break;
                        }
                        System.out.println("Please make a move");
                        String move = scanner.next();
                        while(!Board.Legalcapture(move,board)&&!Board.LegalMove(move,board)){
                            System.out.println("Illegal move");
                            System.out.println("Try again");
                            move = scanner.next();
                        }
                        board = Agent.result(board, move);

                        board.printBoard();
                        System.out.println(Agent.actions(board, computer));
                    }
                }
                else if (option == 1){
                    Board board = new Board(8);
                    while (true) {
                        board = board.checkKing(board);
                        board.printBoard();
                        board = RandomAgent.random(board,computer);
                        System.out.println("The agent has made a random move");
                        board.printBoard();
                        if (Agent.terminalTest(board)) {
                            System.out.println("The game finished");
                            System.out.println("Winner: "+Agent.checkWinner(board));
                            break;
                        }
                        System.out.println("Please make a move");
                        String move = scanner.next();
                        while(!Board.Legalcapture(move,board)&&!Board.LegalMove(move,board)){
                            System.out.println("Illegal move");
                            System.out.println("Try again");
                            move = scanner.next();
                        }
                        board = Agent.result(board, move);
                        System.out.println(Agent.actions(board, computer));
                    }
                }
            }
        }
        else{
            if (boardOption == 1) {
                System.out.println("Choose your opponent:");

                System.out.println("1. An agent that plays randomly");
                System.out.println("2. An agent that uses MINIMAX");
                System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");
                System.out.println("4. An agent that uses H-MINIMAX with a fixed depth cutoff");
                System.out.print("Your choice?");
                int option = scanner.nextInt();
                switch(option){
                    case 1:
                        Board board1 = new Board(4);
                        while (true) {

                            board1 = board1.checkKing(board1);
                            board1.printBoard();
                            System.out.println(human + " turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board1)&&!Board.LegalMove(move,board1)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }

                            board1 = Agent.result(board1, move);
                            System.out.println(Agent.actions(board1, computer));
                            if (Agent.terminalTest(board1)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board1));
                                break;
                            }

                            System.out.println(computer +" turn");
                            System.out.println("I am thinking...");
                            board1 = RandomAgent.random(board1,computer);
                           // board1.printBoard();
                            if (Agent.terminalTest(board1)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board1));
                                break;
                            }

                        }
                        break;
                    case 2:
                        Board board2 = new Board(4);
                        while (true) {
                            board2 = board2.checkKing(board2);
                            board2.printBoard();
                            System.out.println(human+" turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board2)&&!Board.LegalMove(move,board2)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board2 = Agent.result(board2, move);
                            System.out.println(Agent.actions(board2, computer));
                            System.out.println(computer + " turn");
                            System.out.println("I am thinking...");
                            Board copy = new Board(4);
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    copy.board.get(i).set(j, board2.board.get(i).get(j));
                                }
                            }
                            String aiMove = MiniMaxAgent.minMaxDecision(board2,computer);
                            try{
                                aiMove = Agent.actions(copy, computer).get(0);
                            }catch(Exception e){
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board2));
                                break;
                            }

                            System.out.println("Your opponent made the move " + aiMove);
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            //copy.printBoard();
                            board2 = copy;
                            if (Agent.terminalTest(board2)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board2));
                                break;
                            }
                        }
                        break;
                    case 3:
                        Board board3 = new Board(4);
                        while (true) {
                            board2 = board3.checkKing(board3);
                            board2.printBoard();

                            System.out.println(human+" turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board3)&&!Board.LegalMove(move,board3)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board3 = Agent.result(board3, move);
                            System.out.println(Agent.actions(board3, computer));

                            System.out.println(computer + " turn");
                            System.out.println("I am thinking...");
                            Board copy = new Board(4);
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    copy.board.get(i).set(j, board2.board.get(i).get(j));
                                }
                            }

                            String aiMove = abMiniMaxAgent.abMiniDecision(board3,computer);
                            try{
                                aiMove = Agent.actions(copy, computer).get(0);
                            }catch(Exception e){
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board3));
                                break;
                            }

                            System.out.println("Your opponent made the move " + aiMove);
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            //copy.printBoard();
                            board3 = copy;
                            if (Agent.terminalTest(board3)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board3));
                                break;
                            }

                        }
                        break;
                    case 4:
                        System.out.println("Depth limit:");
                        int depth = scanner.nextInt();
                        Board board4 = new Board(4);
                        while (true) {
                            board4 = board4.checkKing(board4);
                            board4.printBoard();

                            System.out.println(human+" turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board4)&&!Board.LegalMove(move,board4)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board4 = Agent.result(board4, move);
                            System.out.println(Agent.actions(board4, computer));

                            System.out.println(computer + " turn");
                            System.out.println("I am thinking...");
                            Board copy = new Board(4);
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    copy.board.get(i).set(j, board4.board.get(i).get(j));
                                }
                            }

                            String aiMove = HMiniMaxAgent.hMinMaxDecision(board4,depth,computer);
                            try{
                                aiMove = Agent.actions(copy, computer).get(0);
                            }catch(Exception e){
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board4));
                                break;
                            }

                            System.out.println("Your opponent made the move " + aiMove);
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            //copy.printBoard();
                            board4 = copy;
                            if (Agent.terminalTest(board4)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board4));
                                break;
                            }


                        }
                        break;
                    default:
                        break;

                }
            }
            else if (boardOption == 2) {
                System.out.println("Choose your opponent:");

                System.out.println("1. An agent that plays randomly");
                System.out.println("2. An agent that uses MINIMAX");
                System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");
                System.out.println("4. An agent that uses H-MINIMAX with a fixed depth cutoff");
                System.out.print("Your choice?");
                int option = scanner.nextInt();
                switch(option){
                    case 1:
                        Board board1 = new Board(8);
                        while (true) {

                            board1 = board1.checkKing(board1);
                            board1.printBoard();
                            System.out.println(human + " turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board1)&&!Board.LegalMove(move,board1)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }

                            board1 = Agent.result(board1, move);
                            System.out.println(Agent.actions(board1, computer));
                            if (Agent.terminalTest(board1)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board1));
                                break;
                            }

                            System.out.println(computer +" turn");
                            System.out.println("I am thinking...");
                            board1 = RandomAgent.random(board1,computer);
                            //board1.printBoard();
                            if (Agent.terminalTest(board1)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board1));
                                break;
                            }

                        }
                        break;
                    case 2:
                        Board board2 = new Board(8);
                        while (true) {
                            board2 = board2.checkKing(board2);
                            board2.printBoard();
                            System.out.println(human+" turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board2)&&!Board.LegalMove(move,board2)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board2 = Agent.result(board2, move);
                            System.out.println(Agent.actions(board2, computer));
                            System.out.println(computer + " turn");
                            System.out.println("I am thinking...");
                            Board copy = new Board(8);
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    copy.board.get(i).set(j, board2.board.get(i).get(j));
                                }
                            }
                            String aiMove = MiniMaxAgent.minMaxDecision(board2,computer);
                            try{
                                aiMove = Agent.actions(copy, computer).get(0);
                            }catch(Exception e){
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board2));
                                break;
                            }

                            System.out.println("Your opponent made the move " + aiMove);
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            //copy.printBoard();
                            board2 = copy;
                            if (Agent.terminalTest(board2)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board2));
                                break;
                            }
                        }
                        break;
                    case 3:
                        Board board3 = new Board(8);
                        while (true) {
                            board2 = board3.checkKing(board3);
                            board2.printBoard();

                            System.out.println(human+" turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board3)&&!Board.LegalMove(move,board3)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board3 = Agent.result(board3, move);
                            System.out.println(Agent.actions(board3, computer));

                            System.out.println(computer + " turn");
                            System.out.println("I am thinking...");
                            Board copy = new Board(8);
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    copy.board.get(i).set(j, board2.board.get(i).get(j));
                                }
                            }

                            String aiMove = abMiniMaxAgent.abMiniDecision(board3,computer);
                            try{
                                aiMove = Agent.actions(copy, computer).get(0);
                            }catch(Exception e){
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board3));
                                break;
                            }

                            System.out.println("Your opponent made the move " + aiMove);
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            //copy.printBoard();
                            board3 = copy;
                            if (Agent.terminalTest(board3)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board3));
                                break;
                            }

                        }
                        break;
                    case 4:
                        System.out.println("Depth limit:");
                        int depth = scanner.nextInt();
                        Board board4 = new Board(8);
                        while (true) {
                            board4 = board4.checkKing(board4);
                            board4.printBoard();

                            System.out.println(human+" turn");
                            System.out.println("Your move (? for help):");
                            String move = scanner.next();
                            while(!Board.Legalcapture(move,board4)&&!Board.LegalMove(move,board4)){
                                System.out.println("Illegal move");
                                System.out.println("Try again");
                                move = scanner.next();
                            }
                            board4 = Agent.result(board4, move);
                            System.out.println(Agent.actions(board4, computer));

                            System.out.println(computer + " turn");
                            System.out.println("I am thinking...");
                            Board copy = new Board(8);
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    copy.board.get(i).set(j, board4.board.get(i).get(j));
                                }
                            }

                            String aiMove = HMiniMaxAgent.hMinMaxDecision(board4,depth,computer);
                            try{
                                aiMove = Agent.actions(copy, computer).get(0);
                            }catch(Exception e){
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board4));
                                break;
                            }

                            System.out.println("Your opponent made the move " + aiMove);
                            copy = Agent.result(copy, aiMove);
                            copy = Board.checkKing(copy);
                            //copy.printBoard();
                            board4 = copy;
                            if (Agent.terminalTest(board4)) {
                                System.out.println("Game finished");
                                System.out.println("Winner: "+Agent.checkWinner(board4));
                                break;
                            }


                        }
                        break;
                    default:
                        break;

                }
            }
        }


    }
}
