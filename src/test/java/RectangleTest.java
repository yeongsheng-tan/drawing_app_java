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
    public void testDrawA1PixelRectangle() {
        Canvas canvas = drawApp.createNewCanvas(1,1);
        Rectangle r = new Rectangle(1, 1, 1, 1);
        canvas = r.paintCanvasPixels(canvas);
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
    public void testDrawA2PixelsRectangle() {
        Canvas canvas = drawApp.createNewCanvas(2,2);
        Rectangle r = new Rectangle(1, 1, 1, 2);
        canvas = r.paintCanvasPixels(canvas);
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
    public void testDrawA4PixelsSquare() {
        Canvas canvas = drawApp.createNewCanvas(2,2);
        Rectangle r = new Rectangle(1, 1, 2, 2);
        canvas = r.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        int numberOfXPixel = 0;
        for(int y = 0; y <= canvas.getHeight(); y++) {
            for(int x = 0; x <= canvas.getWidth(); x ++) {
                if("X" == canvasPixels[x][y].getPixelVal())
                    numberOfXPixel += 1;
            }
        }

        assertEquals(4, numberOfXPixel);
        assertAll("All line pixels are X",
                  () -> assertEquals("X", canvasPixels[1][1].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[2][1].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[1][2].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[2][2].getPixelVal())
        );
    }

    @Test
    public void testDrawARectangle() {
        Canvas canvas = drawApp.createNewCanvas(30,25);
        Rectangle r = new Rectangle(2, 5, 5, 10);
        canvas = r.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        assertAll("All line pixels are X",
                  () -> assertEquals("X", canvasPixels[2][5].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[3][5].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[4][5].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[5][5].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[2][10].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[3][10].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[4][10].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[5][10].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[2][6].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[2][7].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[2][8].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[2][9].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[5][6].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[5][7].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[5][8].getPixelVal()),
                  () -> assertEquals("X", canvasPixels[5][9].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[3][6].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[4][6].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[3][9].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[4][9].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[4][4].getPixelVal())
        );
    }
}
