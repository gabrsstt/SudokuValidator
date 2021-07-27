package com.gabrst.sudokuvalidator;

import com.gabrst.sudokuvalidator.exception.InvalidSolutionException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SudokuValidation {

    public void validate(final int[][] solution) throws InvalidSolutionException {

        final boolean[][] columns = new boolean[9][9];
        final boolean[][] rows = new boolean[9][9];
        final boolean[][][] nestedSquared = new boolean[3][3][9];

        log.info("Validating solution...");
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                final int current = solution[r][c];
                final int nestedColumnSquare = getNestedSquareForPosition(c);
                final int nestedRowSquare = getNestedSquareForPosition(r);
                if (columns[c][current - 1]) {
                    throw new InvalidSolutionException("Duplicate value " + current + " in column " + c + " and row " + r);
                }
                if (rows[r][current - 1]) {
                    throw new InvalidSolutionException("Duplicate value " + current + " in column " + c + " and row " + r);
                }
                if (nestedSquared[nestedColumnSquare][nestedRowSquare][current - 1]) {
                    throw new InvalidSolutionException("Duplicate value in nested square " + current + " in column " + c + " and row " + r);
                }
                columns[c][current - 1] = true;
                rows[r][current - 1] = true;
                nestedSquared[nestedColumnSquare][nestedRowSquare][current - 1] = true;
            }
        }
    }

    private int getNestedSquareForPosition(final int position) {
        return position / 3;
    }
}
