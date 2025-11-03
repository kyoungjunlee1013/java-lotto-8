package lotto.controller;

import lotto.validator.InputValidator;
import lotto.view.InputView;

import java.util.List;

import static lotto.validator.InputValidator.validateAndConvertAmount;

public class LottoGameController {
    private final InputView inputView;

    public LottoGameController(InputView inputView) {
        this.inputView = inputView;
        // 생성자에서 inputview를 초기화한 이유는 해당 인스턴스 변수는 객체가 생성될 때 무조건 한번 초기화되어야하기 때문이다.
        // 또한 public을 안 붙이면 application에서 해당 생성자에 접근할 수 없기 때문에 public을 붙여줘야 한다.
    }

    public void start(){
        int amount = readPurchaseAmountWithRetry();
        int lottoCount = calculateLottoCount(amount);

        List<Integer> winningNumbers = readWinningNumbersWithRetry();

        int bonusNumber = readBonusNumberWithRetry(winningNumbers);

        // TODO: 기능 2.1 (로또 발행)으로 연결
    }
    private int readPurchaseAmountWithRetry(){
        while(true){
            try{
                String input = inputView.readPurchaseAmount();
                return validateAndConvertAmount(input);
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()   );
            }
        }
    }
    private List<Integer> readWinningNumbersWithRetry(){
        while(true){
            try{
                String input = inputView.readWinningNumbers();
                return InputValidator.validateAndConvertWinningNumbers(input);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
    private int readBonusNumberWithRetry(List<Integer> winningNumbers){
        while(true) {
            try {
                String input = inputView.readBonusNumber();

                return InputValidator.validateAndConvertBonusNumber(input, winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
