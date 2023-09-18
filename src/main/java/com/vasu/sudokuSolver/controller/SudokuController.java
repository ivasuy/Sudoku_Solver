package com.vasu.sudokuSolver.controller;

import com.vasu.sudokuSolver.model.SudokuSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(
        origins = "http://localhost:5173"
)
@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

    @Autowired
    private SudokuSolver sudokuSolver;

    @PostMapping("/solve")
    public ResponseEntity<List<String>> solveSudoku(@RequestBody List<List<Integer>> puzzle){

        int[][] sudoku = new int[puzzle.size()][puzzle.get(0).size()];

        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).size(); j++) {
                sudoku[i][j] = puzzle.get(i).get(j);
            }
        }

        int[][] solvedSudoku = sudokuSolver.solveSudoku(sudoku);

        if(solvedSudoku != null){
            List<String> formattedRows = new ArrayList<>();
            for (int[] row : solvedSudoku) {
                String formattedRow = Arrays.toString(row)
                        .replace("[", "")
                        .replace("]", "")
                        .replace(",", "");
                formattedRows.add(formattedRow);
            }

            return ResponseEntity.ok(formattedRows);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
