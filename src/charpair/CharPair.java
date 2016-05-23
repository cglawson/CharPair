/*
This is the main class that the program runs.
 */
package charpair;

import java.util.Scanner;

public class CharPair {

    Control control = new Control();
    Scanner input = new Scanner(System.in);
    int menuChoice;
    boolean exit = false;

    public void displayMenu() {
        System.out.println("Menu: ");
        System.out.println("    1. Console input.");
        System.out.println("    2. Console output (Choose random, then roll for acceptance).");
        System.out.println("    3. Console output (Roll, choose based on probability wheel).");
        System.out.println("    4. Console output (Pure random choice from model).");
        System.out.println("    5. Exit.");
    }

    public void getMenuChoice() {
        menuChoice = input.nextInt();
    }

    public void menuLoop() {
        while (!this.exit) {
            this.displayMenu();
            this.getMenuChoice();

            switch (this.menuChoice) {
                case 1:
                    System.out.println("Paste text into the console, be sure to have removed any newlines or paragraph breaks, then press ENTER.");
                    this.control.getInputFromConsole();
                    this.control.loadInputToModel();
                    break;
                case 2:
                    System.out.println("Enter the character length of the output, then press ENTER.");
                    this.control.getInputFromConsole();
                    System.out.println(this.control.generateOutput(Integer.parseInt(control.input)));
                    break;
                case 3:
                    System.out.println("Enter the character length of the output, then press ENTER.");
                    this.control.getInputFromConsole();
                    System.out.println(this.control.generateOutput2(Integer.parseInt(control.input)));
                    break;                    
                    case 4:
                    System.out.println("Enter the character length of the output, then press ENTER.");
                    this.control.getInputFromConsole();
                    System.out.println(this.control.generateOutput3(Integer.parseInt(control.input)));
                    break;
                case 5:
                    System.out.println("EXITED.");
                    this.exit = true;
            }
        }
    }

    public static void main(String[] args) {
        CharPair charpair = new CharPair();
        charpair.menuLoop();
    }

}
