package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int COUNT = 6;

    private Lotto generateSingleLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, COUNT);
        return new Lotto(numbers);
    }

    public List<Lotto> generateLottos(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateSingleLotto())
                .collect(Collectors.toList());
    }
}
