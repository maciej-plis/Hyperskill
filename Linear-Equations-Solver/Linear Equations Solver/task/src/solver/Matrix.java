package solver;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
    Row[] matrix;
    int numberOfRows;
    int numberOfColumns;

    public Matrix(String fileName){
        initFromFile(fileName);
    }

    private void initFromFile(String fileName) {
        try(Scanner scanner = new Scanner(new File(fileName));) {

            numberOfColumns = scanner.nextInt();
            numberOfRows = scanner.nextInt();

            matrix = new Row[numberOfRows];

            for (int i = 0; i < numberOfRows; i++) {
                double[] row = new double[numberOfColumns +1];
                for (int j = 0; j < numberOfColumns + 1; j++) {
                    row[j] = scanner.nextDouble();
                }
                matrix[i] = new Row(row);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    public int getLength() {
        return matrix.length;
    }

    public Row getRow(int rowNumber) {
        return matrix[rowNumber];
    }

    public void setRow(int rowNumber, Row row) {
        matrix[rowNumber] = row;
    }

    public void printMatrix() {
        for(int i = 0; i< numberOfRows; i++) {
            double[] row = matrix[i].getRow();

            for(int j=0; j<row.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
    }

    public boolean containsContradiction()  {
        for(int i=0; i<numberOfRows; i++) {
            boolean onlyZeros = true;

            for(int j=0; j<numberOfColumns; j++) {
                if(Math.abs(matrix[i].getNumber(j)) != 0) {
                    onlyZeros = false;
                    break;
                }
            }

            if(onlyZeros && Math.abs(matrix[i].getNumber(numberOfColumns)) != 0) {
                return true;
            }
        }

        return false;
    }

    public boolean isEmptyRow(int rowNumber) {
        for(int i=0; i<numberOfColumns; i++) {
            if(matrix[rowNumber].getNumber(i) != 0)
                return false;
        }

        return true;
    }

    public void swapRows(int row1Index, int row2Index) {
        Row temp = matrix[row1Index];

        matrix[row1Index] = matrix[row2Index];
        matrix[row2Index] = temp;

        System.out.printf("R%d <-> R%d\n", row1Index+1, row2Index+1);
    }

    public boolean is(double isWhat, int rowNumber, int columnNumber) {
        double value = matrix[rowNumber].getNumber(columnNumber);

        return value == isWhat;
    }

    public void swapColumns(int column1Index, int column2Index) {
        for(int i = 0; i< numberOfRows; i++) {
            double c1Value = matrix[i].getNumber(column1Index);
            double c2Value = matrix[i].getNumber(column2Index);

            matrix[i].setValue(column1Index, c2Value);
            matrix[i].setValue(column2Index, c1Value);
        }
    }

    public int findFirstIndexNotZeroInColumn(int position) {
        for(int i=position; i<numberOfRows; i++) {
            double value = matrix[i].getNumber(position);
            if(value != 0)
                return i;
        }

        return -1;
    }

    public int findFirstIndexNotZeroInRow(int position) {
        for(int i=position; i<numberOfColumns-1; i++) {
            double value = matrix[position].getNumber(i);
            if(value != 0)
                return i;
        }

        return -1;
    }

    public ArrayList<Double> getResults() {
        ArrayList<Double> results = new ArrayList<>();

        for(int i=0; i<matrix.length; i++) {
            if(isEmptyRow(i))
                continue;
            int lastIndex = matrix[i].getLength() - 1;
            results.add(matrix[i].getNumber(lastIndex));
        }

        return results;
    }
}
