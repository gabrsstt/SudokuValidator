package com.gabrst.sudokuvalidator.exception;

import static com.gabrst.sudokuvalidator.ReturnCode.INVALID_INPUT;

public class InvalidInputException extends SudokuValidationException {
    public InvalidInputException(final String errorMessage) {
        super(errorMessage, INVALID_INPUT);
    }
}
