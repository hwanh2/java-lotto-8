package lotto.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RetryHandler {

    // 실행할 로직과 에러 처리 방법
    public static <T> T retry(Supplier<T> action, Consumer<String> errorHandler) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                errorHandler.accept(e.getMessage());
            }
        }
    }
}

