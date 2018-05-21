package org.ys;

import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.NoSuchElementException;

import org.ys.DrawingApp;

public class Cli {
    private DrawingApp drawApp;
    private Map<String, String> helpMessage;
    private static Scanner consoleInputScanner = new Scanner(System.in);
    public static String[] allowedCommands = {"C", "L", "R", "B", "Q"};

    public Cli(DrawingApp drawApp){
        this.drawApp = drawApp;
        helpMessage = new LinkedHashMap<String, String>();
        helpMessage.put("C", "format: \"C w h\". Creates a new canvas of width w and height h. w and h must be greater than 0 and less than or equals to 30");
        helpMessage.put("L", "format: \"L x1 y1 x2 y2\". Creates a new line of 'x' from (x1,y1) to (x2,y2). Only supports horizontal or vertical lines.");
        helpMessage.put("R", "format: \"R x1 y1 x2 y2\". Creates a new rectangle, (x1,y1) is upper left corner & (x2,y2) is lower right corner.");
        helpMessage.put("B", "format: \"B x y c\". Fills the entire area around (x,y) with \"colour\" c. Same as that of the \"bucket fill\" tool in paint programs.");
        helpMessage.put("Q", "Quit");

        printHelpMessage();
    }

    public void receiveCommand() {
        System.out.print("enter command: ");
        try {
            processCommand(consoleInputScanner.nextLine());
        } catch(NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch(LineTypeNotSupportedException lte) {
            System.out.println(lte.getMessage());
        } catch(NoSuchElementException nsee) {
            System.out.println("No user input received!\n\t" + nsee.getMessage());
        } finally {
            printHelpMessage();
        }
        System.out.println();
    }

    public void printHelpMessage() {
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println("\n| WELCOME to the Drawing App. |\n");
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println("How to use:\n\n================");
        for(Map.Entry<String, String> entry : helpMessage.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("\t" + key + " - " + value);
        }
    }

    public boolean isAllowedCommand(String cmd) {
        return Arrays.asList(allowedCommands).contains(cmd);
    }

    public void processCommand(String consoleInputLine) throws NumberFormatException, IllegalArgumentException {
        String[] commandWithArgs = consoleInputLine.trim().split(" ");

        String command = commandWithArgs[0].toUpperCase();
        if (!isAllowedCommand(command))
           throw new IllegalArgumentException("Inadmissable Command: " + command + "!\nAllowed commands are: " + Arrays.toString(allowedCommands));

        String[] args = Arrays.copyOfRange(commandWithArgs, 1, commandWithArgs.length);

        int x1, x2, y1, y2;
        switch(command) {
            case "Q":
                consoleInputScanner.close();
                drawApp.quit();
                break;
            case "C":
                if (args.length != 2)
                    throw new IllegalArgumentException("Error: 2 integer parameters required for canvas creation!");
                int[] canvasParams = sanitiseCommandParams(args);
                int width = canvasParams[0];
                int height = canvasParams[1];
                drawApp.createNewCanvas(width, height);
                break;
            case "L":
                if (args.length != 4)
                    throw new IllegalArgumentException("Error: 4 integer parameters required for line creation!");
                int[] lineParams = sanitiseCommandParams(args);
                x1 = lineParams[0];
                y1 = lineParams[1];
                x2 = lineParams[2];
                y2 = lineParams[3];
                drawApp.drawLine(x1, y1, x2, y2);
                break;

            case "R":
                if (args.length != 4)
                    throw new IllegalArgumentException("Error: 4 integer parameters required for rectangle creation!");
                int[] rectangleParams = sanitiseCommandParams(args);
                x1 = rectangleParams[0];
                y1 = rectangleParams[1];
                x2 = rectangleParams[2];
                y2 = rectangleParams[3];
                drawApp.drawRectangle(x1, y1, x2, y2);
                break;

            case "B":
                if (args.length != 3)
                    throw new IllegalArgumentException("Error: 2 integer parameters and 1 character parameter required for bucket fill!");
                int[] bucketFillParams = sanitiseCommandParams(args);
                int x = bucketFillParams[0];
                int y = bucketFillParams[1];
                char c = (char)(bucketFillParams[2]);
                drawApp.bucketFill(x, y, c);
                break;

            default:
                throw new IllegalArgumentException(("Error: Command must be either C, L, R, B or Q!"));
        }
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

    int validatedNumericParam(String param) {
        int numericParam;

        try {
            numericParam = Integer.parseInt(param);
            if (drawApp.getCanvas() == null) {
                if (numericParam <= 0 || numericParam > 30)
                    throw new IllegalArgumentException("Error: Inadmissable canvas/line/rectangle/fill command params!\nAllowed canvas width/height or line/rectangle/fill coordinates must be between 1 to 30");
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Error: Inadmissable params to command!\nOnly numeric integer params allowed!");
        }
        return numericParam;
    }
}
