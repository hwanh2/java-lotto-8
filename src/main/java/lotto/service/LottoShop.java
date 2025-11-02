package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.generator.RandomLottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_AMOUNT = ERROR_PREFIX + "구입 금액은 1000원 단위여야 합니다.";
    private static final String ERROR_NEGATIVE_AMOUNT = ERROR_PREFIX + "구입 금액은 0보다 커야 합니다.";

    private final RandomLottoGenerator generator;

    public LottoShop(RandomLottoGenerator generator) {
        this.generator = generator;
    }

    public Lottos purchaseLottos(int amount) {
        validateAmount(amount);
        int count = amount / LOTTO_PRICE;
        return generateLottos(count);
    }

    private void validateAmount(int amount) {
        validatePositiveAmount(amount);
        validateDivisibleByLottoPrice(amount);
    }

    private void validatePositiveAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_AMOUNT);
        }
    }

    private void validateDivisibleByLottoPrice(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_INVALID_AMOUNT);
        }
    }

    private Lottos generateLottos(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(generator.generate());
        }
        return Lottos.createLottos(lottoList);
    }
}

