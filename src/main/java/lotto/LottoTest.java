package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("로또 번호의 개수가 6개가 아니면 예외가 발생한다.")
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다.")
    void createLottoByUnderSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또와 당첨 번호의 일치 개수를 정확히 반환한다.")
    void countMatchTest() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        int matchCount = userLotto.countMatch(winningNumbers);
        // 1, 2, 3 세 개 일치
        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("로또에 보너스 번호가 포함되어 있는지 정확히 반환한다.")
    void hasBonusTest() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;
        int nonBonusNumber = 7;

        // 6은 포함됨
        assertThat(userLotto.hasBonus(bonusNumber)).isTrue();
        // 7은 포함되지 않음
        assertThat(userLotto.hasBonus(nonBonusNumber)).isFalse();
    }
}