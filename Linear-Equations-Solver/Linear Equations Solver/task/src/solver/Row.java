package solver;

public class Row {
    double[] row;

    public Row(double[] row) {
        this.row = row;
    }

    public double getNumber(int i) {
        return row[i];
    }

    public double[] getRow() {
        return row;
    }

    public int getLength() {
        return row.length;
    }

    public void setValue(int i, double value) {
        row[i] = value;
    }

    public static Row multiplyRowBy(Row row, double multiplier) {
        double[] newRow = new  double[row.getLength()];

        for(int i=0; i<row.getLength(); i++) {
            newRow[i] = row.getNumber(i)*multiplier;
        }

        return new Row(newRow);
    }

    public static Row addRows(Row row1, Row row2) {
        double[] newRow = new double[row1.getLength()];

        for(int i=0; i<newRow.length; i++) {
            newRow[i] = row1.getNumber(i) + row2.getNumber(i);
        }

        return new Row(newRow);
    }
}
