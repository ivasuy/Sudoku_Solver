package com.vasu.sudokuSolver.model;

import org.springframework.stereotype.Component;

@Component
public class SudokuSolver {
    public int[][] solveSudoku(int[][] board){

        int[] emptyCell = findEmpty(board);
        if(emptyCell == null) return board;

        int emptyCellRow = emptyCell[0];
        int emptyCellColumn = emptyCell[1];

        for(int i = 1; i <= 9; i++){
            if(isValid(board, emptyCellRow, emptyCellColumn, i)){
                board[emptyCellRow][emptyCellColumn] = i;

                int[][] solvedBoard = solveSudoku(board);
                if(solvedBoard != null) return solvedBoard;

                board[emptyCellRow][emptyCellColumn] = 0;
            }
        }

        return null;
    }

    public int[] findEmpty(int[][] board){

        for(int i = 0; i < board.length; i++){
            for(int j =  0; j < board[0].length; j++){
                if(board[i][j] == 0) return new int[]{i, j};
            }
        }
        return null;
    }
    public boolean isValid(int[][] board, int currRow, int currCol, int num){

        for(int i = 0; i < 9; i++){

            if(board[currRow][i] == num) return false;
            if(board[i][currCol] == num) return false;
            if(board[3 * (currRow/3) + i/3][3 * (currCol/3) + i%3] == num) return false;

        }

        return true;
    }
}
