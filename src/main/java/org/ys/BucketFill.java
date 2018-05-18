package org.ys;

import java.util.Set;
import java.util.HashSet;
import org.ys.Canvas;
import org.ys.Pixel;

public class BucketFill {
    private int x, y;
    private char c;

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
        Pixel seedPixel = canvasPixels[this.x][this.y];

        Set<Pixel> candidateFillPixels = new HashSet<Pixel>();
        Set<Pixel> previouslySearchedPixels = new HashSet<Pixel>();

        candidateFillPixels = seedPixel.neighbours(canvas.getWidth(), canvas.getHeight(), canvas.getPixels(), candidateFillPixels, previouslySearchedPixels);
        if(seedPixel.getPixelVal() != "X")
            candidateFillPixels.add(seedPixel);

        candidateFillPixels.stream().forEach(pixel -> canvas.updateAPixel(pixel.getX(), pixel.getY(), String.valueOf(this.c)));
        return canvas;
    }
}
