import  java.util.*;

public class Solution {
    private static final char player = 'X', opponent = 'O';
    public static  void main(String args[]){
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = '_';
            }
        }
        display(board);
        while(true){

            int move = sc.nextInt();
            switch (move){
                case 1:
                    board[0][0] = 'X';
                    break;
                case 2:
                    board[0][1] = 'X';
                    break;
                case 3:
                    board[0][2] = 'X';
                    break;
                case 4:
                    board[1][0] = 'X';
                    break;
                case 5:
                    board[1][1] = 'X';
                    break;
                case 6:
                    board[1][2] = 'X';
                    break;
                case 7:
                    board[2][0] = 'X';
                    break;
                case 8:
                    board[2][1] = 'X';
                    break;
                case 9:
                    board[2][2] = 'X';
                    break;
            }
            display(board);

            int hasWon = hasWon(board);
            if(hasWon == 10){
                System.out.println("X WON");
                break;
            }
            else if(hasWon==-10){
                System.out.println("O WON");
                break;
            }
            if(isDraw(board)){
                System.out.println("GAME DRAQ");
                break;
            }

            Move m = bestMove(board);
            board[m.i][m.j] = opponent;
            display(board);

        }

    }

    static void display(char[][] board){
        System.out.flush();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(board[i][j]+"  ");
            }
            System.out.println();
        }

    }


    static Move bestMove(char[][] board){
        Move m = new Move(-1,-1);
        int score = Integer.MIN_VALUE;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]=='_'){
                    board[i][j] = opponent;
                    int value = miniMax(board,0,false);
                    board[i][j] = '_';
                    if(value>score){
                        score = value;
                        m.i = i;
                        m.j = j;
                    }

                }
            }
        }
        return m;

    }

    static int miniMax(char[][] board, int depth, boolean isMaximize){

        int score = hasWon(board);
        if(score==10) return score;
        if(score==-10) return score;
        if(isDraw(board)) return 0;

        if(isMaximize){

            int best = Integer.MIN_VALUE;
            for(int i=0; i<3; i++){
                for(int j=0;j<3;j++){
                    if(board[i][j]=='_'){
                        board[i][j] = player;
                        best = Math.max(best,miniMax(board,depth+1,!isMaximize));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
        else{

            int best = Integer.MAX_VALUE;
            for(int i=0;i<3;i++){
                for(int j =0;j<3;j++){
                    if(board[i][j] == '_'){
                        board[i][j] = opponent;
                        best = Math.min(best,miniMax(board,depth+1,!isMaximize));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

    }

    static boolean isDraw(char board[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]=='_') return false;
            }
        }
        int score = hasWon(board);
        if(score == 0) return true;
        return false;
    }

    static int hasWon(char[][] board){

        for(int i=0;i<3;i++){

            if(board[i][0] == board[i][1] && board[i][0] == board[i][2]) {

                if (board[i][0] == player) return 10;
                else if (board[i][0] == opponent) return -10;

            }
        }

        for(int i=0;i<3;i++) {

            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {

                if (board[0][i] == player) return 10;
                else if (board[i][0] == opponent) return -10;

            }
        }

        if(board[0][0] == board[1][1] && board[0][0] == board[2][2]){
            if(board[0][0] == player) return 10;
            else if(board[0][0] == opponent) return -10;
        }
        if(board[0][2]==board[1][1] && board[0][2] == board[2][0]){
            if(board[0][2]==player) return 10;
            else if(board[0][2]==opponent) return -10;
        }
        return 0;



    }
}
class Move{
    int i,j;
    public Move(int i, int j){
        this.i = i;
        this.j = j;
    }
}