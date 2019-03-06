//Handles gauss jordan elimination. 

package work.with.matrices;

import java.util.Arrays;

public class RowReduce {

    private Matrix matrix;
    private int numberOfRows;
    private int numberOfColumns;

    public RowReduce(Matrix matrix) {
        this.matrix = matrix;
        this.numberOfColumns = matrix.getNumberOfColumns();
        this.numberOfRows = matrix.getNumberOfRows();
        rowReduce();
    }
    
    //Makes all the numbers below/above it zero.
    public void make0(int rowWorkingOn, int tempRowWorkingOn, int columnWorkingOn) {
        Fraction[] arrayToAdd = new Fraction[numberOfColumns];
        Fraction toMultiplyBy = matrix.getNumber(tempRowWorkingOn, columnWorkingOn);
        toMultiplyBy = new Fraction(toMultiplyBy.getNum(), toMultiplyBy.getDenom());
        toMultiplyBy.divide(matrix.getNumber(rowWorkingOn, columnWorkingOn));
        for (int i = 0; i < numberOfColumns; i++) {
            Fraction toCopy = matrix.getNumber(rowWorkingOn, i);
            arrayToAdd[i] = new Fraction(toCopy.getNum(), toCopy.getDenom());
            arrayToAdd[i].multiply(toMultiplyBy);
            arrayToAdd[i].multiply(-1);
        }
        matrix.addAnArray(arrayToAdd, tempRowWorkingOn);
    }

    //Main logic for the class.
    public void rowReduce() {
        int rowWorkingOn = 0;
        int columnWorkingOn = 0;
        while (rowWorkingOn < numberOfRows && columnWorkingOn < numberOfColumns) {
            pivotRow(rowWorkingOn, columnWorkingOn);
            if (matrix.getNumber(rowWorkingOn, columnWorkingOn).getNum() == 0) {
                columnWorkingOn++;
                continue;
            }
            make1(rowWorkingOn, columnWorkingOn);
            for (int tempRowWorkingOn = rowWorkingOn + 1; tempRowWorkingOn < numberOfRows; tempRowWorkingOn++) {
                make0(rowWorkingOn, tempRowWorkingOn, columnWorkingOn);
            }
            rowWorkingOn++;
            columnWorkingOn++;
        }
        columnWorkingOn = numberOfColumns - 1;
        while (columnWorkingOn >= 0) {
            rowWorkingOn = findLeading1(columnWorkingOn);
            int tempRowWorkingOn = rowWorkingOn - 1;
            while (tempRowWorkingOn >= 0) {
                make0(rowWorkingOn, tempRowWorkingOn, columnWorkingOn);
                tempRowWorkingOn--;
            }
            columnWorkingOn--;
        }
    }

    public int findLeading1(int columnWorkingOn) {
        int currentRow = numberOfRows - 1;
        while (currentRow >= 0) {
            if (matrix.getNumber(currentRow, columnWorkingOn).getDenom() == 1 && matrix.getNumber(currentRow, columnWorkingOn).getNum() == 1) {
                return currentRow;
            } else {
                currentRow--;
            }
        }
        return -1;
    }

    public void make1(int rowWorkingOn, int columnWorkingOn) {
        Fraction workingWith = matrix.getNumber(rowWorkingOn, columnWorkingOn);
        Fraction toMultiplyBy = new Fraction(workingWith.getDenom(), workingWith.getNum());
        matrix.multiplyARow(toMultiplyBy, rowWorkingOn);
    }

    public void pivotRow(int rowWorkingOn, int columnWorkingOn) {
        if (matrix.getNumber(rowWorkingOn, columnWorkingOn).getNum() == 0) {
            int i = rowWorkingOn;
            while (i < this.numberOfRows) {
                if (!(matrix.getNumber(i, columnWorkingOn).getNum() == 0)) {
                    matrix.swapRows(i, rowWorkingOn);
                    break;
                }
                i++;
            }

        }

    }

}
