package lotto.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    // 기본적인 예외 처리 및 검증을 포함함
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_NOT_NUMBER = ERROR_PREFIX + "숫자를 입력해야 합니다.";
    private static final String ERROR_INVALID_NUMBER_FORMAT = ERROR_PREFIX + "올바른 형식의 숫자를 입력해야 합니다.";
    private static final String DELIMITER = ",";

    public static int parseAmount(String input) {
        validateNotBlank(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }

    public static List<Integer> parseLottoNumbers(String input) {
        validateNotBlank(input);
        String[] parseNumbers = input.split(DELIMITER);
        try {
            return Arrays.stream(parseNumbers)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER_FORMAT);
        }
    }

    public static int parseBonusNumber(String input) {
        validateNotBlank(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }
}

