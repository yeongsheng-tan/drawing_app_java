import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testDrawingAppCreatedCanvasHasCorrectWidthAndHeight() {
        Canvas canvas = drawApp.createNewCanvas(20, 4);
        assertEquals(21, canvas.getWidth());
        assertEquals(5 , canvas.getHeight());
    }

    @Test
    public void testDrawingAppCanUpdateAPixelInCanvas() {
        Canvas canvas = drawApp.createNewCanvas(4, 20);
        canvas.updateAPixel(3, 3, "#");
        assertEquals("#", canvas.getAPixel(3, 3).getPixelVal());
        assertEquals(" ", canvas.getAPixel(1, 1).getPixelVal());
    }
}
