package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {
    @DisplayName("보너스 번호가 1보다 작으면 예외가 발생한다")
    @Test
    void 보너스_번호가_1보다_작으면_예외() {
        assertThatThrownBy(() -> WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다");
    }

    @DisplayName("보너스 번호가 45보다 크면 예외가 발생한다")
    @Test
    void 보너스_번호가_45보다_크면_예외() {
        assertThatThrownBy(() -> WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다");
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외() {
        assertThatThrownBy(() -> WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다");
    }

    @DisplayName("1등: 6개 번호 일치")
    @Test
    void 일등_당첨() {
        WinningNumbers winningNumbers = WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(winningNumbers.match(userLotto)).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("2등: 5개 번호 + 보너스 번호 일치")
    @Test
    void 이등_당첨() {
        WinningNumbers winningNumbers = WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        assertThat(winningNumbers.match(userLotto)).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("3등: 5개 번호 일치")
    @Test
    void 삼등_당첨() {
        WinningNumbers winningNumbers = WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        assertThat(winningNumbers.match(userLotto)).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("4등: 4개 번호 일치")
    @Test
    void 사등_당첨() {
        WinningNumbers winningNumbers = WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));

        assertThat(winningNumbers.match(userLotto)).isEqualTo(LottoRank.FOURTH);
    }

    @DisplayName("5등: 3개 번호 일치")
    @Test
    void 오등_당첨() {
        WinningNumbers winningNumbers = WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));

        assertThat(winningNumbers.match(userLotto)).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("없음: 2개 이하 일치")
    @Test
    void 없음() {
        WinningNumbers winningNumbers = WinningNumbers.createWithBonus(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));

        assertThat(winningNumbers.match(userLotto)).isEqualTo(LottoRank.NONE);
    }
}

