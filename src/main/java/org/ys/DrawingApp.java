package org.ys;

import org.ys.Canvas;
import org.ys.Line;
import org.ys.LineTypeNotSupportedException;
import org.ys.Rectangle;
import org.ys.Cli;

public class DrawingApp {
    private Canvas canvas;
    private Cli cli;

    public static void main(String[] args) {
        DrawingApp drawApp = new DrawingApp();
        Cli cli = new Cli(drawApp);

        // loop to receive CLI command and process for next outcome
        while(true) {
            cli.receiveCommand();
            if (drawApp.canvas != null)
                drawApp.canvas.render();
        }
    }

    public Canvas createNewCanvas(int w, int h) {
        canvas = null;
        return canvas = new Canvas(w, h);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void quit() {
        System.out.println("BYE!\n");
        System.exit(0);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        Line line = new Line(x1, y1, x2, y2);
        canvas = line.paintCanvasPixels(canvas);
    }

    public void drawRectangle(int x1, int y1, int x2, int y2) {
        Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
        canvas = rectangle.paintCanvasPixels(canvas);
    }

    public void bucketFill(int x, int y, char c) {
        BucketFill bucketFill = new BucketFill(x, y, c);
        canvas = bucketFill.fillCanvas(canvas);
    }
}
