package com.gabrst.sudokuvalidator;

import com.gabrst.sudokuvalidator.exception.SudokuValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SudokuValidator {

    public static void main(final String[] args) {
        log.info("Starting sudoku validator..");
        if (args.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments, expected 1 got " + args.length);
        }
        final ReturnCode returnCode = runValidation(args[0], new InputFileReader(), new SudokuValidation());
        System.out.println(returnCode.name());
        System.out.println(returnCode.getReturnCode());
    }

    /*In reality I would have used Spring to wire these dependencies*/
    protected static ReturnCode runValidation(final String fileName, final InputFileReader inputFileReader, final SudokuValidation sudokuValidator) {
        try {
            final int[][] solution = inputFileReader.getValidatedInput(fileName);
            sudokuValidator.validate(solution);
            log.info("The solution is valid!");
            return (ReturnCode.VALID);
        } catch (SudokuValidationException e) {
            log.warn("The solution is not valid");
            log.warn(e.getMessage());
            return (e.getReturnCode());
        }
    }
}
