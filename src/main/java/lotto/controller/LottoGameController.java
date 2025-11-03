package lotto.controller;

import lotto.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

import static lotto.validator.InputValidator.validateAndConvertAmount;

public class LottoGameController {
    private final InputView inputView;
    private final LottoGenerator lottoGenerator;
    private final OutputView outputView;

    public LottoGameController(InputView inputView) {
        this.inputView = inputView;
        this.lottoGenerator = new LottoGenerator();
        this.outputView = new OutputView();
        // 생성자에서 inputview를 초기화한 이유는 해당 인스턴스 변수는 객체가 생성될 때 무조건 한번 초기화되어야하기 때문이다.
        // 또한 public을 안 붙이면 application에서 해당 생성자에 접근할 수 없기 때문에 public을 붙여줘야 한다.
    }

    public void start() {
        int amount = readPurchaseAmountWithRetry();
        int lottoCount = calculateLottoCount(amount);
        List<Lotto> purchasedLottos = lottoGenerator.generateLottos(lottoCount);
        outputView.printPurchasedLottos(purchasedLottos);
        List<Integer> winningNumbers = readWinningNumbersWithRetry();
        int bonusNumber = readBonusNumberWithRetry(winningNumbers);
        LottoResult lottoResult = compileLottoResults(purchasedLottos, winningNumbers, bonusNumber);
        outputView.printWinningResult(lottoResult);
        outputView.printProfitRate(amount, lottoResult);

    }

    private int readPurchaseAmountWithRetry() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return validateAndConvertAmount(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Integer> readWinningNumbersWithRetry() {
        while (true) {
            try {
                String input = inputView.readWinningNumbers();
                return InputValidator.validateAndConvertWinningNumbers(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readBonusNumberWithRetry(List<Integer> winningNumbers) {
        while (true) {
            try {
                String input = inputView.readBonusNumber();
                return InputValidator.validateAndConvertBonusNumber(input, winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int calculateLottoCount(int amount) {
        return amount / lotto.validator.InputValidator.LOTTO_PRICE;
    }

    private LottoResult compileLottoResults(
            List<Lotto> purchasedLottos,
            List<Integer> winningNumbers,
            int bonusNumber) {
        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : purchasedLottos) {
            int matchCount = lotto.countMatch(winningNumbers);
            boolean matchBonus = lotto.hasBonus(bonusNumber);
            Rank rank = Rank.valueOf(matchCount, matchBonus);

            lottoResult.addRank(rank);
        }
        return lottoResult;
    }
}
