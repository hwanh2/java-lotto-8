package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoTest {

    @DisplayName("로또 번호가 6개 미만이면 예외가 발생한다")
    @Test
    void 로또_번호가_6개_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호가 6개를 초과하면 예외가 발생한다")
    @Test
    void 로또_번호가_6개를_초과하면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("로또 번호가 1보다 작으면 예외가 발생한다")
    @Test
    void 로또_번호가_1보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(-1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이여야 합니다");
    }

    @DisplayName("로또 번호가 45보다 크면 예외가 발생한다")
    @Test
    void 로또_번호가_45보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이여야 합니다");
    }
}

