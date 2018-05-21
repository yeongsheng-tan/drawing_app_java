
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyChar;

import java.util.Arrays;
import org.ys.DrawingApp;
import org.ys.Cli;
import org.ys.Canvas;

@ExtendWith(MockitoExtension.class)
public class CliTest {
    Cli cli;

    @BeforeEach
    public void initCli() {
        cli = new Cli(new DrawingApp());
    }

    @Test
    public void testIsAllowedCommandForValidCommands() {
        String[] expectedAllowedCommands = {"C", "L", "R", "B", "Q"};

        assertTrue(Arrays.equals(expectedAllowedCommands, Cli.allowedCommands), () -> "allowedCommands are \"C\", \"L\", \"R\", \"B\" and \"Q\"");
    }

    @Test
    public void testIsAllowedCommandForInvalidCommands() {
        assertAll("Invalid commands returns false", () -> {
            assertFalse(cli.isAllowedCommand("Z"), () -> "\"Z\" is not an allowed command");
            assertFalse(cli.isAllowedCommand(""), () -> "\"\" is not an allowed command");
            assertFalse(cli.isAllowedCommand("-99"), () -> "\" \" is not an allowed command");
        });
    }

    @Test
    public void testC_CommandExpects2Arguments() {
        assertThrows(IllegalArgumentException.class, () -> {
           cli.processCommand("C 1");
        });
    }

    @Test
    public void testL_CommandExpects4Arguments() {
        assertThrows(IllegalArgumentException.class, () -> {
           cli.processCommand("L 1 2 3 4 5");
        });
    }

    @Test
    public void testR_CommandExpects4Arguments() {
        assertThrows(IllegalArgumentException.class, () -> {
           cli.processCommand("R 1 2 3");
        });
    }

    @Test
    public void testB_CommandExpects3Arguments() {
        assertThrows(IllegalArgumentException.class, () -> {
            cli.processCommand("B 1 2 z 4");
        });
    }

    @Test
    public void testQ_CommandInvokedDrawingAppQuit() {
        DrawingApp mockDrawApp = mock(DrawingApp.class);
        Cli subjectCli = new Cli(mockDrawApp);
        subjectCli.processCommand("Q");
        verify(mockDrawApp, times(1)).quit();
    }

    @Test
    public void testC_CommandInvokedDrawingAppCreateNewCanvasWith2IntParams() {
        DrawingApp mockDrawApp = mock(DrawingApp.class);
        Cli subjectCli = new Cli(mockDrawApp);
        subjectCli.processCommand("C 30 30");
        verify(mockDrawApp, times(1)).createNewCanvas(anyInt(), anyInt());
    }

    @Test
    public void testL_CommandInvokedDrawingAppDrawLineWith4IntParams() {
        DrawingApp mockDrawApp = mock(DrawingApp.class);
        Cli subjectCli = new Cli(mockDrawApp);
        subjectCli.processCommand("L 1 1 1 1");
        verify(mockDrawApp, times(1)).drawLine(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void testR_CommandInvokedDrawingAppDrawRectangleWith4IntParams() {
        DrawingApp mockDrawApp = mock(DrawingApp.class);
        Cli subjectCli = new Cli(mockDrawApp);
        subjectCli.processCommand("R 1 1 5 5");
        verify(mockDrawApp, times(1)).drawRectangle(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void testB_CommandInvokedDrawingAppBucketFillWith2IntParamsAnd1CharParam() {
        DrawingApp mockDrawApp = mock(DrawingApp.class);
        Cli subjectCli = new Cli(mockDrawApp);
        subjectCli.processCommand("B 2 3 *");
        verify(mockDrawApp, times(1)).bucketFill(anyInt(), anyInt(), anyChar());
    }

    @Test
    public void assertLessThan2CommandParamsToThrowIllegalArgumentException() {
        String[] oneParam = {"99"};
        assertThrows(IllegalArgumentException.class, () -> {
            cli.sanitiseCommandParams(oneParam);
        });
    }

    @Test
    public void assertMoreThan4CommandParamsToThrowIllegalArgumentException() {
        String[] fourParams = {"0", "100", "-1", "A", "++"};
        assertThrows(IllegalArgumentException.class, () -> {
            cli.sanitiseCommandParams(fourParams);
        });
    }

    @Test
    public void assert2NonNumericStringCommandParamsToThrowNumberFormatException() {
        String[] twoNonNumericStringParams = {"A", "++"};
        assertThrows(NumberFormatException.class, () -> {
            cli.sanitiseCommandParams(twoNonNumericStringParams);
        });
    }

    @Test
    public void assert4NonNumericStringCommandParamsToThrowNumberFormatException() {
        String[] fourNonNumericStringParams = {"A", "-1", " ", "Z9"};
        assertThrows(NumberFormatException.class, () -> {
            cli.sanitiseCommandParams(fourNonNumericStringParams);
        });
    }

    @Test
    public void assertFirst2NonNumericStringInA3ParamsCommandToThrowNumberFormatException() {
        String[] first2NonNumericStringIn3StringParams = {"K", "V", "C"};
        assertThrows(NumberFormatException.class, () -> {
            cli.sanitiseCommandParams(first2NonNumericStringIn3StringParams);
        });
    }

    @Test
    public void assertEmptyStringInA2ParamsCommandToThrowIllegalFormatException() {
        String[] emptyStringIn2StringParams = {" ", "5"};
        assertThrows(IllegalArgumentException.class, () -> {
            cli.sanitiseCommandParams(emptyStringIn2StringParams);
        });
    }

    @Test
    public void assertEmptyStringInA3ParamsCommandToThrowIllegalFormatException() {
        String[] emptyStringIn3StringParams = {"0", "0", "    "};
        assertThrows(IllegalArgumentException.class, () -> {
            cli.sanitiseCommandParams(emptyStringIn3StringParams);
        });
    }

    @Test
    public void assertEmptyStringInA4ParamsCommandToThrowIllegalFormatException() {
        String[] emptyStringIn4StringParams = {"1", "", "0", " "};
        assertThrows(IllegalArgumentException.class, () -> {
            cli.sanitiseCommandParams(emptyStringIn4StringParams);
        });
    }
}
