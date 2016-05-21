/*
This class manipulates the Model and uses it to generate results.
 */
package charpair;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Control {

    Model model = new Model();
    Scanner console = new Scanner(System.in);
    String input = new String();

    public String generateOutput(int outputLength) {
        String output = "";
        char currSource = this.model.getRandomSourceDestination().getSource(); //Start with a random
        ArrayList<SourceDestination> destinationsPossibleForSource;
        Random rand = new Random();

        while (output.length() < outputLength) { //String building loop
            output = output + currSource;
            destinationsPossibleForSource = this.model.listDestinationsOfSource(currSource);

            while (destinationsPossibleForSource.isEmpty()) { //If a source has no destinations, pick random
                currSource = this.model.getRandomSourceDestination().getSource();
                destinationsPossibleForSource = this.model.listDestinationsOfSource(currSource);
            }

            boolean picked = false;
            while (!picked) { //Choose a random destination from the list, roll against its probability to accept
                SourceDestination candidate = destinationsPossibleForSource.get(rand.nextInt(destinationsPossibleForSource.size()));
                double roll = rand.nextDouble();

                if (roll <= candidate.getProbability()) {
                    picked = true;
                    currSource = candidate.getDestination();
                }
            }
        }

        return output;
    }

    public String generateOutput2(int outputLength) {
        String output = "";
        char currSource = this.model.getRandomSourceDestination().getSource(); //Start with random
        ArrayList<SourceDestination> destinationsPossibleForSource;
        Random rand = new Random();

        while (output.length() < outputLength) { //String building loop
            output = output + currSource; //Add onto string
            

            destinationsPossibleForSource = this.model.listDestinationsOfSource(currSource);

            while (destinationsPossibleForSource.isEmpty()) { //If list is empty choose random
                currSource = this.model.getRandomSourceDestination().getSource();
                destinationsPossibleForSource = this.model.listDestinationsOfSource(currSource);
            }

            boolean picked = false;
            while (!picked) {
                double roll = rand.nextDouble(); //Roll for a double
                double cumulativeProbability = 0.0;

                for (int x = 0; x < destinationsPossibleForSource.size() && !picked; x++) { //Find the destination that matches the roll
                    if (roll > cumulativeProbability && roll < (cumulativeProbability + destinationsPossibleForSource.get(x).getProbability())) {
                        picked = true;
                        currSource = destinationsPossibleForSource.get(x).getDestination();
                    }
                    cumulativeProbability += destinationsPossibleForSource.get(x).getProbability();
                }
            }
        }

        return output;
    }

    public void getInputFromConsole() {
        input = console.nextLine();
    }

    public void loadInputToModel() {
        for (int x = 0; x < input.length() - 1; x++) {
            char source = input.charAt(x);
            char destination = input.charAt(x + 1);

            this.model.addSourceDestination(source, destination);
        }

        this.model.calculateProbabilities();
    }
}
