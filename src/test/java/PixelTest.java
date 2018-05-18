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
import org.ys.Line;
import org.ys.Rectangle;

public class PixelTest {
    DrawingApp drawApp;
    Set<Pixel> candidateFillPixels;
    Set<Pixel> prevSearchedPixels;

    @BeforeEach
    public void initDrawingApp() {
        drawApp = new DrawingApp();
        candidateFillPixels = new HashSet<Pixel>();
        prevSearchedPixels = new HashSet<Pixel>();
    }

    @Test
    public void testPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(10, 10);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[5][5];
        // Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[4][4], canvasPixels[5][4], canvasPixels[6][4], canvasPixels[4][5], canvasPixels[6][5], canvasPixels[4][6], canvasPixels[5][6], canvasPixels[6][6]));
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[2][1], canvasPixels[3][1], canvasPixels[4][1], canvasPixels[5][1],
                                                                         canvasPixels[6][1], canvasPixels[7][1], canvasPixels[8][1], canvasPixels[9][1], canvasPixels[10][1],
                                                                         canvasPixels[1][2], canvasPixels[2][2], canvasPixels[3][2], canvasPixels[4][2], canvasPixels[5][2],
                                                                         canvasPixels[6][2], canvasPixels[7][2], canvasPixels[8][2], canvasPixels[9][2], canvasPixels[10][2],
                                                                         canvasPixels[1][3], canvasPixels[2][3], canvasPixels[3][3], canvasPixels[4][3], canvasPixels[5][3],
                                                                         canvasPixels[6][3], canvasPixels[7][3], canvasPixels[8][3], canvasPixels[9][3], canvasPixels[10][3],
                                                                         canvasPixels[1][4], canvasPixels[2][4], canvasPixels[3][4], canvasPixels[4][4], canvasPixels[5][4],
                                                                         canvasPixels[6][4], canvasPixels[7][4], canvasPixels[8][4], canvasPixels[9][4], canvasPixels[10][4],
                                                                         canvasPixels[1][5], canvasPixels[2][5], canvasPixels[3][5], canvasPixels[4][5],
                                                                         canvasPixels[6][5], canvasPixels[7][5], canvasPixels[8][5], canvasPixels[9][5], canvasPixels[10][5],
                                                                         canvasPixels[1][6], canvasPixels[2][6], canvasPixels[3][6], canvasPixels[4][6], canvasPixels[5][6],
                                                                         canvasPixels[6][6], canvasPixels[7][6], canvasPixels[8][6], canvasPixels[9][6], canvasPixels[10][6],
                                                                         canvasPixels[1][7], canvasPixels[2][7], canvasPixels[3][7], canvasPixels[4][7], canvasPixels[5][7],
                                                                         canvasPixels[6][7], canvasPixels[7][7], canvasPixels[8][7], canvasPixels[9][7], canvasPixels[10][7],
                                                                         canvasPixels[1][8], canvasPixels[2][8], canvasPixels[3][8], canvasPixels[4][8], canvasPixels[5][8],
                                                                         canvasPixels[6][8], canvasPixels[7][8], canvasPixels[8][8], canvasPixels[9][8], canvasPixels[10][8],
                                                                         canvasPixels[1][9], canvasPixels[2][9], canvasPixels[3][9], canvasPixels[4][9], canvasPixels[5][9],
                                                                         canvasPixels[6][9], canvasPixels[7][9], canvasPixels[8][9], canvasPixels[9][9], canvasPixels[10][9],
                                                                         canvasPixels[1][10], canvasPixels[2][10], canvasPixels[3][10], canvasPixels[4][10], canvasPixels[5][10],
                                                                         canvasPixels[6][10], canvasPixels[7][10], canvasPixels[8][10], canvasPixels[9][10], canvasPixels[10][10]
                                                                         )
                                                           );

        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(expectedPixelNeighbours.size(), pixelNeighbours.size(),"There should be 8 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testNoPixelNeighboursIfPixelIsX() {
        Canvas canvas = drawApp.createNewCanvas(2, 2);

        canvas.updateAPixel(1, 1, "X");
        Pixel pixel = canvas.getPixels()[1][1];
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertEquals("X", pixel.getPixelVal(), "Subject pixel value should be X");
        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(0, pixelNeighbours.size(),"There should be no neighbours");
    }

    @Test
    public void testTopLeftCornerPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(2, 2);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[1][1];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[2][1], canvasPixels[2][2], canvasPixels[1][2]));
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

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
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

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
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

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
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(3, pixelNeighbours.size(),"There should be 3 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testTopCenterBorderPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[2][1];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[3][1],
                                                                         canvasPixels[1][2], canvasPixels[2][2], canvasPixels[3][2],
                                                                         canvasPixels[1][3], canvasPixels[2][3], canvasPixels[3][3]
                                                                         )
                                                           );
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(8, pixelNeighbours.size(),"There should be 8 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testBottomCenterBorderPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[2][3];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][3], canvasPixels[1][2], canvasPixels[1][1],
                                                                         canvasPixels[2][2], canvasPixels[2][1],
                                                                         canvasPixels[3][3], canvasPixels[3][2], canvasPixels[3][1]
                                                                         )
                                                           );
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(8, pixelNeighbours.size(),"There should be 8 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testLeftCenterBorderPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[1][2];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[2][1], canvasPixels[3][1],
                                                                         canvasPixels[2][2], canvasPixels[3][2],
                                                                         canvasPixels[1][3], canvasPixels[2][3], canvasPixels[3][3]
                                                                         )
                                                           );
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(8, pixelNeighbours.size(),"There should be 8 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testRightCenterBorderPixelNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[3][2];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[3][1], canvasPixels[2][1], canvasPixels[1][1],
                                                                         canvasPixels[2][2], canvasPixels[1][2],
                                                                         canvasPixels[3][3], canvasPixels[2][3], canvasPixels[1][3]
                                                                         )
                                                           );
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(8, pixelNeighbours.size(),"There should be 8 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testPixelatedPixelAreNotNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(3, 3);
        Rectangle rectangle = new Rectangle(1, 1, 3, 3);
        canvas = rectangle.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();

        Pixel pixel = canvasPixels[2][2];
        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(0, pixelNeighbours.size(),"There should be 0 neighbours");
    }

    @Test
    public void testNeighboursAtOutsideBottomRightCornerOfRectangle() {
        Canvas canvas = drawApp.createNewCanvas(4, 4);
        Rectangle rectangle = new Rectangle(1, 1, 3, 3);
        canvas = rectangle.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        Pixel pixel = canvasPixels[4][4];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[4][1], canvasPixels[4][2], canvasPixels[4][3],
                                                                         canvasPixels[3][4], canvasPixels[2][4], canvasPixels[1][4]
                                                                         )
                                                           );

        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(6, pixelNeighbours.size(),"There should be 6 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testOnlyPixelsInsideRectangleAreNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(6, 6);
        Rectangle rectangle = new Rectangle(1, 1, 5, 5);
        canvas = rectangle.paintCanvasPixels(canvas);
        Pixel[][] canvasPixels = canvas.getPixels();
        Pixel pixel = canvasPixels[3][3];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[2][2], canvasPixels[3][2], canvasPixels[4][2],
                                                                         canvasPixels[2][3], canvasPixels[4][3],
                                                                         canvasPixels[2][4], canvasPixels[3][4], canvasPixels[4][4]
                                                                         )
                                                           );

        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(8, pixelNeighbours.size(),"There should be 8 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testOnlyPixelsInsideRectangleAndLineCrossSectionAreNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(7, 6);
        Rectangle rectangle = new Rectangle(2, 2, 6, 6);
        canvas = rectangle.paintCanvasPixels(canvas);

        Line line = new Line(1, 4, 7 , 4);
        canvas = line.paintCanvasPixels(canvas);

        Pixel[][] canvasPixels = canvas.getPixels();
        Pixel pixel = canvasPixels[3][3];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[4][3], canvasPixels[5][3]));

        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(2, pixelNeighbours.size(),"There should be 2 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }

    @Test
    public void testPixelsOutsideRectangleAndLineCrossSectionAreNeighbours() {
        Canvas canvas = drawApp.createNewCanvas(7, 6);
        Rectangle rectangle = new Rectangle(2, 2, 6, 6);
        canvas = rectangle.paintCanvasPixels(canvas);

        Line line = new Line(1, 4, 7 , 4);
        canvas = line.paintCanvasPixels(canvas);

        Pixel[][] canvasPixels = canvas.getPixels();
        Pixel pixel = canvasPixels[7][2];
        Set<Pixel> expectedPixelNeighbours = new HashSet<>(Arrays.asList(canvasPixels[1][1], canvasPixels[2][1], canvasPixels[3][1], canvasPixels[4][1], canvasPixels[5][1], canvasPixels[6][1], canvasPixels[7][1],
                                                                         canvasPixels[1][2],
                                                                         canvasPixels[1][3], canvasPixels[7][3]
                                                                         )
                                                           );

        Set<Pixel> pixelNeighbours =  pixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, prevSearchedPixels);

        assertFalse(pixelNeighbours.contains(pixel),"Subject pixel is not in the list of neighbours");
        assertEquals(10, pixelNeighbours.size(),"There should be 10 neighbours");
        assertEquals(expectedPixelNeighbours, pixelNeighbours, "Pixel knows its neighbours");
    }
}
