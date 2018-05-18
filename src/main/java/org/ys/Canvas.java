package org.ys;

import org.ys.Pixel;

public class Canvas {
    private int width, height;
    private Pixel[][] pixels;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public void setPixels(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    public Pixel getAPixel(int x, int y) {
        return this.pixels[x][y];
    }

    public void updateAPixel(int x, int y, String c) {
        this.pixels[x][y].setPixelVal(c);
    }

    public Canvas(int w, int h) {
        this.width = w + 1;
        this.height = h + 1;

        this.pixels = new Pixel[width + 1][height + 1];
        Pixel pixel;
        for(int y=0; y <= height; y++){
            for(int x=0; x <= width; x++){
                pixel = new Pixel(x, y, width, height);
                this.pixels[x][y] = pixel;
            }
        }
    }

    public String render() {
        String output = "";
        for(int y=0; y <= height; y++) {
            for(int x=0; x <= width; x++) {
                output += this.pixels[x][y].getPixelVal();
            }
        }
        System.out.println(output);
        return output;
    }
}
