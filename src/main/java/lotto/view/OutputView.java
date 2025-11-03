package lotto.view;

import lotto.Lotto;

import java.util.List;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;

public class OutputView {

    public void printPurchasedLottos(List<Lotto> purchasedLottos) {
        int count = purchasedLottos.size();

        System.out.println("\n" + count + "개를 구매했습니다.");

        for (Lotto lotto : purchasedLottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        Map<Rank, Integer> result = lottoResult.getResult();

        printRankStatistic(Rank.FIFTH, result.get(Rank.FIFTH));
        printRankStatistic(Rank.FOURTH, result.get(Rank.FOURTH));
        printRankStatistic(Rank.THIRD, result.get(Rank.THIRD));
        printRankStatistic(Rank.SECOND, result.get(Rank.SECOND));
        printRankStatistic(Rank.FIRST, result.get(Rank.FIRST));
    }
    private void printRankStatistic(Rank rank, int count) {
        String matchInfo = formatMatchInfo(rank);
        System.out.printf("%s (%s원) - %d개%n", matchInfo, formatPrize(rank.getPrize()), count);
    }

    private String formatMatchInfo(Rank rank) {
        if (rank == Rank.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치", rank.getMatchCount());
        }
        return String.format("%d개 일치", rank.getMatchCount());
    }


    private String formatPrize(long prize) {
        return String.valueOf(prize);
    }

    public void printProfitRate(int purchaseAmount, LottoResult lottoResult) {
        long totalPrize = lottoResult.calculateTotalPrize();

        double profitRate = (double) totalPrize / purchaseAmount * 100;

        double roundedProfitRate = Math.round(profitRate * 100.0) / 100.0;

        String formattedRate = String.format("%.1f%%", roundedProfitRate);
        System.out.println(String.format("총 수익률은 %s입니다.", formattedRate));
    }
}


