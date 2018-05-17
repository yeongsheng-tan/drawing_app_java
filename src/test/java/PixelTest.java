import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import org.ys.DrawingApp;
import org.ys.Canvas;
import org.ys.Pixel;
import org.ys.Rectangle;

public class PixelTest {
    DrawingApp drawApp;

    @BeforeEach
    public void initDrawingApp() {
        drawApp = new DrawingApp();
    }

    @Test
    public void testPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(10, 10);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[5][5];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[4][4], canvasPixels[5][4], canvasPixels[6][4], canvasPixels[4][5], canvasPixels[6][5], canvasPixels[4][6], canvasPixels[5][6], canvasPixels[6][6]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(expectedPixelNeighbours.size(), pixelNeighbours.size(),"There should be 8 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testNoPixelNeighboursIfPixelIsX() {
        Canvas canvas = drawApp.createNewCanvas(2, 2);
        canvas.setPixel(1, 1, "X");

        Pixel pixel = canvas.getPixel(1, 1);
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(0, pixelNeighbours.size(),"There should be no neighbours");
    }

    @Test
    public void testTopLeftCornerPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(2, 2);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[1][1];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[2][1], canvasPixels[2][2], canvasPixels[1][2]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(3, pixelNeighbours.size(),"There should be 3 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testTopRightCornerPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(2, 2);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[2][1];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[1][2], canvasPixels[2][2]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(3, pixelNeighbours.size(),"There should be 3 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testBottomLeftCornerPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(2, 2);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[1][2];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[2][1], canvasPixels[2][2]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(3, pixelNeighbours.size(),"There should be 3 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testBottomRightCornerPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(2, 2);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[2][2];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[2][1], canvasPixels[1][2]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(3, pixelNeighbours.size(),"There should be 3 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testTopCenterBorderPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[2][1];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[3][1], canvasPixels[1][2], canvasPixels[2][2], canvasPixels[3][2]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(5, pixelNeighbours.size(),"There should be 5 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testBottomCenterBorderPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[2][3];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][3], canvasPixels[1][2], canvasPixels[2][2], canvasPixels[3][2], canvasPixels[3][3]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(5, pixelNeighbours.size(),"There should be 5 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testLeftCenterBorderPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[1][2];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[2][1], canvasPixels[2][2], canvasPixels[2][3], canvasPixels[1][3]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(5, pixelNeighbours.size(),"There should be 5 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testRightCenterBorderPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[3][2];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[3][1], canvasPixels[2][1], canvasPixels[2][2], canvasPixels[2][3], canvasPixels[3][3]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(5, pixelNeighbours.size(),"There should be 5 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testPixelatedPixelAreNotNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Rectangle rectangle = new Rectangle(1, 1, 3, 3);
        canvas = rectangle.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[2][2];
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(0, pixelNeighbours.size(),"There should be 0 neighbours");
    }

    @Test
    public void testNeighboursAtOutsideBottomRightCornerOfRectangle() {
        Canvas canvas = drawApp.createNewCanvas(4, 4);
        Rectangle rectangle = new Rectangle(1, 1, 3, 3);
        canvas = rectangle.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[4][3], canvasPixels[3][4]));

        Pixel pixel = canvasPixels[4][4];
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(2, pixelNeighbours.size(),"There should be 0 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }
}
