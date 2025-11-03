package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        // 테스트마다 새로운 LottoResult 객체로 초기화 (모든 Rank는 0으로 시작)
        lottoResult = new LottoResult();
    }

    // 3.3: 등수별 개수 집계 테스트
    @Test
    @DisplayName("Rank별 당첨 개수가 정확히 집계되는지 확인한다.")
    void addRankIncrementsCount() {
        lottoResult.addRank(Rank.FIFTH);  // 5등 1개
        lottoResult.addRank(Rank.FIFTH);  // 5등 2개
        lottoResult.addRank(Rank.THIRD);  // 3등 1개
        lottoResult.addRank(Rank.MISS);   // MISS는 집계 제외

        assertThat(lottoResult.getResult().get(Rank.FIFTH)).isEqualTo(2);
        assertThat(lottoResult.getResult().get(Rank.THIRD)).isEqualTo(1);
        // MISS는 집계되지 않으므로 0으로 유지
        assertThat(lottoResult.getResult().get(Rank.MISS)).isEqualTo(0);
    }

    // 4.2: 총 상금 계산 테스트
    @Test
    @DisplayName("집계된 결과를 바탕으로 총 상금을 정확하게 계산한다.")
    void calculateTotalPrizeTest() {
        // 5등 (5,000원) 1개, 4등 (50,000원) 2개 당첨 가정
        lottoResult.addRank(Rank.FIFTH);
        lottoResult.addRank(Rank.FOURTH);
        lottoResult.addRank(Rank.FOURTH);

        // 계산: (5,000 * 1) + (50,000 * 2) = 105,000
        long expectedPrize = 5_000L + 100_000L;
        long actualPrize = lottoResult.calculateTotalPrize();

        assertThat(actualPrize).isEqualTo(expectedPrize);
    }
}