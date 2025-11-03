package lotto;

import lotto.controller.LottoGameController;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        LottoGameController controller = new LottoGameController(new InputView());
        controller.start();
    }
}
