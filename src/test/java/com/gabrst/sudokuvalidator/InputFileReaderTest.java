package com.gabrst.sudokuvalidator;

import com.gabrst.sudokuvalidator.exception.InvalidInputException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputFileReaderTest {

    private final static InputFileReader UNDER_TEST = new InputFileReader();

    private static final String PATH_PREFIX = "src/test/resources/";

    @ParameterizedTest
    @ValueSource(strings = "example_valid.csv") //TODO: Should add more tests
    public void testValidFiles(final String input) {
        assertDoesNotThrow(() -> UNDER_TEST.getValidatedInput(PATH_PREFIX + input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"example_invalid_1.csv", "example_invalid_2.csv", "example_invalid_3.csv", "example_invalid_4.csv"})
    //TODO: Should add more tests
    public void testInvalidFiles(final String input) {
        final InvalidInputException ex = assertThrows(InvalidInputException.class, () ->
                UNDER_TEST.getValidatedInput(PATH_PREFIX + input));
        //assertThat(ex.getMessage()).isEqualTo("Duplicate value 1 in column 4 and row 0");
    }
}