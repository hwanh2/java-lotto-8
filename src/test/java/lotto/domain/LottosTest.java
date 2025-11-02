package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    @DisplayName("당첨 통계를 계산한다")
    @Test
    void 당첨_통계_계산() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));  // 1등
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));  // 2등 (보너스)
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 8));  // 3등
        Lotto lotto4 = new Lotto(List.of(1, 2, 3, 4, 10, 11)); // 4등
        Lotto lotto5 = new Lotto(List.of(1, 2, 3, 10, 11, 12)); // 5등
        Lotto lotto6 = new Lotto(List.of(10, 11, 12, 13, 14, 15)); // 없음

        Lottos lottos = Lottos.createLottos(List.of(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6));
        WinningNumbers winningNumbers = WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 7);

        Map<LottoRank, Long> result = lottos.calculateResult(winningNumbers);

        assertThat(result.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.get(LottoRank.THIRD)).isEqualTo(1);
        assertThat(result.get(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(result.get(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(result.get(LottoRank.NONE)).isEqualTo(1);
    }

}

