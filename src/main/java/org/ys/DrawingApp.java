package org.ys;

import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import org.ys.Canvas;
import org.ys.Line;
import org.ys.LineTypeNotSupportedException;
import org.ys.Rectangle;

public class DrawingApp {
    private String[] allowedCommands = {"C", "L", "R", "B", "Q"};
    private Canvas canvas;
    private Scanner consoleInputScanner;
    private static Map<String, String> helpMessage;

    public DrawingApp() {
        helpMessage = new LinkedHashMap<String, String>();
        helpMessage.put("C", "format: \"C w h\". Creates a new canvas of width w and height h. w and h must be greater than 0 and less than or equals to 30");
        helpMessage.put("L", "format: \"L x1 y1 x2 y2\". Creates a new line of 'x' from (x1,y1) to (x2,y2). Only supports horizontal or vertical lines.");
        helpMessage.put("R", "format: \"R x1 y1 x2 y2\". Creates a new rectangle, (x1,y1) is upper left corner & (x2,y2) is lower right corner.");
        helpMessage.put("B", "format: \"B x y c\". Fills the entire area around (x,y) with \"colour\" c. Same as that of the \"bucket fill\" tool in paint programs.");
        helpMessage.put("Q", "Quit");
    }

    public static void main(String[] args) {
        DrawingApp drawApp = new DrawingApp();
        drawApp.printHelpMessage();

        // create a scanner so we can read the command-line input
        drawApp.consoleInputScanner = new Scanner(System.in);

        // loop to receive CLI command and process for next outcome
        while(true) {
            drawApp.receiveCommand(drawApp.consoleInputScanner);
        }

    }

    public Canvas createNewCanvas(int w, int h) {
        return this.canvas = new Canvas(w, h);
    }

    public String[] getAllowedCommands() {
        return allowedCommands;
    }

    public boolean isAllowedCommand(String cmd) {
        return Arrays.asList(allowedCommands).contains(cmd);
    }

    public int[] sanitiseCommandParams(String[] params) throws NumberFormatException, IllegalArgumentException {
        if (params.length < 2 || params.length > 4)
            throw new IllegalArgumentException("Error: Inadmissable params to command!\nCommand should receive between 2 to 4 parameters");

        int[] sanitisedParams = new int[params.length];
        String param;
        for(int i=0; i < params.length; i++) {
            // reject empty chars
            param = params[i].trim();
            if (param.length() == 0)
                throw new IllegalArgumentException("Error: Empty parameter in command not allowed!");

            // assume params list of 2 or 4 elements being canvas/line/rectangle creation
            if (params.length == 2 || params.length == 4) {
                sanitisedParams[i] = validatedNumericParam(param);
            }

            // validate x, y, c params for B command
            if (params.length == 3) {
                if(i < 2)
                    sanitisedParams[i] = validatedNumericParam(param);
                if(i == 2) {
                    int fillColor = (int)(param.charAt(0));
                    if(fillColor < 33 || fillColor > 126)
                        throw new IllegalArgumentException(("Error: Fill color for bucket must be printable character in ascii decimal code range 33 to 126!"));
                    sanitisedParams[i] = fillColor;
                }
            }
        }
        return sanitisedParams;
    }

    void receiveCommand(Scanner consoleInputScanner) {
        System.out.print("enter command: ");
        try {
            processCommand(consoleInputScanner.nextLine());
            if (canvas != null)
                canvas.render();
        } catch(NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch(LineTypeNotSupportedException lte) {
            System.out.println(lte.getMessage());
        } finally {
            printHelpMessage();
        }
        System.out.println();
    }

    void processCommand(String consoleInputLine) throws NumberFormatException, IllegalArgumentException {
        String[] commandWithArgs = consoleInputLine.trim().split(" ");

        String command = commandWithArgs[0].toUpperCase();
        if (!isAllowedCommand(command))
           throw new IllegalArgumentException("Inadmissable Command: " + command + "!\nAllowed commands are: " + Arrays.toString(allowedCommands));

        String[] args = Arrays.copyOfRange(commandWithArgs, 1, commandWithArgs.length);

        int x1, x2, y1, y2;
        switch(command) {
            case "Q":
                quit();
                break;
            case "C":
                canvas = null;
                if (args.length != 2)
                    throw new IllegalArgumentException("Error: 2 integer parameters required for canvas creation!");
                int[] canvasParams = sanitiseCommandParams(args);
                int width = canvasParams[0];
                int height = canvasParams[1];
                canvas = new Canvas(width, height);
                break;
            case "L":
                if (args.length != 4)
                    throw new IllegalArgumentException("Error: 4 integer parameters required for line creation!");
                int[] lineParams = sanitiseCommandParams(args);
                x1 = lineParams[0];
                y1 = lineParams[1];
                x2 = lineParams[2];
                y2 = lineParams[3];
                Line line = new Line(x1, y1, x2, y2);
                canvas = line.paintCanvasPixels(canvas);
                break;

            case "R":
                if (args.length != 4)
                    throw new IllegalArgumentException("Error: 4 integer parameters required for rectangle creation!");
                int[] rectangleParams = sanitiseCommandParams(args);
                x1 = rectangleParams[0];
                y1 = rectangleParams[1];
                x2 = rectangleParams[2];
                y2 = rectangleParams[3];
                Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
                canvas = rectangle.paintCanvasPixels(canvas);
                break;

            case "B":
                if (args.length != 3)
                    throw new IllegalArgumentException("Error: 2 integer parameters and 1 character parameter required for bucket fill!");
                int[] bucketFillParams = sanitiseCommandParams(args);
                int x = bucketFillParams[0];
                int y = bucketFillParams[1];
                char c = (char)(bucketFillParams[2]);
                BucketFill bucketFill = new BucketFill(x, y, c);
                canvas = bucketFill.fillCanvas(canvas);
                break;

            default:
                throw new IllegalArgumentException(("Error: Command must be either C, L, R, B or Q!"));
        }
    }

    int validatedNumericParam(String param) {
        int numericParam;

        try {
            numericParam = Integer.parseInt(param);
            if (this.canvas == null) {
                if (numericParam <= 0 || numericParam > 30)
                    throw new IllegalArgumentException("Error: Inadmissable canvas/line/rectangle/fill command params!\nAllowed canvas width/height or line/rectangle/fill coordinates must be between 1 to 30");
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Error: Inadmissable params to command!\nOnly numeric integer params allowed!");
        }
        return numericParam;
    }

    void quit() {
        consoleInputScanner.close();
        System.out.println("BYE!\n");
        System.exit(0);
    }

    void printHelpMessage() {
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println("\n\nWELCOME to the Drawing App.\n\n");
        System.out.println("How to use:\n============");
        for(Map.Entry<String, String> entry : helpMessage.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("\t" + key + " - " + value);
        }
    }
}
