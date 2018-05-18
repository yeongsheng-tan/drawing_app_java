
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.ys.DrawingApp;
import org.ys.BucketFill;
import org.ys.Canvas;
import org.ys.Rectangle;
import org.ys.Pixel;

public class BucketFillTest {
    DrawingApp drawApp;

    @BeforeEach
    public void initDrawingApp() {
        drawApp = new DrawingApp();
    }

    @Test
    public void testCanvasMustExistBeforeBucketFill() {
        assertThrows(IllegalArgumentException.class, () -> {
            BucketFill bf = new BucketFill(1, 1, '^');
            bf.fillCanvas(null);
        });
    }

    @Test
    public void testFillsCanvasWithSpecifiedCharacter() {
        Canvas c = drawApp.createNewCanvas(2, 2);
        BucketFill bf = new BucketFill(1, 2, '?');
        c = bf.fillCanvas(c);
        Pixel[][] canvasPixels = c.getPixels();

        assertAll("All empty pixels in canvas are filled with '?'",
                  () -> assertEquals("?", canvasPixels[1][1].getPixelVal()),
                  () -> assertEquals("?", canvasPixels[2][1].getPixelVal()),
                  () -> assertEquals("?", canvasPixels[1][2].getPixelVal()),
                  () -> assertEquals("?", canvasPixels[2][2].getPixelVal())
        );
    }

    @Test
    public void testFillsCorrectCanvasRegionWithSpecifiedCharacter() {
        Canvas c = drawApp.createNewCanvas(6, 5);
        Rectangle r = new Rectangle(2, 2, 5, 5);
        c = r.paintCanvasPixels(c);
        BucketFill bf = new BucketFill(3, 1, '*');
        c = bf.fillCanvas(c);
        Pixel[][] canvasPixels = c.getPixels();

        assertAll("All empty pixels in canvas outside rectangle are filled with '*'",
                  () -> assertEquals("*", canvasPixels[1][1].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[2][1].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[3][1].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[4][1].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[5][1].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[6][1].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[1][2].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[6][2].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[1][3].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[6][3].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[1][4].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[6][4].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[1][5].getPixelVal()),
                  () -> assertEquals("*", canvasPixels[6][5].getPixelVal())
         );

        assertAll("All empty pixels in canvas in rectangle remains empty",
                  () -> assertEquals(" ", canvasPixels[3][3].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[4][3].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[3][4].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[4][4].getPixelVal())
         );
    }
}
