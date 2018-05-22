package org.ys;

import org.ys.Canvas;

public class Rectangle {
    private int x1, y1, x2, y2;
    private Line top, bottom, left, right;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.top = new Line(x1, y1, x2, y1);
        this.bottom = new Line(x1, y2, x2, y2);
        this.left = new Line(x1, y1, x1, y2);
        this.right = new Line(x2, y1, x2, y2);
    }

    public Canvas paintCanvasPixels(Canvas canvas) {
        if (canvas == null)
            throw new IllegalArgumentException("Canvas must be created before drawing a rectangle!");
        if (!((x1 > 0 && x1 < canvas.getWidth()) && (x2 > 0 && x2 < canvas.getWidth()) && (y1 > 0 && y1 < canvas.getHeight()) && (y2 > 0 && y2 < canvas.getHeight())))
            throw new IllegalArgumentException("Rectangle coordinates must be within bounds of canvas dimension!");

        canvas = top.paintCanvasPixels(canvas);
        canvas = bottom.paintCanvasPixels(canvas);
        canvas = left.paintCanvasPixels(canvas);
        canvas = right.paintCanvasPixels(canvas);

        return canvas;
    }
}
