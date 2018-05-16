import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.ys.DrawingApp;
import org.ys.Canvas;
import org.ys.Pixel;
import org.ys.Rectangle;

public class RectangleTest {
    DrawingApp drawApp;

    @BeforeEach
    public void initDrawingApp() {
        drawApp = new DrawingApp();
    }

    @Test
    public void testCanvasMustExistBeforeRectangleDraw() {
        assertThrows(IllegalArgumentException.class, () -> {
            Rectangle rect = new Rectangle(1,1,3,5);
            rect.paintCanvasPixels(null);
        });
    }

    @Test
    public void testDrawARectangle() {
        Canvas canvas = drawApp.createNewCanvas(30,25);
        Rectangle r = new Rectangle(2, 5, 5, 10);
        canvas = r.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        assertAll("All line pixels are X",
                  () -> assertEquals("X", canvasPixels[2][5].getPixel()),
                  () -> assertEquals("X", canvasPixels[3][5].getPixel()),
                  () -> assertEquals("X", canvasPixels[4][5].getPixel()),
                  () -> assertEquals("X", canvasPixels[5][5].getPixel()),
                  () -> assertEquals("X", canvasPixels[2][10].getPixel()),
                  () -> assertEquals("X", canvasPixels[3][10].getPixel()),
                  () -> assertEquals("X", canvasPixels[4][10].getPixel()),
                  () -> assertEquals("X", canvasPixels[5][10].getPixel()),
                  () -> assertEquals("X", canvasPixels[2][6].getPixel()),
                  () -> assertEquals("X", canvasPixels[2][7].getPixel()),
                  () -> assertEquals("X", canvasPixels[2][8].getPixel()),
                  () -> assertEquals("X", canvasPixels[2][9].getPixel()),
                  () -> assertEquals("X", canvasPixels[5][6].getPixel()),
                  () -> assertEquals("X", canvasPixels[5][7].getPixel()),
                  () -> assertEquals("X", canvasPixels[5][8].getPixel()),
                  () -> assertEquals("X", canvasPixels[5][9].getPixel()),
                  () -> assertEquals(" ", canvasPixels[3][6].getPixel()),
                  () -> assertEquals(" ", canvasPixels[4][6].getPixel()),
                  () -> assertEquals(" ", canvasPixels[3][9].getPixel()),
                  () -> assertEquals(" ", canvasPixels[4][9].getPixel()),
                  () -> assertEquals(" ", canvasPixels[4][4].getPixel())
        );
    }
}
