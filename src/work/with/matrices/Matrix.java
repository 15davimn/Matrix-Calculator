//The representation of a matrix. To be improved later by adding try and catch blocks.

package work.with.matrices;

import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Matrix {

    private Fraction[][] matrix;
    private Scanner reader;
    private int numberOfRows;
    private int numberOfColumns;
    
    public int getNumber() {
        while(true) {
            String entered = reader.nextLine();
            if (StringUtils.isNumeric(entered)) {
                int numberEntered = Integer.parseInt(entered);
                if (numberEntered > 0) {
                    return numberEntered;
                }
            }
            System.out.println("Incorrect input.");
        }
    }

    public Matrix(Scanner reader) {
        this.reader = reader;
        System.out.println("Enter how many rows you want the matrix to be, then how many columns");
        this.numberOfRows = getNumber();
        this.numberOfColumns = getNumber();
        this.matrix = new Fraction[numberOfRows][numberOfColumns];

        fillMatrix();
        System.out.println("You made the following matrix");
        displayMatrix();
    }

    public Matrix(int numberOfRows, int numberOfColumns, Scanner reader) {
        this.matrix = new Fraction[numberOfRows][numberOfColumns];
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.reader = reader;

    }

    public void setNumber(int row, int column, Fraction number) {
        matrix[row][column] = number;
    }

    public void multiplyARow(Fraction toMultiplyBy, int rowWorkingOn) {
        int i = 0;
        while (i < numberOfColumns) {
            matrix[rowWorkingOn][i].multiply(toMultiplyBy);
            matrix[rowWorkingOn][i].reduce();
            i++;

        }
    }

    public void swapRows(int row1, int row2) {
        Fraction[] temp1 = matrix[row1];
        Fraction[] temp2 = matrix[row2];
        matrix[row1] = temp2;
        matrix[row2] = temp1;

    }

    public void reduceRow(int rowToReduce) {

        for (int i = 0; i < numberOfColumns; i++) {
            matrix[rowToReduce][i].reduce();
        }

    }

    public Fraction getNumber(int row, int column) {
        return matrix[row][column];
    }

    public void addAnArray(Fraction[] arrayToAdd, int rowToAddTo) {
        for (int i = 0; i < numberOfColumns; i++) {
            matrix[rowToAddTo][i].addFraction(arrayToAdd[i]);
        }
    }

    public void displayMatrix() {
        int column = 0;
        int row = 0;
        int i = 0;

        while (i < numberOfRows) {
            reduceRow(i);
            i++;
        }
        while (row < numberOfRows) {
            while (column < numberOfColumns) {
                System.out.printf("%8s", matrix[row][column]);
                column++;
            }
            System.out.println("");
            row++;
            column = 0;
        }

    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    public void fillMatrix() {
        System.out.println("You are creating an array with " + numberOfRows + " rows and " + numberOfColumns + " columns. Enter the numbers one at a time, filling the rows left to right. If you want to enter a fraction, type f, then the fraction. Otherwise, enter an integer.");
        int currentRow = 0;
        int currentColumn = 0;
        while (currentRow < numberOfRows) {
            while (currentColumn < numberOfColumns) {
                System.out.println("Enter number for row " + (currentRow + 1) + " column " + (currentColumn + 1) + ":");
                String toFillWith = reader.nextLine();
                boolean isNumbers = StringUtils.isNumeric(toFillWith);
                char toEqual;
                if (!toFillWith.isEmpty()) {
                    toEqual = toFillWith.charAt(0);
                } else {
                    toEqual = 'a';
                }
                char negative = "-".charAt(0);
                boolean isNegative = (toEqual == negative);
                if (toFillWith.equals("f")) {
                    System.out.println("Numerator?");
                    int num = Integer.parseInt(reader.nextLine());
                    System.out.println("Denominator?");
                    int denom = Integer.parseInt(reader.nextLine());
                    matrix[currentRow][currentColumn] = new Fraction(num, denom);
                } else if (isNumbers || isNegative) {
                    matrix[currentRow][currentColumn] = new Fraction(Integer.parseInt(toFillWith));
                }
                else {
                    System.out.println("Wrong input. Try again.");
                    continue;
                }
                currentColumn++;
            }
            currentRow++;
            currentColumn = 0;
        }

    }
}
