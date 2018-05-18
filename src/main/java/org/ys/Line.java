package org.ys;

import java.lang.Math;
import org.ys.Canvas;
import org.ys.LineTypeNotSupportedException;

public class Line {
    private int x1, y1, x2, y2;

    public Line(int x1, int y1, int x2, int y2) {
        if (x1 != x2 && y1 != y2)
            throw new LineTypeNotSupportedException("Error: Diagonal line not supported yet!");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Canvas paintCanvasPixels(Canvas canvas) {
        if (canvas == null)
            throw new IllegalArgumentException("Canvas must be created before drawing a line!");
        if (!((x1 > 0 && x1 < canvas.getWidth()) && (x2 > 0 && x2 < canvas.getWidth()) && (y1 > 0 && y1 < canvas.getHeight()) && (y2 > 0 && y2 < canvas.getHeight())))
            throw new IllegalArgumentException("Line coordinates must be within bounds of canvas dimension!");

        int xStart = Math.min(x1, x2);
        int yStart = Math.min(y1, y2);
        int xRange = xStart + Math.abs(x2 - x1);
        int yRange = yStart + Math.abs(y2 - y1);

        Pixel[][] canvasPixels = canvas.getPixels();
        for (int y=yStart; y <= yRange; y++) {
            for(int x=xStart; x <= xRange; x++) {
                canvasPixels[x][y].setPixelVal("X");
                // canvas.updateAPixel(x, y, "X");
            }
        }
        canvas.setPixels(canvasPixels);
        return canvas;
    }
}
