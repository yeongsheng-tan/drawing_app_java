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
    public void testDrawA1PixelLine() {
        Canvas canvas = drawApp.createNewCanvas(1,1);
        Line l = new Line(1, 1, 1, 1);
        canvas = l.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        int numberOfXPixel = 0;
        for(int y = 0; y <= canvas.getHeight(); y++) {
            for(int x = 0; x <= canvas.getWidth(); x ++) {
                if("X" == canvasPixels[x][y].getPixelVal())
                    numberOfXPixel += 1;
            }
        }

        assertEquals(1, numberOfXPixel);
        assertEquals("X", canvasPixels[1][1].getPixelVal());
    }

    @Test
    public void testDrawA2PixelsHorizontalLine() {
        Canvas canvas = drawApp.createNewCanvas(3,3);
        Line l = new Line(1, 1, 2, 1);
        canvas = l.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        int numberOfXPixel = 0;
        for(int y = 0; y <= canvas.getHeight(); y++) {
            for(int x = 0; x <= canvas.getWidth(); x ++) {
                if("X" == canvasPixels[x][y].getPixelVal())
                    numberOfXPixel += 1;
            }
        }

        assertEquals(2, numberOfXPixel);
        assertAll("All line pixels are X",
                  () -> assertEquals("X", canvasPixels[1][1].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[2][1].getPixelVal())
        );
    }

    @Test
    public void testDrawA2PixelsVerticalLine() {
        Canvas canvas = drawApp.createNewCanvas(3,3);
        Line l = new Line(1, 1, 1, 2);
        canvas = l.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        int numberOfXPixel = 0;
        for(int y = 0; y <= canvas.getHeight(); y++) {
            for(int x = 0; x <= canvas.getWidth(); x ++) {
                if("X" == canvasPixels[x][y].getPixelVal())
                    numberOfXPixel += 1;
            }
        }

        assertEquals(2, numberOfXPixel);
        assertAll("All line pixels are X",
                  () -> assertEquals("X", canvasPixels[1][1].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[1][2].getPixelVal())
        );
    }

    @Test
    public void testDrawALine() {
        Canvas canvas = drawApp.createNewCanvas(5,5);
        Line l = new Line(3, 1, 3, 5);
        canvas = l.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        assertAll("All line pixels are X",
                  () -> assertEquals("X", canvasPixels[3][1].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[3][2].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[3][3].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[3][4].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[3][5].getPixelVal())
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
