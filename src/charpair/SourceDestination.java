/*
This class is the base unit that the Model unit uses to store data about
SourceDestination character pairs, the total number of occurences encountered,
and the calculated probability of a particular SourceDestination verses all
other destination choices for a Source.
 */
package charpair;

public class SourceDestination {

    private final char source;
    private final char destination;
    private int occurences = 1;
    private double probability;

    public SourceDestination(char source, char destination) {
        this.source = source;
        this.destination = destination;
    }

    public void calculateProbability(int numSources) {
        /*
        Probability is calculated from how many times the particular 
        SourceDestination has occured versus the total number of occurences
        of a particular source character.
        
        For instance, source character A has two SourceDestinations: (A,B) which
        has occurences of 2, and (A,C) which has occurences of 1. The source
        A has total occurences of 3.  Therefore (A,B) has a probability of .66
        and (A,C) has a probability of .33.
        */
        this.probability = (double) this.occurences / (double) numSources;
    }

    public char getDestination() {
        return this.destination;
    }

    public char getSource() {
        return this.source;
    }

    public int getOccurences() {
        return this.occurences;
    }

    public double getProbability() {
        return this.probability;
    }

    public void iterateOccurence() {
        this.occurences++;
    }

    @Override
    public String toString() {
        String string = this.getSource() + " " + this.getDestination() + ": " + this.getProbability();

        return string;
    }

}
