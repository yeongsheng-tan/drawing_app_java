package org.ys;

import java.util.Set;
import java.util.HashSet;

public class Pixel {
    private int x, y;
    private String pixel;

    public String getPixel() {
        return pixel;
    }

    public Pixel() {
        this.x = this.y = 0;
        pixel = " ";
    }

    public Pixel(int x, int y, String pixel) {
        this.x = x;
        this.y = y;
        this.pixel = pixel;
    }

    public Pixel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.pixel = " ";

        // ((y == 0 || y == height_at_boundary) && x == width_at_boundary)  -> {:ok, coord} = Pixel.new(x, y, "-\n"); coord
        if (isRightEdgeAtTopOrBottom(x, y, width, height))
            this.pixel = "-\r\n";
        // (y == 0 || y == height_at_boundary)                              -> {:ok, coord} = Pixel.new(x, y, "-"); coord
        else if (isTopOrBottom(x, y, width, height))
            this.pixel = "-";
        // ((y != 0 || y != height_at_boundary) && x == width_at_boundary)  -> {:ok, coord} = Pixel.new(x, y, "|\n"); coord
        else if (isRightEdge(x, y, width, height))
            this.pixel = "|\r\n";
        // (x == 0 && y != 0 && y != height_at_boundary)                    -> {:ok, coord} = Pixel.new(x, y, "|"); coord
        else if (isLeftEdge(x, y, width, height))
            this.pixel = "|";
    }

    public Set<Pixel> neighbours(Canvas canvas) {
        Set<Pixel> myNeighbours = new HashSet();
        Pixel[][] canvasPixels = canvas.getPixels();

        if (this.pixel == "X")
            return myNeighbours;

        if (((this.x - 1) > 0) && ((this.x - 1) < canvas.getWidth()) && ((this.y - 1) > 0) && ((this.y - 1) < canvas.getHeight())) {
            if(canvasPixels[this.x - 1][this.y - 1].getPixel() != "X")
                myNeighbours.add(canvasPixels[this.x - 1][this.y - 1]);
        }

        if (((this.y - 1) > 0) && ((this.y - 1) < canvas.getHeight())) {
            if(canvasPixels[this.x][this.y - 1].getPixel() != "X")
                myNeighbours.add(canvasPixels[this.x][this.y - 1]);
        }

        if (((this.x + 1) < canvas.getWidth()) && ((this.y - 1) > 0) && ((this.y - 1) < canvas.getHeight())) {
            if(canvasPixels[this.x + 1][this.y - 1].getPixel() != "X")
                myNeighbours.add(canvasPixels[this.x + 1][this.y - 1]);
        }

        if (((this.x - 1) > 0) && ((this.x - 1) < canvas.getWidth())) {
            if(canvasPixels[this.x - 1][this.y].getPixel() != "X")
                myNeighbours.add(canvasPixels[this.x - 1][this.y]);
        }

        if ((this.x + 1) < canvas.getWidth()) {
            if(canvasPixels[this.x + 1][this.y].getPixel() != "X")
                myNeighbours.add(canvasPixels[this.x + 1][this.y]);
        }

        if (((this.x - 1) > 0) && ((this.x - 1) < canvas.getWidth()) && ((this.y + 1) < canvas.getHeight())) {
            if(canvasPixels[this.x - 1][this.y + 1].getPixel() != "X")
                myNeighbours.add(canvasPixels[this.x - 1][this.y + 1]);
        }

        if ((this.y + 1) < canvas.getHeight()) {
            if(canvasPixels[this.x][this.y + 1].getPixel() != "X")
                myNeighbours.add(canvasPixels[this.x][this.y + 1]);
        }

        if (((this.x + 1) < canvas.getWidth()) && ((this.y + 1) < canvas.getHeight())) {
            if(canvasPixels[this.x + 1][this.y + 1].getPixel() != "X")
                myNeighbours.add(canvasPixels[this.x + 1][this.y + 1]);
        }
        return myNeighbours;
    }

    boolean isRightEdgeAtTopOrBottom(int x, int y, int width, int height) {
        return (x == width && (y == 0 || y == height));
    }

    boolean isTopOrBottom(int x, int y, int width, int height) {
        return (x != width && (y == 0 || y == height));
    }

    boolean isRightEdge(int x, int y, int width, int height) {
        return (x == width && (y != 0 || y != height));
    }

    boolean isLeftEdge(int x, int y, int width, int height) {
        return (x == 0 && y != 0 && y != height);
    }
}
