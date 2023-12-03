import java.util.Scanner;

abstract class Main {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to the Tenzis Dice Game!");
        System.out.println("Select the same dice face in every roll until you win.\n");

        // Create an instance of the DiceGame class
        DiceGame diceGame = new DiceGame();
        // Run the dice game
        diceGame.playGame();
    }
}

class DiceGame {
    private boolean won = false;
    private int[] dices = new int[10];
    private int[] selectedDices = new int[10];
    private int diceInput;

    public DiceGame() {
        // Initialize arrays
        dices = new int[10];
        selectedDices = new int[10];
    }

    // Check if all elements in the array are the same
    private boolean allDicesMatched(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                return false;
            }
        }

        if (arr.length == 0) {
            return false;
        }

        int firstElement = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != firstElement) {
                return false; // Found an element that is different, so return false
            }
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != diceInput) {
                System.out.print(arr);
                return false;
            }
        }

        return true; // All elements are the same
    }

    // Generate a random dice face (1 to 6)
    private int generateDice() {
        return (int) (Math.random() * 6) + 1;
    }

    // Generate initial values for all dices
    private void generateAllDices() {
        for (int i = 0; i < 10; i++) {
            dices[i] = generateDice();
        }
    }

    // Hold the selected dice and display the updated dice faces
    private void holdDice(int diceInput) {
        for (int i = 0; i < 10; i++) {
            if (dices[i] == diceInput) {
                selectedDices[i] = diceInput;
            }
        }

        for (int value : dices) {
            if (value == diceInput) {
                System.out.print("\u001B[31m" + value + "\u001B[0m ");
            } else {
                System.out.print(value + " ");
            }
        }

        System.out.println();

        // Check if all dices are matched
        if (allDicesMatched(selectedDices)) {
            won = true;
        }
    }

    // Roll all dices and display the results
    private void rollDices() {
        for (int i = 0; i < 10; i++) {
            dices[i] = generateDice();
        }

        for (int value : dices) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Replace held dices with selected dices
        for (int i = 0; i < selectedDices.length; i++) {
            if (selectedDices[i] != 0) {
                dices[i] = selectedDices[i];
            }
        }
    }

    // Start the dice game and handle user input
    public void playGame() {
        generateAllDices();
        Scanner scanner = new Scanner(System.in);

        while (!won) {
            // Display all dices
            rollDices();
            System.out.print("Enter the dice number to keep: ");

            // Validate user input
            while (!scanner.hasNextInt()) {
                System.out.println("\nDice face must be an integer.");
                System.out.print(":> ");
                scanner.next();
            }

            diceInput = scanner.nextInt();
            holdDice(diceInput);
        }

        // If all selected dices are held, player won
        if (won) {
            System.out.println("Player won!");
        }

        scanner.close();
    }
}
