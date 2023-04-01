import java.util.*;
class Game {
    private static char[][] create_board(){
        char[][] a = new char[3][3];
        for(char[] row : a){
            for(int i=0;i<row.length;i++) row[i] = ' ';
        }
        return a;
    }
    private static void play(char player, int row, int col, char[][] board){
        if(row<0 || row>3 || col<0 || col>2 || board[row][col] != ' '){
            System.out.println("INVALID MOVE..!");
            return;
        }
        board[row][col] = player;
    }
    private static void display(char[][] a){
        System.out.println(" -------------");
        for(char[] row : a){
            for(int i=0;i<row.length;i++) System.out.print(" | "+row[i]);
            System.out.println(" |");
            System.out.println(" -------------");
        }
    }
    private static boolean is_game_over(char player, char[][] board){
        for(int i=0;i<board.length;i++){
            if(board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
        }
        for(int j=0;j<board.length;j++){
            if(board[0][j] == player && board[1][j] == player && board[2][j] == player) return true;
        }
        return ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player));
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        char[][] board = create_board();
        boolean gameOver = false;
        char player = 'O';
        int row, col;
        while(!gameOver){
            player = player == 'X' ? 'O' : 'X';
            System.out.println("Row and Col for '"+player +"' ->");
            row = in.nextInt();
            col = in.nextInt();
            play(player, row, col, board);
            gameOver = is_game_over(player, board);
            display(board);
        }
        System.out.println("Player "+player+" Won ..!");
    }
}
