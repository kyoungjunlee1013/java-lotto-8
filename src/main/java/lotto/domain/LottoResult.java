package lotto.domain;

import java.util.Collections;
import java.util.Map;
import java.util.EnumMap;

public class LottoResult {
    private final Map<Rank, Integer> result = new EnumMap<>(Rank.class);

    public LottoResult(){
        for(Rank rank : Rank.values()){
            result.put(rank, 0);
        }
    }
    public void addRank(Rank rank) {
        if (rank != Rank.MISS) {
            result.put(rank, result.get(rank) + 1); //키에 해당되는 integer의 값이 1 증가되는 것
        }
    }

    public Map<Rank, Integer> getResult() {
        return Collections.unmodifiableMap(result); // 해당 rank의 불변성을 위한 로직
    }
    public long calculateTotalPrize() {
        long totalPrize = 0;

        for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();

            // (해당 등수 상금 * 당첨 개수)를 모두 더함
            totalPrize += rank.getPrize() * count;
        }
        return totalPrize;
    }
}
