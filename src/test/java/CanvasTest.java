import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.ys.Canvas;
import org.ys.Pixel;

public class CanvasTest {
    Canvas canvas;

    @BeforeEach()
    public void initCanvas(){
        canvas = new Canvas(2,3);
    }

    @Test
    public void testRenderCanvas() {
        String renderedCanvas = canvas.render();
        assertEquals("----\r\n|  |\r\n|  |\r\n|  |\r\n----\r\n", renderedCanvas);
    }

    @Test
    public void testDrawACanvasWithPixels() {
        Pixel[][] canvasPixels = canvas.getPixels();
        assertAll("Top margin pixels are -",
                  () -> assertEquals("-", canvasPixels[0][0].getPixelVal()),
                  () -> assertEquals("-", canvasPixels[1][0].getPixelVal()),
                  () -> assertEquals("-", canvasPixels[2][0].getPixelVal()),
                  () -> assertEquals("-\r\n", canvasPixels[3][0].getPixelVal())
        );

        assertAll("Bottom margin pixels are -",
                  () -> assertEquals("-", canvasPixels[0][4].getPixelVal()),
                  () -> assertEquals("-", canvasPixels[1][4].getPixelVal()),
                  () -> assertEquals("-", canvasPixels[2][4].getPixelVal()),
                  () -> assertEquals("-\r\n", canvasPixels[3][4].getPixelVal())
        );

        assertAll("Left margin pixels are |",
                  () -> assertEquals("|", canvasPixels[0][1].getPixelVal()),
                  () -> assertEquals("|", canvasPixels[0][2].getPixelVal()),
                  () -> assertEquals("|", canvasPixels[0][3].getPixelVal())
        );

        assertAll("Right margin pixels are |\r\n",
                  () -> assertEquals("|\r\n", canvasPixels[3][1].getPixelVal()),
                  () -> assertEquals("|\r\n", canvasPixels[3][2].getPixelVal()),
                  () -> assertEquals("|\r\n", canvasPixels[3][3].getPixelVal())
        );

        assertAll("Non-border pixels are \" \"",
                  () -> assertEquals(" ", canvasPixels[1][1].getPixelVal()),
                  () -> assertEquals(" ", canvasPixels[2][2].getPixelVal())
        );
    }

    @Test
    public void testUpdateCanvasPixel() {
        canvas.updateAPixel(2, 2, "@");
        Pixel[][] canvasPixels = canvas.getPixels();
        Pixel subjectPixel = canvas.getAPixel(2, 2);
        assertEquals("@", canvasPixels[2][2].getPixelVal());
        assertEquals("@", subjectPixel.getPixelVal());
    }
}
