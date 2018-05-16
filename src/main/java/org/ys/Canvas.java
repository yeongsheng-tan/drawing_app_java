package org.ys;

import java.util.List;
import java.util.ArrayList;
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

    public void setPixel(int x, int y, String c) {
        Pixel pixel = new Pixel(x, y, c);
        pixels[x][y] = pixel;
        // System.out.println("y: " + pixel.y + " | x: " + pixel.x + " | pixel: " + pixel.pixel );
    }

    public Canvas(int w, int h) {
        this.width = w + 1;
        this.height = h + 1;

        pixels = new Pixel[width + 1][height + 1];
        Pixel pixel;
        for(int y=0; y <= height; y++){
            for(int x=0; x <= width; x++){
                pixel = new Pixel(x, y, width, height);
                // System.out.println("y: " + pixel.y + " | x: " + pixel.x + " | pixel: " + pixel.pixel );
                pixels[x][y] = pixel;
            }
        }
    }

    public String render() {
        String output = "";
        for(int y=0; y <= height; y++) {
            for(int x=0; x <= width; x++) {
                output += pixels[x][y].getPixel();
            }
        }
        System.out.println(output);
        return output;
    }
}