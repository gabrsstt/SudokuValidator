package com.gabrst.sudokuvalidator;

import com.gabrst.sudokuvalidator.exception.InvalidInputException;
import com.gabrst.sudokuvalidator.exception.InvalidSolutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gabrst.sudokuvalidator.ReturnCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SudokuValidatorTest {

    @Mock
    SudokuValidation sudokuValidation;

    @Mock
    InputFileReader inputFileReader;

    @Test
    public void testValid() throws InvalidInputException {
        when(inputFileReader.getValidatedInput(eq("someFile.csv"))).thenReturn(new int[][]{{1}, {1}});
        final ReturnCode ret = SudokuValidator.runValidation("someFile.csv", inputFileReader, sudokuValidation);
        assertThat(ret).isEqualTo(VALID);
    }

    @Test
    public void testInvalidInput() throws InvalidInputException {
        when(inputFileReader.getValidatedInput(eq("someFile.csv"))).thenThrow(new InvalidInputException("Some msg"));
        final ReturnCode ret = SudokuValidator.runValidation("someFile.csv", inputFileReader, sudokuValidation);
        assertThat(ret).isEqualTo(INVALID_INPUT);
    }

    @Test
    public void testInvalidSolution() throws InvalidSolutionException {
        doThrow(new InvalidSolutionException("Some msg")).when(sudokuValidation).validate(any());
        final ReturnCode ret = SudokuValidator.runValidation("someFile.csv", inputFileReader, sudokuValidation);
        assertThat(ret).isEqualTo(INVALID_SOLUTION);
    }

}