package com.company;

public class Main {

    private static boolean possible(int[][] board, int x, int y, int value){
        for(int i = 0; i < 9; i++){
            if(board[y][i] == value || board[i][x] == value){
                return false;
            }
        }
        int x0 = (x/3) * 3;
        int y0 = (y/3) * 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (board[y0+i][x0+j] == value){
                    return false;
                }
            }
        }
        return true;
    }

    protected static boolean solve(int[][] board, int n){
        {
            for(int y = 0; y < 9; y++){
                for(int x = 0; x < 9; x++){
                    if (board[y][x] == 0) {
                        for (int i=1; i <= 9; i++){
                            if (possible(board,x,y,i)) {
                                board[y][x] = i;
                                solve(board, n);
                                board[y][x] = 0;
                            }
                        }
                        return false;
                    }
                }
            }
            printBoard(board, n);
        }
        return false;
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
        int [][] board = {
                {0, 0, 0, 8, 0, 4, 9, 3, 7},
                {0, 7, 4, 1, 0, 0, 0, 8, 0},
                {8, 3, 2, 0, 0, 0, 4, 0, 0},
                {2, 0, 5, 3, 0, 0, 7, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 6},
                {1, 4, 3, 0, 0, 0, 2, 0, 0},
                {0, 0, 7, 0, 9, 0, 6, 0, 0},
                {0, 2, 1, 7, 5, 6, 8, 9, 0},
                {6, 5, 9, 2, 3, 0, 0, 7, 4}};

        System.out.println(possible(board, 4, 4, 4));
        System.out.println(possible(board, 4, 4, 1));
        System.out.println(possible(board, 5, 6, 6));
        System.out.println(possible(board, 5, 6, 1));
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
