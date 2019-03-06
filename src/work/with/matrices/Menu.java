//This is the menu that the user is presented with. 

package work.with.matrices;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {

    private HashMap<String, Matrix> matrices;
    private Scanner reader;

    public Menu() {
        this.matrices = new HashMap<String, Matrix>();
        this.reader = new Scanner(System.in);
        System.out.println("Welcome to the matrix calculator!");

    }

    public String setName() {
        while (true) {

            String name = reader.nextLine();
            while (matrices.containsKey(name)) {
                    System.out.println("There is already a matrix with this name. Replace old matrix with new one? (Y/N)");
                    String command = reader.nextLine();
                    if (command.equalsIgnoreCase("Y")) {
                        return name;
                    }
                    System.out.println("Try another name.");
                    name = reader.nextLine();
            }
            return name;
        }

    }

    public Matrix getMatrix() {
        System.out.println("Which matrix?");
        String matrixToSummon = reader.nextLine();
        if (matrices.containsKey(matrixToSummon)) {
            Matrix summonedMatrix = matrices.get(matrixToSummon);
            return summonedMatrix;
        } else {
            System.out.println("The matrix is not found.");
            return null;
        }
    }

    public void displayAllMatricies() {
        for (String fractionName : matrices.keySet()) {
            System.out.println("Matrix " + fractionName);
            matrices.get(fractionName).displayMatrix();
        }
    }

    public void startMenu() {
        while (true) {
            System.out.println("The following options are available:\n"
                    + "1 - Make a new matrix\n"
                    + "2 - Display a matrix\n"
                    + "3 - Display all matrices\n"
                    + "4 - Find reduced row echelon form\n"
                    + "5 - Add two matrices\n"
                    + "6 - Multiply two matrices\n"
                    + "7 - Find the inverse\n"
                    + "quit - ends the program");
            String command = reader.nextLine();
            if (command.equals("1")) {
                System.out.println("What do you want to call this matrix?");
                String matrixName = setName();
                Matrix newMatrix = new Matrix(reader);
                matrices.put(matrixName, newMatrix);
            } else if (command.equals("quit")) {
                break;
            } else if (command.equals("2")) {
                Matrix summonedMatrix = getMatrix();
                if (!(summonedMatrix == null)) {
                    summonedMatrix.displayMatrix();
                }
            } else if (command.equals("3")) {
                displayAllMatricies();
            } else if (command.equals("4")) {
                Matrix matrixToUse = getMatrix();
                if (!(matrixToUse == null)) {
                    RowReduce reduce = new RowReduce(matrixToUse);
                    System.out.println("Done! Here is the new matrix.");
                    matrixToUse.displayMatrix();
                }

            } else if (command.equals("5")) {
                System.out.println("You need to pick the two matrices you want to add together.");
                Matrix matrix1 = getMatrix();
                System.out.println("And?");
                Matrix matrix2 = getMatrix();
                if (matrix1 == null || matrix2 == null) {
                    System.out.println("Incorrect input.");
                    continue;
                }
                Matrix newMatrix = MathBetweenMatricies.addMatrices(matrix1, matrix2, reader);
                if (newMatrix == null) {
                    continue;
                }
                System.out.println("What do you want to call the new matrix?");
                String name = setName();
                matrices.put(name, newMatrix);
                System.out.println("You made the following matrix:");
                newMatrix.displayMatrix();
            } else if (command.equals("6")) {
                System.out.println("Enter the matrices you want to multiply.");
                Matrix matrix1 = getMatrix();
                Matrix matrix2 = getMatrix();
                if (matrix1 == null || matrix2 == null) {
                    System.out.println("Incorrect input.");
                    continue;
                }
                Matrix newMatrix = MathBetweenMatricies.multipleTwoMatricies(matrix1, matrix2, reader);
                if (newMatrix == null) {
                    continue;
                }
                System.out.println("You made the following matrix:");
                newMatrix.displayMatrix();
                System.out.println("What would you like to call this matrix?");
                String name = setName();
                matrices.put(name, newMatrix);
            } else if (command.equals("7")) {
                Inverse invert;
                Matrix summoned = getMatrix();
                if (summoned == null) {
                    System.out.println("Incorrect input.");
                    continue;
                }
                if (!(summoned.getNumberOfColumns() == summoned.getNumberOfRows())) {
                    System.out.println("Matrix is not a sqaure matrix. It cannot have an inverse.");
                    continue;
                } else {
                    invert = new Inverse(summoned, reader);
                }
                Matrix inverted = invert.invert();
                if (inverted == null) {
                    continue;
                }
                System.out.println("Done! Here is the new matrix.");
                inverted.displayMatrix();
                System.out.println("What would you like to call this matrix?");
                String name = reader.nextLine();
                matrices.put(name, inverted);
            }
            System.out.println("Type a new command.");
        }
        System.out.println("Thank you. Have a nice day!");
    }

}
