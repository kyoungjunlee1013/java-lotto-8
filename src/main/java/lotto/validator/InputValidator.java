package lotto.validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    public static List<Integer> validateAndConvertWinningNumbers(String input){
        String[] parts = input.split(",");
        if(parts.length !=6 ){
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        List<Integer> numbers = new ArrayList<>();
        for(String part : parts){
            numbers.add(parseAndValidateRange(part.trim()));
        }
        if(numbers.size() != new HashSet<>(numbers).size()){ //hashset의 중복불가능성을 이용함
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
        return numbers;
    }

    private static int parseAndValidateRange(String numberString){
        try{
            int number = Integer.parseInt(numberString);
            if(number < 1 || number > 45){
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            return number;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 로또 번호는 숫자 형식이어야 합니다.");
        }
    }
    public static int validateAndConvertBonusNumber(String input, List<Integer> winningNumbers){
            int bonusNumber = parseAndValidateRange(input.trim());

            if(winningNumbers.contains(bonusNumber)){
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        return bonusNumber;
    }
}
