package com.gabrst.sudokuvalidator;

import com.gabrst.sudokuvalidator.exception.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputFileReader {

    public int[][] getValidatedInput(final String filename) throws InvalidInputException {
        int[][] output = new int[9][9];
        int currentLine = 0;
        try (BufferedReader br = new BufferedReader((new FileReader(filename)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (currentLine > 8) {
                    throw new InvalidInputException("File contains more lines than expected");
                }
                String[] values = line.split(",");
                if (values.length != 9) {
                    throw new InvalidInputException("Line " + currentLine + " contains more values than expected - " + values.length);
                }
                for (int i = 0; i < 9; i++) {
                    output[currentLine][i] = mapAndValidate(values[i]);
                }
                currentLine++;
            }
        } catch (IOException e) {
            throw new InvalidInputException("Error reading file " + filename);
        }
        if (currentLine != 9) {
            throw new InvalidInputException("File contains less lines than expected");
        }
        return output;
    }

    private int mapAndValidate(final String input) throws InvalidInputException {
        int mappedValue;
        try {
            mappedValue = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new InvalidInputException("Cannot convert to int " + input);
        }
        if (mappedValue < 1 || mappedValue > 9) {
            throw new InvalidInputException("Value not allowed " + input);
        }
        return mappedValue;
    }
}
