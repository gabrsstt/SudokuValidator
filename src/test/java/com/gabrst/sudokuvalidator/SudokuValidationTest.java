package com.gabrst.sudokuvalidator;

import com.gabrst.sudokuvalidator.exception.InvalidSolutionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SudokuValidationTest {

    private final static SudokuValidation UNDER_TEST = new SudokuValidation();

    private final static int[][] VALID_SOLUTION = {{4, 2, 9, 8, 1, 3, 5, 6, 7}, {5, 1, 6, 4, 7, 2, 9, 3, 8}, {7, 8, 3, 6, 5, 9, 2, 4, 1},
            {6, 7, 2, 1, 3, 4, 8, 5, 9}, {3, 9, 5, 2, 8, 6, 1, 7, 4}, {8, 4, 1, 7, 9, 5, 6, 2, 3},
            {1, 5, 8, 3, 6, 7, 4, 9, 2}, {9, 3, 4, 5, 2, 8, 7, 1, 6}, {2, 6, 7, 9, 4, 1, 3, 8, 5}};

    @Test
    public void testValidSolution() {
        assertDoesNotThrow(() -> UNDER_TEST.validate(VALID_SOLUTION));
    }


    @Test
    public void checkRowFails() {
        final int[][] wrong = cloneArray(VALID_SOLUTION);
        wrong[0][0] = 1;
        final InvalidSolutionException ex = assertThrows(InvalidSolutionException.class, () ->
                UNDER_TEST.validate(wrong));
        assertThat(ex.getMessage()).isEqualTo("Duplicate value 1 in column 4 and row 0");
    }

    private static int[][] cloneArray(final int[][] input) {
        int[][] ret = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            ret[i] = input[i].clone();
        }
        return ret;
    }

}