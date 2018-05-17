import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.ys.DrawingApp;
import org.ys.Canvas;
import org.ys.Pixel;
import org.ys.Line;
import org.ys.LineTypeNotSupportedException;

public class LineTest {
    DrawingApp drawApp;

    @BeforeEach
    public void initDrawingApp() {
        drawApp = new DrawingApp();
    }

    @Test
    public void testCanvasMustExistBeforeLineDraw() {
        assertThrows(IllegalArgumentException.class, () -> {
            Line line = new Line(1,1,1,9);
            line.paintCanvasPixels(null);
        });
    }

    @Test
    public void testDrawALine() {
        Canvas canvas = drawApp.createNewCanvas(5,5);
        Line l = new Line(3, 1, 3, 5);
        canvas = l.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        assertAll("All line pixels are X",
                  () -> assertEquals("X", canvasPixels[3][1].getPixel()),
                  () -> assertEquals("X", canvasPixels[3][2].getPixel()),
                  () -> assertEquals("X", canvasPixels[3][3].getPixel()),
                  () -> assertEquals("X", canvasPixels[3][4].getPixel()),
                  () -> assertEquals("X", canvasPixels[3][5].getPixel())
        );
    }

    @Test
    public void testCannotCreateDiagonalLine() {
        assertThrows(LineTypeNotSupportedException.class, () -> {
            Line line = new Line(1,1,2,2);
        });
    }

    @Test
    public void testLineCoordinatesMustBeWithinCanvasDimension() {
        assertThrows(IllegalArgumentException.class, () -> {
            Canvas canvas = drawApp.createNewCanvas(2,2);
            Line line = new Line(1,2,1,3);
            canvas = line.paintCanvasPixels(canvas);
        });
    }
}
