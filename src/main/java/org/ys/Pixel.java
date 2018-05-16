package org.ys;

public class Pixel {
    private int x, y;
    private String pixel;

    public String getPixel() {
        return pixel;
    }

    public Pixel() {
        x = y = 0;
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
