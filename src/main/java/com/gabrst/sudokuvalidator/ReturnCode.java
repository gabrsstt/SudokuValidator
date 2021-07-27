package com.gabrst.sudokuvalidator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReturnCode {

    VALID(0, "OK"),
    INVALID_INPUT(1, "The input format is invalid"),
    INVALID_SOLUTION(2, "The solution is not valid");

    private final int returnCode;
    private final String codeString;
}
