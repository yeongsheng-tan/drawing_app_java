package org.ys;

import java.util.Set;
import java.util.HashSet;

public class Pixel {
    private int x, y;
    private String pixelVal;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getPixelVal() {
        return pixelVal;
    }

    public void setPixelVal(String pixelVal) {
        this.pixelVal = pixelVal;
    }

    public Pixel() {
        this.x = this.y = 0;
        this.pixelVal = " ";
    }

    public Pixel(int x, int y, String pixelVal) {
        this.x = x;
        this.y = y;
        this.pixelVal = pixelVal;
    }

    public Pixel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.pixelVal = " ";

        if (isRightEdgeAtTopOrBottom(x, y, width, height))
            this.pixelVal = "-\r\n";
        else if (isTopOrBottom(x, y, width, height))
            this.pixelVal = "-";
        else if (isRightEdge(x, y, width, height))
            this.pixelVal = "|\r\n";
        else if (isLeftEdge(x, y, width, height))
            this.pixelVal = "|";
    }

    public Set<Pixel> neighbours(int canvasWidth, int canvasHeight, Pixel[][] canvasPixels, Set<Pixel> candidatePixels, Set<Pixel> searchedPixels) {
        searchedPixels.add(this);

        if (this.pixelVal == "X")
            return candidatePixels;

        if (((this.x - 1) > 0) && ((this.x - 1) < canvasWidth) && ((this.y - 1) > 0) && ((this.y - 1) < canvasHeight)) {
            if((canvasPixels[this.x - 1][this.y - 1].getPixelVal() != "X") && !searchedPixels.contains(canvasPixels[this.x - 1][this.y - 1])) {
                candidatePixels.add(canvasPixels[this.x - 1][this.y - 1]);
                candidatePixels.addAll(canvasPixels[this.x - 1][this.y - 1].neighbours(canvasWidth, canvasHeight, canvasPixels, candidatePixels, searchedPixels));
            }
        }

        if (((this.y - 1) > 0) && ((this.y - 1) < canvasHeight)) {
            if((canvasPixels[this.x][this.y - 1].getPixelVal() != "X") && !searchedPixels.contains(canvasPixels[this.x][this.y - 1])) {
                candidatePixels.add(canvasPixels[this.x][this.y - 1]);
                candidatePixels.addAll(canvasPixels[this.x][this.y - 1].neighbours(canvasWidth, canvasHeight, canvasPixels, candidatePixels, searchedPixels));
            }
        }

        if (((this.x + 1) < canvasWidth) && ((this.y - 1) > 0) && ((this.y - 1) < canvasHeight)) {
            if((canvasPixels[this.x + 1][this.y - 1].getPixelVal() != "X") && !searchedPixels.contains(canvasPixels[this.x + 1][this.y - 1])) {
                candidatePixels.add(canvasPixels[this.x + 1][this.y - 1]);
                candidatePixels.addAll(canvasPixels[this.x + 1][this.y - 1].neighbours(canvasWidth, canvasHeight, canvasPixels, candidatePixels, searchedPixels));
            }
        }

        if (((this.x - 1) > 0) && ((this.x - 1) < canvasWidth)) {
            if((canvasPixels[this.x - 1][this.y].getPixelVal() != "X") && !searchedPixels.contains(canvasPixels[this.x - 1][this.y])) {
                candidatePixels.add(canvasPixels[this.x - 1][this.y]);
                candidatePixels.addAll(canvasPixels[this.x - 1][this.y].neighbours(canvasWidth, canvasHeight, canvasPixels, candidatePixels, searchedPixels));
            }
        }

        if ((this.x + 1) < canvasWidth) {
            if((canvasPixels[this.x + 1][this.y].getPixelVal() != "X") && !searchedPixels.contains(canvasPixels[this.x + 1][this.y])) {
                candidatePixels.add(canvasPixels[this.x + 1][this.y]);
                candidatePixels.addAll(canvasPixels[this.x + 1][this.y].neighbours(canvasWidth, canvasHeight, canvasPixels, candidatePixels, searchedPixels));
            }
        }

        if (((this.x - 1) > 0) && ((this.x - 1) < canvasWidth) && ((this.y + 1) < canvasHeight)) {
            if((canvasPixels[this.x - 1][this.y + 1].getPixelVal() != "X") && !searchedPixels.contains(canvasPixels[this.x - 1][this.y + 1])) {
                candidatePixels.add(canvasPixels[this.x - 1][this.y + 1]);
                candidatePixels.addAll(canvasPixels[this.x - 1][this.y + 1].neighbours(canvasWidth, canvasHeight, canvasPixels, candidatePixels, searchedPixels));
            }
        }

        if ((this.y + 1) < canvasHeight) {
            if((canvasPixels[this.x][this.y + 1].getPixelVal() != "X") && !searchedPixels.contains(canvasPixels[this.x][this.y + 1])) {
                candidatePixels.add(canvasPixels[this.x][this.y + 1]);
                candidatePixels.addAll(canvasPixels[this.x][this.y + 1].neighbours(canvasWidth, canvasHeight, canvasPixels, candidatePixels, searchedPixels));
            }
        }

        if (((this.x + 1) < canvasWidth) && ((this.y + 1) < canvasHeight)) {
            if((canvasPixels[this.x + 1][this.y + 1].getPixelVal() != "X") && !searchedPixels.contains(canvasPixels[this.x + 1][this.y + 1])) {
                candidatePixels.add(canvasPixels[this.x + 1][this.y + 1]);
                candidatePixels.addAll(canvasPixels[this.x + 1][this.y + 1].neighbours(canvasWidth, canvasHeight, canvasPixels, candidatePixels, searchedPixels));
            }
        }
        return candidatePixels;
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
