package com.gabrst.sudokuvalidator.exception;

import com.gabrst.sudokuvalidator.ReturnCode;
import lombok.Getter;

@Getter
public abstract class SudokuValidationException extends Exception {

    private final ReturnCode returnCode;

    public SudokuValidationException(final String errorMessage, final ReturnCode returnCode) {
        super(errorMessage);
        this.returnCode = returnCode;
    }
}
