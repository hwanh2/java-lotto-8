package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.List;

public class RandomLottoGenerator {
    public Lotto generate(){
        List<Integer> lotto = Randoms.pickUniqueNumbersInRange(Lotto.MIN_LOTTO_NUM, Lotto.MAX_LOTTO_NUM, Lotto.LOTTO_NUMBER_COUNT)
                .stream()
                .sorted()
                .toList();
        return new Lotto(lotto);
    }
}
