package com.gabrst.sudokuvalidator.exception;

import static com.gabrst.sudokuvalidator.ReturnCode.INVALID_SOLUTION;

public class InvalidSolutionException extends SudokuValidationException {
    public InvalidSolutionException(final String errorMessage) {
        super(errorMessage, INVALID_SOLUTION);
    }
}
