/*
This class stores and manipulates the dictionary of SourceDestination pairs.
 */
package charpair;

import java.util.ArrayList;
import java.util.Random;

public class Model {

    ArrayList<SourceDestination> sourceDestinations = new ArrayList<>();

    public void addSourceDestination(char source, char destination) {
        int index = this.sourceDestinationIndex(source, destination);

        if (index >= 0) { //If exists, iterate the occurence
            this.sourceDestinations.get(index).iterateOccurence();
        } else { //If not exists, add as new SourceDestination
            this.sourceDestinations.add(new SourceDestination(source, destination));
        }

    }

    public void calculateProbabilities() {
        //Get all the unique source characters
        ArrayList<Character> sources = this.listAllUniqueSources();

        for (int x = 0; x < sources.size(); x++) { //Iterate through each source
            ArrayList<SourceDestination> destinationResults = this.listDestinationsOfSource(sources.get(x));
            //Probability is based on total of occurences for all destinations of a particular source
            int totalOccurences = this.totalOccurrencesForSource(sources.get(x)); 

            for (int y = 0; y < destinationResults.size(); y++) { //Calculate for each SourceDestination
                destinationResults.get(y).calculateProbability(totalOccurences);
            }
        }
    }

    public SourceDestination getRandomSourceDestination() {
        /*
        Used in beginning the generation of output, and also when there are no
        destinations for a particular source.
        */
        Random rand = new Random();
        return this.sourceDestinations.get(rand.nextInt(sourceDestinations.size()));
    }

    public ArrayList listAllUniqueSources() {
        /*
        Used to calculate probability for each unique source.
        */
        ArrayList<Character> sources = new ArrayList<>();

        for (int x = 0; x < this.sourceDestinations.size(); x++) {
            if (!sources.contains(sourceDestinations.get(x).getSource())) {
                sources.add(sourceDestinations.get(x).getSource());
            }
        }

        return sources;
    }

    public ArrayList listDestinationsOfSource(char source) {
        /*
        Used to list the different possibilities for a particular source.
        */
        ArrayList<SourceDestination> destinationsOfSource = new ArrayList<>();

        for (int x = 0; x < sourceDestinations.size(); x++) {
            if (source == this.sourceDestinations.get(x).getSource()) {
                destinationsOfSource.add(this.sourceDestinations.get(x));
            }
        }

        return destinationsOfSource;
    }

    public void printSourceDestinations() {
        System.out.println();
        
        for (int x = 0; x < this.sourceDestinations.size(); x++) {
            System.out.println(this.sourceDestinations.get(x).toString());
        }
        
        System.out.println();
    }

    public int sourceDestinationIndex(char source, char destination) {
        int index = -1; //If -1, does not exist

        for (int x = 0; x < this.sourceDestinations.size() && index == -1; x++) {
            if (source == this.sourceDestinations.get(x).getSource() && destination == this.sourceDestinations.get(x).getDestination()) {
                index = x;
            }
        }

        return index;
    }

    public int totalOccurrencesForSource(char source) {
        int totalOccurences = 0;
        ArrayList<SourceDestination> sources = this.listDestinationsOfSource(source);

        for (int x = 0; x < sources.size(); x++) {
            totalOccurences += sources.get(x).getOccurences();
        }

        return totalOccurences;
    }

}
