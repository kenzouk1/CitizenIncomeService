package service;

import com.kian.model.Frequency;
import com.kian.model.RegularAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class IncomeValidatorTest {
    private static final String INVALID_WEEKLY_AMOUNT_ERROR = "Invalid payment amount. The weekly amount in Pence is not a whole number";
    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private Validator validator;

    @BeforeEach
    void init() {
        this.validator = VALIDATOR_FACTORY.getValidator();
    }
    private static Stream<Arguments> parameters() {
        return Stream.of(
                // Test cases for invalid amount
                Arguments.of(Frequency.WEEK, null, true),
                Arguments.of(Frequency.TWO_WEEK, " ", true),
                Arguments.of(Frequency.FOUR_WEEK, "s200", true),
                // Test case for null frequency
                Arguments.of(null, "100", true),
                // Test cases for weekly frequency
                Arguments.of(Frequency.WEEK, "50", true),
                Arguments.of(Frequency.WEEK, "49.55", true),
                // Test cases for monthly frequency
                Arguments.of(Frequency.MONTH, "200", true),
                Arguments.of(Frequency.MONTH, "199.99", true),
                // Test cases for 2-weekly frequency
                Arguments.of(Frequency.TWO_WEEK, "100", true),
                Arguments.of(Frequency.TWO_WEEK, "99.80", true),
                Arguments.of(Frequency.TWO_WEEK, "99.81", false),
                // Test case for 4-weekly frequency
                Arguments.of(Frequency.FOUR_WEEK, "200", true),
                Arguments.of(Frequency.FOUR_WEEK, "200.40", true),
                Arguments.of(Frequency.FOUR_WEEK, "200.30", false),
                // Test case for quarterly frequency
                Arguments.of(Frequency.QUARTER, "520", true),
                Arguments.of(Frequency.QUARTER, "662.48", true),
                Arguments.of(Frequency.QUARTER, "664", false),
                // Test case for yearly frequency
                Arguments.of(Frequency.YEAR, "2860", true),
                Arguments.of(Frequency.YEAR, "2992.6", true),
                Arguments.of(Frequency.YEAR, "3000", false)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void testWeeklyAmountValidation(final Frequency frequency, final String amount, final boolean isValid) {
        final RegularAmount regularAmount = new RegularAmount(frequency, amount);
        Set<ConstraintViolation<RegularAmount>> violations = this.validator.validate(regularAmount);
        if (isValid) {
            assertThat(violations).isEmpty();
        } else {
            assertThat(violations).hasSize(1);
            assertThat(violations.iterator().next().getMessage()).isEqualTo(INVALID_WEEKLY_AMOUNT_ERROR);
        }
    }
}
