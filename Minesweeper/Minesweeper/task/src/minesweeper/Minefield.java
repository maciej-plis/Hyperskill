package minesweeper;

import java.util.Random;

public class Minefield {

    private final int MINEFIELD_SIZE = 9;
    private Field[][] fields;

    private int amountOfMines;
    private  boolean minesGenerated = false;

    public Minefield(int amountOfMines) {
        fields = new Field[MINEFIELD_SIZE][MINEFIELD_SIZE];
        this.amountOfMines = amountOfMines;

        initFields();
    }

    public void initFields() {
        for (int i = 0; i < MINEFIELD_SIZE; i++) {
            for (int j = 0; j < MINEFIELD_SIZE; j++) {
                fields[i][j] = new Field();
            }
        }
    }

    private void generateRandomMines(int howMany) {
        Random rand = new Random();

        for(int i=0; i<howMany; i++) {
            int mineX;
            int mineY;

            do {
                mineX = rand.nextInt(MINEFIELD_SIZE);
                mineY = rand.nextInt(MINEFIELD_SIZE);
            } while( fields[mineX][mineY].isBomb() || fields[mineX][mineY].isSafe() );

            fields[mineX][mineY].setAsBomb();

            fillCellsAround(mineX, mineY);
        }
    }

    public boolean areMinesGenerated() {
        return minesGenerated;
    }

    private void fillCellsAround(int x, int y) {
        int xStart = x-1 < 0 ? x : x-1;
        int xEnd = x+1 >= MINEFIELD_SIZE ? x : x+1;

        int yStart = y-1 < 0 ? y : y-1;
        int yEnd = y+1 >= MINEFIELD_SIZE ? y : y+1;

        for(int i=xStart; i<=xEnd; i++) {
            for(int j=yStart; j<=yEnd; j++) {
                if(fields[i][j].isBomb()) {
                    continue;
                } else {
                    fields[i][j].addBombAround();
                }
            }
        }
    }

    public void printMinefield() {
        System.out.print("\n");
        System.out.println(" |123456789|\n" +
                "-|---------|");
        for(int i=0; i< MINEFIELD_SIZE; i++) {
            System.out.print(i+1 + "|");
            for(int j=0; j< MINEFIELD_SIZE; j++) {
                System.out.print(fields[i][j].getValue());
            }
            System.out.print("|\n");
        }
        System.out.println("-|---------|");
    }

    public boolean isMarkable(int x, int y) {
        return fields[x][y].isHidden();
    }

    public boolean isBomb(int x, int y) {
        return fields[x][y].isBomb();
    }

    public void revealAllConnectedEmpty(int x, int y) {
        if(!minesGenerated) {
            setFieldsAroundAsSafe(x, y);
            generateRandomMines(amountOfMines);
            minesGenerated = true;
        }

        int xStart = x-1 < 0 ? x : x-1;
        int xEnd = x+1 >= MINEFIELD_SIZE ? x : x+1;

        int yStart = y-1 < 0 ? y : y-1;
        int yEnd = y+1 >= MINEFIELD_SIZE ? y : y+1;

        fields[x][y].reveal();

        if(!fields[x][y].isEmpty())
            return;

        for(int i=xStart; i<=xEnd; i++) {
            for(int j=yStart; j<=yEnd; j++) {
                if( fields[i][j].isHidden() ) {
                    revealAllConnectedEmpty(i, j);
                }
            }
        }
    }

    public void setFieldsAroundAsSafe(int x, int y) {
        int xStart = x-1 < 0 ? x : x-1;
        int xEnd = x+1 >= MINEFIELD_SIZE ? x : x+1;

        int yStart = y-1 < 0 ? y : y-1;
        int yEnd = y+1 >= MINEFIELD_SIZE ? y : y+1;

        for(int i=xStart; i<=xEnd; i++) {
            for(int j=yStart; j<=yEnd; j++) {
                fields[i][j].setAsSafe();
            }
        }
    }

    public void mark(int x, int y) {
        fields[x][y].toggleMark();
    }

    public boolean allBombsAreMarked() {
        for (int i = 0; i < MINEFIELD_SIZE; i++) {
            for (int j = 0; j < MINEFIELD_SIZE; j++) {
                Field field = fields[i][j];

                if(field.isMarked() && !field.isBomb()) {
                    return false;
                } else if(field.isBomb() && !field.isMarked()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean onlyBombsAreHidden() {
        for (int i = 0; i < MINEFIELD_SIZE; i++) {
            for (int j = 0; j < MINEFIELD_SIZE; j++) {
                Field field = fields[i][j];

                if(!field.isBomb() && field.isHidden()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void revealAllMines() {
        for (int i = 0; i < MINEFIELD_SIZE; i++) {
            for (int j = 0; j < MINEFIELD_SIZE; j++) {
                if(fields[i][j].isBomb()) {
                    fields[i][j].reveal();
                }
            }
        }
    }

}
