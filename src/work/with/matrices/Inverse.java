//Finds the inverse of a matrix. Augments on the idenity matrix, does Gauss Jordan elimination.
//Then, trims it to the left side and returns the resulting square matrix.
//In the future, it can be made into a static class. 

package work.with.matrices;

import java.util.Scanner;

public class Inverse {

    private Matrix toInvert;
    private int numberOfRows;
    private int numberOfColumns;
    private Scanner reader;
    private int startingRowNumber;
    private int startingColumnNumber;

    public Inverse(Matrix startingMatrix, Scanner reader) {
        int startingRowNumber = startingMatrix.getNumberOfRows();
        int startingColumnNumber = startingMatrix.getNumberOfColumns();
        this.numberOfRows = startingRowNumber;
        this.numberOfColumns = startingColumnNumber * 2;
        this.reader = reader;
        augment(startingMatrix);
    }
//Maks a new matrix that will be a copy of the original, then augments on the identity
    public void augment(Matrix startingMatrix) {
        this.toInvert = new Matrix(numberOfRows, numberOfColumns, reader);
        for (int currentRow = 0; currentRow < numberOfRows; currentRow++) {
            for (int currentColumn = 0; currentColumn < numberOfColumns; currentColumn++) {
                double k = currentColumn - (numberOfColumns / 2.0);
                if (k < 0) {
                    Fraction toReplaceWith = startingMatrix.getNumber(currentRow, currentColumn);
                    Fraction newFraction = new Fraction(toReplaceWith.getNum(), toReplaceWith.getDenom());
                    toInvert.setNumber(currentRow, currentColumn, newFraction);
                } else if (k == currentRow) {
                    toInvert.setNumber(currentRow, currentColumn, new Fraction(1, 1));
                } else {
                    toInvert.setNumber(currentRow, currentColumn, new Fraction(0, 0));
                }
            }
        }
    }

    public Matrix invert() {
        //Reduces the augmented matrix.
        RowReduce inverted = new RowReduce(toInvert);
        inverted.rowReduce();
        //Makes a new matrix for the inverse to go into.
        Matrix tempMatrix = new Matrix(numberOfRows, (numberOfColumns / 2), reader);
        
        //Only adds the right square to the new matrix.
        for (int currentRow = 0; currentRow < numberOfRows; currentRow++) {
            for (int currentColumn = (numberOfColumns / 2); currentColumn < numberOfColumns; currentColumn++) {
                tempMatrix.setNumber(currentRow, (currentColumn - (numberOfColumns / 2)), toInvert.getNumber(currentRow, currentColumn));
            }
        }
        return tempMatrix;
    }
}
