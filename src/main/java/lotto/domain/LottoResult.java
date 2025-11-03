package lotto.domain;

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
            result.put(rank, result.get(rank) + 1);
        }
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
