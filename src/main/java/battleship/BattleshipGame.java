package battleship;

import java.util.Scanner;

import battleship.model.ocean.Ocean;

public class BattleshipGame {

    private static Scanner scanner;

    private BattleshipGame() {
    }

    /**
     * This is the main entry point for the game.
     */
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            scanner = sc;

            // Loop for games.
            do {
                // Welcome message.
                System.out.println("\nWelcome to the Battleship Game!\n");

                // Set up the ocean.
                Ocean ocean = new Ocean();

                // Place the ships.
                ocean.placeAllShipsRandomly();

                // Display the ocean.
                System.out.println(ocean.toString());

                // Loop to accept shots until game over.
                do {
                    // Fire a shot. Report if hit or miss.
                    shoot(ocean);

                    System.out.println(ocean.toString());
                } while (!ocean.isGameOver());

                // Print final scores.
                System.out.println("Game over!\n"
                        + "----------\n"
                        + "Shots fired : " + ocean.getShotsFired() + "\n"
                        + "Hit count   : " + ocean.getHitCount() + "\n"
                        + "Ships sunk  : " + ocean.getShipsSunk());

            } while (playAgain());

            // Sign off message to user.
            System.out.println("\nThank you for playing. Goodbye.");
        }
    }

    /**
     * Get row and column to shoot at from the user.
     * 
     * @param ocean
     *            The ocean being shot at.
     * @return If there was a hit.
     */
    private static void shoot(Ocean ocean) {
        // Get the row to fire at and consume next line.
        System.out.print("Row: ");
        int row = scanner.nextInt();
        scanner.nextLine();

        // Get the column to fire at and consume next line.
        System.out.print("Column: ");
        int column = scanner.nextInt();
        scanner.nextLine();

        if (ocean.shotAt(row, column)) {
            if (ocean.getShipArray()[row][column].isSunk()) {
                System.out.println(String.format("You just sank a %s", ocean.getShipArray()[row][column].getShipType()));
            } else {
                System.out.println("Congratulations! You got a hit!");
            }
        } else {
            System.out.println("Sorry, you missed.");
        }
    }

    /**
     * Ask the user if they want to play again.
     * 
     * @return True if they want to play again.
     */
    private static boolean playAgain() {
        // Prompt user.
        System.out.print("\nPlay again (Y/N) default=N ? : ");

        // Get input.
        String playAgain = scanner.nextLine();

        // If any user input.
        if ((playAgain.length() > 0) && (Character.toUpperCase(playAgain.charAt(0)) == 'Y')) {
            return true;
        }

        // If user does not want to.
        return false;
    }
}
