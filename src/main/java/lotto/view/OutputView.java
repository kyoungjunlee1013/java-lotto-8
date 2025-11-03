package lotto.view;

import lotto.Lotto;

import java.util.List;

public class OutputView {

    public void printPurchasedLottos(List<Lotto> purchasedLottos) {
        int count = purchasedLottos.size();

        System.out.println("\n" + count + "개를 구매했습니다.");

        for (Lotto lotto : purchasedLottos) {
            System.out.println(lotto.getNumbers());
        }
    }
}
