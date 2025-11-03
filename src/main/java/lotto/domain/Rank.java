// lotto.domain.Rank.java (신규 파일)
package lotto.domain;

public enum Rank {
    // 6개 일치 (1등)
    FIRST(6, false, 2_000_000_000L),
    // 5개 일치 + 보너스 볼 일치 (2등)
    SECOND(5, true, 30_000_000L),
    // 5개 일치 (3등)
    THIRD(5, false, 1_500_000L),
    // 4개 일치 (4등)
    FOURTH(4, false, 50_000L),
    // 3개 일치 (5등)
    FIFTH(3, false, 5_000L),
    // 2개 이하 일치 (낙첨)
    MISS(0, false, 0L);

    private final int matchCount;
    private final boolean matchBonus;
    private final long prize;

    Rank(int matchCount, boolean matchBonus, long prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    // 일치 개수 반환 (당첨 통계 출력에 사용)
    public int getMatchCount() {
        return matchCount;
    }

    public long getPrize() {
        return prize;
    }
    public static Rank valueOf(int matchCount, boolean matchBonus){
        if(matchCount < 3) {
            return MISS;
        }
        if(matchCount == 5 && matchBonus) {
            return SECOND;
        }
        for(Rank rank : values()) { // enum의 values는 해당 enum 타입에 정의된 모든 상수들을 배열(array) 형태로 반환한다.
            if(rank.matchCount == matchCount && rank.matchBonus == matchBonus) {
                return rank;
            }
        }
        return MISS;
    }
}