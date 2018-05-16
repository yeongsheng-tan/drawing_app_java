import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import org.ys.DrawingApp;
import org.ys.Canvas;

public class DrawingAppTest {
    DrawingApp drawApp;

    @BeforeEach
    public void initDrawingApp() {
        drawApp = new DrawingApp();
    }

    @Test
    public void testIsAllowedCommandForValidCommands() {
        String[] expectedAllowedCommands = {"C", "L", "R", "B", "Q"};

        assertTrue(Arrays.equals(expectedAllowedCommands, drawApp.getAllowedCommands()), () -> "allowedCommands are \"C\", \"L\", \"R\", \"B\" and \"Q\"");
    }

    @Test
    public void testIsAllowedCommandForInvalidCommands() {
        assertAll("Invalid commands returns false", () -> {
            assertFalse(drawApp.isAllowedCommand("Z"), () -> "\"Z\" is not an allowed command");
            assertFalse(drawApp.isAllowedCommand(""), () -> "\"\" is not an allowed command");
            assertFalse(drawApp.isAllowedCommand("-99"), () -> "\" \" is not an allowed command");
        });
    }

    @Test
    public void assertLessThan2CommandParamsToThrowIllegalArgumentException() {
        drawApp.createNewCanvas(3, 3);

        String[] oneParam = {"99"};
        assertThrows(IllegalArgumentException.class, () -> {
            drawApp.sanitiseCommandParams(oneParam);
        });
    }

    @Test
    public void assertMoreThan4CommandParamsToThrowIllegalArgumentException() {
        drawApp.createNewCanvas(3, 3);

        String[] fourParams = {"0", "100", "-1", "A", "++"};
        assertThrows(IllegalArgumentException.class, () -> {
            drawApp.sanitiseCommandParams(fourParams);
        });
    }

    @Test
    public void assert2NonNumericStringCommandParamsToThrowNumberFormatException() {
        drawApp.createNewCanvas(3, 3);

        String[] twoNonNumericStringParams = {"A", "++"};
        assertThrows(NumberFormatException.class, () -> {
            drawApp.sanitiseCommandParams(twoNonNumericStringParams);
        });
    }

    @Test
    public void assert4NonNumericStringCommandParamsToThrowNumberFormatException() {
        drawApp.createNewCanvas(3, 3);

        String[] fourNonNumericStringParams = {"A", "-1", " ", "Z9"};
        assertThrows(NumberFormatException.class, () -> {
            drawApp.sanitiseCommandParams(fourNonNumericStringParams);
        });
    }

    @Test
    public void assertFirst2NonNumericStringInA3ParamsCommandToThrowNumberFormatException() {
        drawApp.createNewCanvas(3, 3);

        String[] first2NonNumericStringIn3StringParams = {"K", "V", "C"};
        assertThrows(NumberFormatException.class, () -> {
            drawApp.sanitiseCommandParams(first2NonNumericStringIn3StringParams);
        });
    }

    @Test
    public void assertEmptyStringInA2Or3Or4ParamsCommandToThrowIllegalFormatException() {
        drawApp.createNewCanvas(1, 1);

        String[] emptyStringIn2StringParams = {" ", "5"};
        String[] emptyStringIn3StringParams = {"0", "0", "    "};
        String[] emptyStringIn4StringParams = {"1", "", "0", " "};
        assertAll("Invalid commands returns false", () -> {
            assertThrows(IllegalArgumentException.class, () -> {
                drawApp.sanitiseCommandParams(emptyStringIn2StringParams);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                drawApp.sanitiseCommandParams(emptyStringIn3StringParams);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                drawApp.sanitiseCommandParams(emptyStringIn4StringParams);
            });
        });
    }
}
