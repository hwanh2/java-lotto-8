package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final int bonusNumber;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_BONUS_DUPLICATE = ERROR_PREFIX + "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private static final String ERROR_BONUS_RANGE = ERROR_PREFIX + "보너스 번호는 " + Lotto.MIN_LOTTO_NUM + "부터 " + Lotto.MAX_LOTTO_NUM + " 사이여야 합니다.";

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningLotto = new Lotto(winningNumbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        validateBonusRange(bonusNumber);
        validateBonusDuplicate(bonusNumber);
    }

    private void validateBonusRange(int bonusNumber) {
        if (bonusNumber < Lotto.MIN_LOTTO_NUM || bonusNumber > Lotto.MAX_LOTTO_NUM) {
            throw new IllegalArgumentException(ERROR_BONUS_RANGE);
        }
    }

    private void validateBonusDuplicate(int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_DUPLICATE);
        }
    }

    public LottoRank match(Lotto userLotto) {
        int matchCount = (int) winningLotto.countMatches(userLotto);
        boolean bonusMatch = userLotto.contains(bonusNumber);
        return LottoRank.findRank(matchCount, bonusMatch);
    }
}

