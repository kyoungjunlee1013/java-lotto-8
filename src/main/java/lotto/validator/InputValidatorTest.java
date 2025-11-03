package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class InputValidatorTest {

    // 테스트에 사용할 유효한 당첨 번호 (보너스 중복 검사에 사용)
    private static final List<Integer> VALID_WINNING_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

    // ===================================================================
    // 1.1 로또 구입 금액 유효성 검사 테스트
    // ===================================================================

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    void validateAmountByNonThousandUnit() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertAmount("1450"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    void validateAmountByNonNumeric() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertAmount("8000a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("구입 금액이 0이거나 음수이면 예외가 발생한다.")
    void validateAmountByZeroOrNegative() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertAmount("-1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");

        assertThatThrownBy(() -> InputValidator.validateAndConvertAmount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("유효한 구입 금액은 정상적으로 변환된다.")
    void validateAmountByValidInput() {
        int amount = InputValidator.validateAndConvertAmount("8000");
        assertThat(amount).isEqualTo(8000);
    }

    // ===================================================================
    // 1.3 당첨 번호 유효성 검사 테스트
    // ===================================================================

    @Test
    @DisplayName("당첨 번호가 쉼표로 분리되어 6개가 아니면 예외가 발생한다.")
    void validateWinningNumbersByInvalidCount() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertWinningNumbers("1,2,3,4,5,6,7")) // 7개
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("당첨 번호에 중복이 있으면 예외가 발생한다.")
    void validateWinningNumbersByDuplication() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertWinningNumbers("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("당첨 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    void validateWinningNumbersByOutOfRange() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertWinningNumbers("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");

        assertThatThrownBy(() -> InputValidator.validateAndConvertWinningNumbers("0,1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("당첨 번호가 숫자가 아닌 문자를 포함하면 예외가 발생한다.")
    void validateWinningNumbersByNonNumeric() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertWinningNumbers("1,2,3,4,5,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("유효한 당첨 번호는 정상적으로 리스트로 변환된다.")
    void validateWinningNumbersByValidInput() {
        List<Integer> numbers = InputValidator.validateAndConvertWinningNumbers("1,2,3,4,5,6");
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    // ===================================================================
    // 1.4 보너스 번호 유효성 검사 테스트
    // ===================================================================

    @Test
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    void validateBonusNumberByOutOfRange() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertBonusNumber("46", VALID_WINNING_NUMBERS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void validateBonusNumberByDuplication() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertBonusNumber("1", VALID_WINNING_NUMBERS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
    void validateBonusNumberByNonNumeric() {
        assertThatThrownBy(() -> InputValidator.validateAndConvertBonusNumber("a", VALID_WINNING_NUMBERS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("유효한 보너스 번호는 정상적으로 변환된다.")
    void validateBonusNumberByValidInput() {
        int bonus = InputValidator.validateAndConvertBonusNumber("7", VALID_WINNING_NUMBERS);
        assertThat(bonus).isEqualTo(7);
    }
}