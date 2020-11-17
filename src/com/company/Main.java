package com.company;

public class Main {

    private static boolean possible(int[][] board, int x, int y, int value){
        for(int i = 0; i < 9; i++){
            if(board[y][i] == value || board[i][x] == value){
                return false;
            }
        }
        int x0 = (int)Math.floor(x/3) * 3;
        int y0 = (int)Math.floor(y/3) * 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (board[y0+i][x0+j] == value){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isSafe(int[][] board,
                                 int row, int col,
                                 int num)
    {
        for (int d = 0; d < board.length; d++)
        {

            // Kollar ifall nummer redan finns i den raden, returnera false.
            if (board[row][d] == num) {
                return false;
            }
        }

        for (int r = 0; r < board.length; r++)
        {

            // Kollar ifall nummer redan finns i den kolumnen, returnera false.
            if (board[r][col] == num)
            {
                return false;
            }
        }

        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++)
        {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++)
            {
                if (board[r][d] == num)
                {
                    return false;
                }
            }
        }

        return true;
    }

    protected static boolean solve(int[][] board, int n){
        {
            int row = -1;
            int col = -1;
            boolean isEmpty = true;
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (board[i][j] == 0)
                    {
                        row = i;
                        col = j;

                        isEmpty = false;
                        break;
                    }
                }
                if (!isEmpty) {
                    break;
                }
            }

            if (isEmpty)
            {
                return true;
            }

            for (int num = 1; num <= n; num++)
            {
                if (isSafe(board, row, col, num))
                {
                    board[row][col] = num;
                    if (solve(board, n))
                    {
                        return true;
                    }
                    else
                    {
                        board[row][col] = 0;
                    }
                }
            }
            return false;
        }

    }

    public static void printBoard(
            int[][] board, int N)
    {

        for (int r = 0; r < N; r++)
        {
            for (int d = 0; d < N; d++)
            {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int)Math.sqrt(N) == 0)
            {
                System.out.print("");
            }
        }
    }

    public static void main(String[] args) {
        int [][] board = {{0, 0, 0, 8, 0, 4, 9, 3, 7},
                {0, 7, 4, 1, 0, 0, 0, 8, 0},
                {8, 3, 2, 0, 0, 0, 4, 0, 0},
                {2, 0, 5, 3, 0, 0, 7, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 6},
                {1, 4, 3, 0, 0, 0, 2, 0, 0},
                {0, 0, 7, 0, 9, 0, 6, 0, 0},
                {0, 2, 1, 7, 5, 6, 8, 9, 0},
                {6, 5, 9, 2, 3, 0, 0, 7, 4}};

        System.out.println(possible(board, 4, 4, 2));
        System.out.println(possible(board, 4, 4, 1));
        System.out.println(possible(board, 5, 6, 3));
        System.out.println(possible(board, 0, 0, 5));

        int N = board.length;
        if (solve(board, N))
        {
            printBoard(board, N);
        }
        else {
            System.out.println("No solution");
        }
    }
}
