package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public static final int MIN_LOTTO_NUM = 1;
    public static final int MAX_LOTTO_NUM = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_SIZE = ERROR_PREFIX + "로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.";
    private static final String ERROR_DUPLICATE_NUMBERS = ERROR_PREFIX + "로또 번호는 중복될 수 없습니다.";
    private static final String ERROR_INVALID_RANGE = ERROR_PREFIX + "로또 번호는 " + MIN_LOTTO_NUM + "부터 " + MAX_LOTTO_NUM + " 사이여야 합니다;";


    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateNoDuplicate(numbers);
        validateNumberRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    private void validateNoDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBERS);
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_LOTTO_NUM || number > MAX_LOTTO_NUM) {
                throw new IllegalArgumentException(ERROR_INVALID_RANGE);
            }
        }
    }

    public long countMatches(Lotto other) {
        return numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }
}