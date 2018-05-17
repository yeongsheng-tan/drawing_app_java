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
                  () -> assertEquals("-", canvasPixels[0][0].getPixel()),
                  () -> assertEquals("-", canvasPixels[1][0].getPixel()),
                  () -> assertEquals("-", canvasPixels[2][0].getPixel()),
                  () -> assertEquals("-\r\n", canvasPixels[3][0].getPixel())
        );

        assertAll("Bottom margin pixels are -",
                  () -> assertEquals("-", canvasPixels[0][4].getPixel()),
                  () -> assertEquals("-", canvasPixels[1][4].getPixel()),
                  () -> assertEquals("-", canvasPixels[2][4].getPixel()),
                  () -> assertEquals("-\r\n", canvasPixels[3][4].getPixel())
        );

        assertAll("Left margin pixels are |",
                  () -> assertEquals("|", canvasPixels[0][1].getPixel()),
                  () -> assertEquals("|", canvasPixels[0][2].getPixel()),
                  () -> assertEquals("|", canvasPixels[0][3].getPixel())
        );

        assertAll("Right margin pixels are |\r\n",
                  () -> assertEquals("|\r\n", canvasPixels[3][1].getPixel()),
                  () -> assertEquals("|\r\n", canvasPixels[3][2].getPixel()),
                  () -> assertEquals("|\r\n", canvasPixels[3][3].getPixel())
        );

        assertAll("Non-border pixels are \" \"",
                  () -> assertEquals(" ", canvasPixels[1][1].getPixel()),
                  () -> assertEquals(" ", canvasPixels[2][2].getPixel())
        );
    }

    @Test
    public void testUpdateCanvasPixel() {
        canvas.setPixel(2, 2, "w");
        Pixel[][] canvasPixels = canvas.getPixels();
        assertEquals("w", canvasPixels[2][2].getPixel());
    }
}
