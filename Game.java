import java.util.*;
class Tic_Tac_Toe {
    private static void check_winner(boolean X, boolean O, boolean empty){
        if(!empty){
            if(X && !O){
                System.out.println("       X won ..!");
                System.exit(0);
            }
            else if(O && !X){
                System.out.println("       O won ..!");
                System.exit(0);
            }
        }
    }
    private static void winner(char[][] board){
        boolean X;
        boolean O;
        boolean empty;
        for(char[] row : board){
            X = O = empty = false;
            for(char ch : row){
                if(ch == 'X') X = true;
                else if(ch == 'O') O = true;
                else if(ch == '-') empty = true;
            }
            check_winner(X,O,empty);
        }
        int i = 0, j = 0;
        for(j=0;j<board.length;j++){
            X =  O = empty = false;
            for(i=0;i<board.length;i++){
                if(board[i][j] == 'X') X = true;
                else if(board[i][j] == 'O') O = true;
                else if(board[i][j] == '-') empty = true;
            }
            check_winner(X,O,empty);
        }

        i = 0;
        j = 0;
        X =  O = empty = false;
        while(i < board.length && j < board[i].length){
            if(board[i][j] == 'X') X = true;
            else if(board[i][j] == 'O') O = true;
            else if(board[i][j] == '-') empty = true;
            i++;
            j++;
        }
        check_winner(X,O,empty);

        X =  O = empty = false;
        i = 0;
        j = board[i].length-1;
        while(i < board.length && j >= 0){
            if(board[i][j] == 'X') X = true;
            else if(board[i][j] == 'O') O = true;
            else if(board[i][j] == '-') empty = true;
            i++;
            j--;
        }
        check_winner(X,O,empty);
    }
    private static boolean play(char[][] board, String r_c_d, int index){
        switch (r_c_d) {
            case "r" -> {
                for (int j = 0; j < board[index].length; j++) {
                    if (board[index][j] == '-') {
                        board[index][j] = 'O';
                        return true;
                    }
                }
            }
            case "c" -> {
                for (int i = 0; i < board.length; i++) {
                    if (board[i][index] == '-') {
                        board[i][index] = 'O';
                        return true;
                    }
                }
            }
            case "ld" -> {
                int i = 0, j = 0;
                while (i < board.length && j < board[i].length) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'O';
                        return true;
                    }
                    i++;
                    j++;
                }
            }
            case "rd" -> {
                int i = 0, j = board[i].length - 1;
                while (i < board.length && j >= 0) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'O';
                        return true;
                    }
                    i++;
                    j--;
                }
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == '-'){
                    board[i][j] = 'O';
                    return true;
                }
            }
        }
        return false;
    }
    private static String find_max_count(int[] max,char[][] board, ArrayList<Integer> list_row, ArrayList<Integer> list_col,
                                         ArrayList<Integer> list_diag){
        String str = "";
        int index;
        for(int i=0;i<list_row.size(); i++){
            if(list_row.get(i) >= max[0]){
                max[0] = list_row.get(i);
                str = "r "+i;
            }
        }
        for(int i=0;i<list_col.size(); i++){
            if(list_col.get(i) >= max[0]){
                max[0] = list_col.get(i);
                str = "c "+i;
            }
        }
        if(list_diag.get(0) >= max[0]) {
            max[0] = list_diag.get(0);
            str = "ld " + 0;
        }
        if(list_diag.get(1) >= max[0]) {
            max[0] = list_diag.get(1);
            str = "rd " + 1;
        }
        return str;
    }
    private static void is_diagonal_list(int row, int col, char[][] board,
                                         ArrayList<Integer> diagonal_list){
        int i=0 , j=0 ;
        boolean found = false;
        while(!found && i<board.length && j < board[i].length){
            if(i == row && j == col){
                diagonal_list.set(0, diagonal_list.get(0)+1);
                found = true;
            }
            i++;
            j++;
        }
        i = 0;
        j = board[i].length-1;
        while(!found && i<board.length && j >=0){
            if(i == row && j == col){
                diagonal_list.set(1, diagonal_list.get(1)+1);
                found = true;
            }
            i++;
            j--;
        }
    }
    private static boolean check_move(char[][] board, int row, int col){
        return row<0 || row>=board.length || col<0 || col>=board.length || board[row][col] != '-';
    }
    private static char[][] board(int row, int col){
        char[][] board = new char[row][col];
        for(char[] Row : board) {
            Arrays.fill(Row, '-');
        }
        return board;
    }
    private static void print_board(int input, char[][] board){
        System.out.println();
        for(char[] row : board){
            System.out.print(" | ");
            for(char ch : row) System.out.print(ch+" | ");
            System.out.println();
        }
    }
    private static void initialize_list(int size, ArrayList<Integer> row_count,
                                        ArrayList<Integer> col_count,
                                        ArrayList<Integer> diagonal_count){
        for(int i=0;i<size;i++){
            row_count.add(0);
            col_count.add(0);
        }
        for(int i=0;i<2;i++) diagonal_count.add(0);
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the n (rows and columns): ");
        int input = in.nextInt(), row, col;
        row= col= input;
        int[] max_num = {1};
        char[][] board = board(row, col);
        print_board(input, board);
        ArrayList<Integer> row_list = new ArrayList<>();
        ArrayList<Integer> col_list = new ArrayList<>();
        ArrayList<Integer> diagonal_list = new ArrayList<>();
        initialize_list(input, row_list, col_list,diagonal_list);
        boolean run = true, valid_move = true;
        int total_moves = row  * col, moves_completed = 0, incremented_list;
        while(run){
            while(valid_move){
                System.out.print("Enter row and col: ");
                row = in.nextInt();
                col = in.nextInt();
                valid_move = check_move(board, row, col);
                if(valid_move) System.out.println("Enter Valid Position..!");
            }
            board[row][col] = 'X';
            incremented_list = row_list.get(row)+1;
            row_list.set(row,incremented_list);
            incremented_list = col_list.get(col)+1;
            col_list.set(col,incremented_list);
            is_diagonal_list(row, col, board, diagonal_list);
            String max = find_max_count(max_num, board, row_list, col_list, diagonal_list);
            String[] split = max.split(" ");
            run = play(board, split[0], Integer.parseInt(split[1]));
            moves_completed += 2;
            valid_move = moves_completed < total_moves;
            print_board(input, board);
            winner(board);
        }
        System.out.println("       Tie ..!");
    }
}
