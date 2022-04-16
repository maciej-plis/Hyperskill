package solver;

import java.util.ArrayList;

public class LinearEquation {
    Matrix matrix;
    boolean infinite = false;
    boolean impossible = false;


    public LinearEquation(Matrix matrix) {
        this.matrix = matrix;
    }

    public ArrayList<Double> solve() {
        matrix.printMatrix();
        System.out.println("Start solving the equation.");

//        if(matrix.numberOfColumns > matrix.numberOfRows) {
//            infinite = true;
//            return null;
//        }

        System.out.println("Rows manipulation:");
        for(int index = 0; index<matrix.getNumberOfColumns(); index++) {
            int pivotPos = index;

            if(pivotIsZero(pivotPos)) {
                if(trySwap(pivotPos) == false) {
                    if(matrix.containsContradiction())
                        impossible = true;
                    infinite = true;
                    return null;
                }
            }

            if(!matrix.is(1, pivotPos, pivotPos))
                changeForOne(pivotPos);

            for(int rowNumber = 0; rowNumber<matrix.getNumberOfRows(); rowNumber++) {
                if(rowNumber == pivotPos)
                    continue;
                if(!matrix.is(0, rowNumber, index))
                    changeForZero(rowNumber, index);
            }
        }
        matrix.printMatrix();
        if(matrix.containsContradiction()) {
            impossible = true;
            return null;
        }

        return matrix.getResults();
    }

    private boolean pivotIsZero(int pivotPos) {
        return matrix.getRow(pivotPos).getNumber(pivotPos) == 0;
    }

    private boolean trySwap(int pivotPos) {
        int toSwap = lookDown(pivotPos);
        if(toSwap != -1) {
            swapRow(pivotPos, toSwap);
            return true;
        }
        toSwap = lookRight(pivotPos);
        if(toSwap != -1) {
            swapColumn(pivotPos, toSwap);
            return true;
        }

        if(checkLeftCorner()) {
            swapRow(pivotPos, matrix.numberOfRows-1);
            swapColumn(pivotPos,0);
            return true;
        }
        return false;
    }

    public int lookDown(int pivotPos) {
        return matrix.findFirstIndexNotZeroInColumn(pivotPos);
    }

    public int lookRight(int pivotPos) {
        return matrix.findFirstIndexNotZeroInRow(pivotPos);
    }

    public String specialCase() {
        if(impossible)
            return "No solutions";
        else
            return "Infinitely many solutions";
    }

    public boolean checkLeftCorner() {
        double value = matrix.getRow(matrix.getLength()-1).getNumber(0);

        return value != 0 ? true : false;
    }

    private void swapRow(int pivotPos, int toSwap) {
        matrix.swapRows(pivotPos, toSwap);
    }

    private void swapColumn(int pivotPos, int toSwap) {
        matrix.swapColumns(pivotPos, toSwap);
    }

    private void changeForOne(int pivotPos) {
        Row rowToModify = matrix.getRow(pivotPos);

        double multiplier = 1 / rowToModify.getNumber(pivotPos);

        Row modifiedRow = Row.multiplyRowBy(rowToModify, multiplier);

        matrix.setRow(pivotPos, modifiedRow);

        System.out.printf("%f * R%d -> R%d\n", multiplier, pivotPos+1, pivotPos+1);
    }

    private void changeForZero(int rowNumber, int index) {
        Row pivotRow = matrix.getRow(index);
        Row rowToModify = matrix.getRow(rowNumber);

        double multiplier = rowToModify.getNumber(index) * -1;
        Row toAdd = Row.multiplyRowBy(pivotRow, multiplier);
        Row modifiedRow = Row.addRows(rowToModify, toAdd);

        matrix.setRow(rowNumber, modifiedRow);

        System.out.printf("%f * R%d + R%d -> R%d\n", multiplier, index+1, rowNumber+1, rowNumber+1);
    }
}
