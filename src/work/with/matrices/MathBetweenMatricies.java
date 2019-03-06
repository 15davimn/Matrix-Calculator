//Takes care of some simple operations, like addition and multiplication. 

package work.with.matrices;

import java.util.Scanner;

public class MathBetweenMatricies {

    public static Matrix addMatrices(Matrix matrix1, Matrix matrix2, Scanner reader) {
        if (!(matrix1.getNumberOfColumns() == matrix2.getNumberOfColumns() && matrix2.getNumberOfRows() == matrix1.getNumberOfRows())) {
            System.out.println("Cannot add the two matrices. They are not the right dimensions.");
            return null;
        }
        Matrix newMatrix = new Matrix(matrix1.getNumberOfRows(), matrix1.getNumberOfColumns(), reader);
        for (int row = 0; row < matrix1.getNumberOfRows(); row++) {
            for (int column = 0; column < matrix1.getNumberOfColumns(); column++) {
                Fraction number = new Fraction(0, 0);
                number.addFraction(matrix1.getNumber(row, column));
                number.addFraction(matrix2.getNumber(row, column));
                newMatrix.setNumber(row, column, number);
            }
        }

        return newMatrix;
    }

    public static Matrix multipleTwoMatricies(Matrix matrix1, Matrix matrix2, Scanner reader) {
        if (!(matrix1.getNumberOfColumns() == matrix2.getNumberOfRows())) {
            System.out.println("Cannot multiply these two matrices. They are not the right dimensions.");
            return null;
        }

        Matrix newMatrix = new Matrix(matrix1.getNumberOfRows(), matrix2.getNumberOfColumns(), reader);
        for (int row = 0; row < matrix1.getNumberOfRows(); row++) {
            for (int column = 0; column < matrix2.getNumberOfColumns(); column++) {
                Fraction number = new Fraction(0, 0);
                for (int i = 0; i < matrix1.getNumberOfColumns(); i++) {
                    Fraction toAdd = new Fraction (matrix1.getNumber(row, i).getNum(),
                            matrix1.getNumber(row, i).getDenom());
                    toAdd.multiply(matrix2.getNumber(i, column));
                    number.addFraction(toAdd);
                }
                newMatrix.setNumber(row, column, number);
            }
        }
        return newMatrix;
    }

}
