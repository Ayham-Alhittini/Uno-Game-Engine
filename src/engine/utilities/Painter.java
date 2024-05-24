package engine.utilities;

import shared.constants.CardColor;
import shared.constants.ConsoleColors;

public class Painter {

    public static String cardPainter(String text, CardColor cardColor) {

        if (cardColor == CardColor.Wild)
            return wildPainter(text);

        String color = switch (cardColor) {
            case Red -> ConsoleColors.RED;
            case Blue -> ConsoleColors.BLUE;
            case Yellow -> ConsoleColors.YELLOW;
            case Green -> ConsoleColors.GREEN;
            default -> ConsoleColors.RESET;
        };
        return paint(text, color);
    }

    private static String wildPainter(String text) {
        String []colors = {ConsoleColors.RED, ConsoleColors.BLUE, ConsoleColors.YELLOW, ConsoleColors.GREEN};
        int colorIndex = 0;

        StringBuilder wildColor = new StringBuilder();

        for (int i = 0; i < text.length(); i++, colorIndex++) {
            wildColor.append(colors[colorIndex % colors.length]).append(text.charAt(i));
        }
        wildColor.append(ConsoleColors.RESET);

        return wildColor.toString();
    }

    public static String paint(String text, String color) {
        return color + text + ConsoleColors.RESET;
    }

    public static void printError(String text) {
        System.out.println(paint(text, ConsoleColors.MAGENTA));
    }

    public static void printAnnouncement(String text) {
        System.out.println(paint(text, ConsoleColors.CYAN));
    }

    public static void printInput(String text) {
        System.out.print(paint(text, ConsoleColors.GRAY));
    }

}
