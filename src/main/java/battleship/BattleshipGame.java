package battleship;

import battleship.model.ocean.Ocean;

import java.util.Scanner;

public class BattleshipGame {

    /**
     * This is the main entry point for the game.
     */
    public static void main(String[] args) {

        // Loop for games.
        do {
            // Welcome message.
            System.out.println("\nWelcome to the Battleship Game!\n");

            // Set up the ocean.
            Ocean ocean = new Ocean();

            // Place the ships.
            ocean.placeAllShipsRandomly();

            // Loop to accept shots until game over.
            do {
                // Fire a shot. Report if hit or miss.
                if (shoot(ocean)) {
                    System.out.println("Congratulations! You got a hit!");
                } else {
                    System.out.println("Sorry, you missed.");
                }

                // Display the ocean.
                System.out.println(ocean.toString());

            } while (!ocean.isGameOver());

            // Print final scores.
            System.out.println("Game over!\n"
                    + "----------\n"
                    + "Shots fired : " + ocean.getShotsFired() + "\n"
                    + "Hit count   : " + ocean.getHitCount() + "\n"
                    + "Ships sunk  : " + ocean.getShipsSunk());

            //TODO Display where the ships were (nice to have)

        } while (playAgain());

        // Sign off message to user.
        System.out.println("\nThank you for playing. Goodbye.");
    }

    /**
     * Get row and column to shoot at from the user.
     *
     * @param ocean The ocean being shot at.
     * @return      If there was a hit.
     */
    private static boolean shoot(Ocean ocean) {

        // Open an input channel.
        Scanner inputShoot = new Scanner(System.in);

        // Get the row to fire at.
        System.out.print("Row: ");
        int row = inputShoot.nextInt();

        // Get the column to fire at.
        System.out.print("Column: ");
        int column = inputShoot.nextInt();

        // Shoot and return hit status.
        return ocean.shotAt(row, column);
    }

    /**
     * Ask the user if they want to play again.
     *
     * @return True if they want to play again.
     */
    private static boolean playAgain() {

        // Open an input channel.
        Scanner inputPa = new Scanner(System.in);

        // Prompt user.
        System.out.print("\nPlay again (Y/N) default=N ? : ");

        // Get input.
        String sPa = inputPa.nextLine();

        // If any user input.
        if (sPa.length() > 0) {
            // Check first character of user response.
            if (Character.toUpperCase(sPa.charAt(0)) == 'Y') {
                return true;
            }
        }

        // If user does not want to.
        return false;
    }
}
