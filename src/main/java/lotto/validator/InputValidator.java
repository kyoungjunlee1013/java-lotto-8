package lotto.validator;

public class InputValidator {
    public static final int LOTTO_PRICE = 1000;

    public static int validateAndConvertAmount(String input){
        try{
            int amount = Integer.parseInt(input);

            if(amount <= 0 || amount%LOTTO_PRICE != 0){
                throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위의 양수여야 합니다.");
            }
            return amount;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자 형식이어야 합니다.");
        }
    }
}
