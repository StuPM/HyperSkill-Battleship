package battleship;

import java.util.Scanner;

import static battleship.CoordinateChecks.convertCoordinatesToNumber;

public class Main {

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {

        Scanner scanner = new Scanner(System.in);
        Board player1 = new Board(1);
        Board player2 = new Board(2);

        prepareGame(player1, scanner);
        ingestEnter(scanner);
        prepareGame(player2, scanner);

        while (true) {
            ingestEnter(scanner);
            if (startShooting(player1, player2, scanner)) {
                break;
            }
            ingestEnter(scanner);
            if (startShooting(player2, player1, scanner)) {
                break;
            }
        }

    }

    private static boolean startShooting(Board leadPlayer, Board subPlayer, Scanner scanner) {

        leadPlayer.drawShotBoard();
        System.out.println("---------------------");
        leadPlayer.drawShipBoard();
        System.out.println(leadPlayer.getName() + ", it's your turn: ");

        String input = scanner.nextLine();

        int[] array = new int[]{convertCoordinatesToNumber(input.substring(0, 1)), Integer.parseInt(input.substring(1)) - 1};

        if (array[0] >= 0 && array[0] <= 9 && array[1] >= 0 && array[1] <= 9) {

            if (subPlayer.checkIfHit(array)) {
                leadPlayer.updateShotBoard(array, 'X');
                subPlayer.updateShipBoard(array, 'X');

                if (subPlayer.checkIfAllShipsDestroyed()) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    return true;
                } else {
                    subPlayer.checkIfShipIsAround(array);
                }
            } else {
                leadPlayer.updateShotBoard(array, 'M');
                subPlayer.updateShipBoard(array, 'M');
                System.out.println("You missed!");
            }


        } else {
            System.out.println("Error! You entered the wrong coordinates! Try again: ");
        }
        return false;
    }

    private static void prepareGame(Board leadPlayer, Scanner scanner) {
        System.out.println(leadPlayer.getName() + ", place your ships on the game field");
        leadPlayer.fillBoards();
        leadPlayer.addShips(scanner);
    }

    private static void ingestEnter(Scanner scanner) {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

}
