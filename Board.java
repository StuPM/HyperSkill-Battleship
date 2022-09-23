package battleship;

import java.util.Arrays;
import java.util.Scanner;

import static battleship.CoordinateChecks.*;

public class Board {

    private char[][] shipBoard;
    private char[][] shotBoard;
    private String name;

    Board(int numberName) {
        this.shipBoard = new char[10][10];
        this.shotBoard = new char[10][10];
        this.name = "Player " + numberName;
    }

    public void fillBoards() {
        for (char[] row : this.shipBoard) {
            Arrays.fill(row, '~');
        }

        for (char[] row : this.shotBoard) {
            Arrays.fill(row, '~');
        }
    }

    public void addShips(Scanner scanner) {

        drawBoard(this.shipBoard);

        int[] coordinateArray;

        for (Ships ship : Ships.values()) {
            boolean shipIsPlaced = false;

            do {
                System.out.print("Enter the coordinates of the " + ship.battleshipName + " (" + ship.size + " cells):");

                String input = scanner.nextLine();
                coordinateArray = returnCoordinateArray(input);

                checkCoordinatesNeedSwitching(coordinateArray);

                if (checkCoordinatesMatchesSizeOfShip(coordinateArray, ship)
                        && checkIfOtherShipIsNear(coordinateArray, this.shipBoard)
                        && checkIfCoordinatesValid(coordinateArray)) {
                    shipIsPlaced = true;
                }

            } while (!shipIsPlaced);

            drawShipByCoordinates(coordinateArray, this.shipBoard);
            drawBoard(this.shipBoard);
        }
    }

    private static void drawShipByCoordinates(int[] coordinateArray, char[][] battleshipBoard) {
        for (int i = coordinateArray[0]; i <= coordinateArray[2]; i++) {
            for (int y = coordinateArray[1]; y <= coordinateArray[3]; y++) {
                battleshipBoard[i][y] = 'O';
            }
        }
    }

    private void drawBoard(char[][] battleshipBoard) {
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10\n");
        int columnAlpha = 65;
        for (char[] row : battleshipBoard) {
            System.out.print((char) columnAlpha);
            columnAlpha++;
            for (char col : row) {
                System.out.print(" " + col);
            }
            System.out.print("\n");
        }
    }

    public String getName() {
        return this.name;
    }

    public void drawShotBoard() {
        drawBoard(this.shotBoard);
    }

    public void drawShipBoard() {
        drawBoard(this.shipBoard);
    }

    public boolean checkIfHit(int[] array) {
        return this.shipBoard[array[0]][array[1]] == 'O' || this.shipBoard[array[0]][array[1]] == 'X';
    }

    public void updateShotBoard(int[] array, char input) {
        this.shotBoard[array[0]][array[1]] = input;
    }

    public void updateShipBoard(int[] array, char input) {
        this.shipBoard[array[0]][array[1]] = input;

    }

    public boolean checkIfAllShipsDestroyed() {
        for (char[] chars : this.shipBoard) {
            for (int y = 0; y < this.shipBoard.length; y++) {
                if (chars[y] == 'O') {
                    return false;
                }
            }
        }

        return true;
    }

    public void checkIfShipIsAround(int[] coordinateArray) {
        int lowerX = coordinateArray[0] - 1;
        int lowerY = coordinateArray[1] - 1;
        int upperX = coordinateArray[0] + 1;
        int upperY = coordinateArray[1] + 1;

        if (lowerX < 0) {
            lowerX = 0;
        }
        if (lowerY < 0) {
            lowerY = 0;
        }
        if (upperX > 9) {
            upperX = 9;
        }
        if (upperY > 9) {
            upperY = 9;
        }

        for (int i = lowerX; i <= upperX; i++) {
            for (int y = lowerY; y <= upperY; y++) {
                if (this.shipBoard[i][y] == 'O') {
                    System.out.println("You hit a ship!");
                    return;
                }

            }
        }
        System.out.println("You sank a ship!");
    }

}
