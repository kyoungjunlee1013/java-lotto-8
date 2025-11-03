package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    // 3.2: 일치 개수와 보너스 일치 여부에 따른 등수 판별 테스트
    @ParameterizedTest
    @CsvSource(value = {
            "6, false, FIRST",   // 1등
            "5, true, SECOND",    // 2등 (5개 + 보너스)
            "5, false, THIRD",   // 3등
            "4, false, FOURTH",  // 4등
            "3, false, FIFTH",   // 5등
            "5, true, SECOND"    // 중복 확인 (2등)
    })
    @DisplayName("일치 개수와 보너스 일치 여부에 따라 정확한 Rank를 반환한다.")
    void valueOfReturnsCorrectRank(int matchCount, boolean matchBonus, Rank expectedRank) {
        Rank actualRank = Rank.valueOf(matchCount, matchBonus);
        assertThat(actualRank).isEqualTo(expectedRank);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2, false, MISS",    // 2개 일치 (낙첨)
            "0, false, MISS"     // 0개 일치 (낙첨)
    })
    @DisplayName("3개 미만 일치 시 MISS Rank를 반환한다.")
    void valueOfReturnsMISS(int matchCount, boolean matchBonus, Rank expectedRank) {
        Rank actualRank = Rank.valueOf(matchCount, matchBonus);
        assertThat(actualRank).isEqualTo(expectedRank);
    }


    @Test
    @DisplayName("각 Rank의 당첨 금액을 정확히 반환하는지 확인한다.")
    void rankPrizeTest() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000L);
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000L);
        assertThat(Rank.FIFTH.getPrize()).isEqualTo(5_000L);
        assertThat(Rank.MISS.getPrize()).isEqualTo(0L);
    }
}