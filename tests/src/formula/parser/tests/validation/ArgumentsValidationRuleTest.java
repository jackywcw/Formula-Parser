package formula.parser.tests.validation;

import formula.parser.FormulaParseException;
import formula.parser.token.FormulaTokenizer;
import formula.parser.validation.FormulaValidationException;
import formula.parser.validation.FormulaValidationRules;
import org.junit.Test;

public class ArgumentsValidationRuleTest {

    @Test
    public void shouldPassValidationWithOperation() throws FormulaParseException {
        checkValidation("3 * y");
    }

    @Test
    public void shouldPassValidationWithBrackets() throws FormulaParseException {
        checkValidation("3 * (y)");
    }

    @Test(expected = FormulaValidationException.class)
    public void shouldFailValidationThoughAbsentOperation() throws FormulaParseException {
        checkValidation("3y");
    }

    @Test(expected = FormulaValidationException.class)
    public void shouldFailValidationThoughBracketBetweenArguments() throws FormulaParseException {
        checkValidation("3(y");
    }

    @Test(expected = FormulaValidationException.class)
    public void shouldFailValidationThoughBracketWithoutNextArgument() throws FormulaParseException {
        checkValidation("3(");
    }

    private void checkValidation(String testString) throws FormulaParseException {
        FormulaTokenizer tokenizer = new FormulaTokenizer(testString);
        FormulaValidationRules.ARGUMENTS_RULE.validate(tokenizer);
    }
}
