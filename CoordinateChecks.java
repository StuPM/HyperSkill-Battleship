package battleship;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinateChecks {
    static boolean checkCoordinatesMatchesSizeOfShip(int[] coordinateArray, Ships ship) {
        if ((int) (Math.hypot(coordinateArray[0] - coordinateArray[2], coordinateArray[1] - coordinateArray[3]) + 1) == ship.size) {
            return true;
        } else {
            System.out.println("Error! Wrong length of the " + ship.battleshipName + "! Try again: ");
            return false;
        }
    }

    static int[] returnCoordinateArray(String input) {
        Pattern matchInputCoordinates = Pattern.compile("([A-Ja-j])(\\d*)\\s([A-Ja-j])(\\d*)");
        Matcher regexMatcher = matchInputCoordinates.matcher(input);

        int[] coordinateArray = new int[4];

        while (regexMatcher.find()) {
            coordinateArray = new int[]{
                    convertCoordinatesToNumber(regexMatcher.group(1)),
                    Integer.parseInt(String.valueOf(regexMatcher.group(2))) - 1,
                    convertCoordinatesToNumber(regexMatcher.group(3)),
                    Integer.parseInt(String.valueOf(regexMatcher.group(4))) - 1};
        }

        return coordinateArray;
    }

    static boolean checkIfCoordinatesValid(int[] coordinateArray) { //Ensure coordinate are in a straight line.
        if (coordinateArray[2] - coordinateArray[0] == 0 || coordinateArray[3] - coordinateArray[1] == 0) {
            return true;
        } else {
            System.out.println("Error! Wrong ship location! Try again: ");
            return false;
        }
    }

    static boolean checkIfOtherShipIsNear(int[] coordinateArray, char[][] battleshipBoard) {

        int startX = coordinateArray[0] - 1;
        int startY = coordinateArray[1] - 1;
        int endX = coordinateArray[2] + 1;
        int endY = coordinateArray[3] + 1;

        if (startX < 0) {
            startX = 0;
        }
        if (startY < 0) {
            startY = 0;
        }
        if (endX > 9) {
            endX = 9;
        }
        if (endY > 9) {
            endY = 9;
        }

        for (int i = startX; i <= endX; i++) {
            for (int y = startY; y <= endY; y++) {
                if (battleshipBoard[i][y] == 'O') {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
            }
        }

        return true;
    }

    static void checkCoordinatesNeedSwitching(int[] coordinateArray) {

        if (coordinateArray[0] > coordinateArray[2]) {
            int temp = coordinateArray[0];
            coordinateArray[0] = coordinateArray[2];
            coordinateArray[2] = temp;
        } else if (coordinateArray[1] > coordinateArray[3]) {
            int temp = coordinateArray[1];
            coordinateArray[1] = coordinateArray[3];
            coordinateArray[3] = temp;
        }

    }

    static int convertCoordinatesToNumber(String s) {
        switch (s.toUpperCase()) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            case "J":
                return 9;
            default:
                return -1;
        }

    }


}
