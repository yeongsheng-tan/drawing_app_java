package org.ys;

import org.ys.Canvas;
import org.ys.Pixel;

public class BucketFill {
    int x, y;
    char c;

    public BucketFill(int x, int y, char c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public Canvas fillCanvas(Canvas canvas) {
        if (canvas == null)
            throw new IllegalArgumentException("Canvas must be created before bucket fill!");
        if (!((x > 0 && x < canvas.getWidth()) && (y > 0 && y < canvas.getHeight())))
            throw new IllegalArgumentException("Rectangle coordinates must be within bounds of canvas dimension!");

        Pixel[][] canvasPixels = canvas.getPixels();
        // for (int Y = y; Y <= canvas.getHeight(); Y++) {
        //     for (int X = x; X <= canvas.getWidth(); X++) {
        //         if (canvasPixels[X][Y].getPixel() != "X")
        //             canvas.setPixel(X, Y, c);
        //     }
        // }
        return canvas;
    }
}
