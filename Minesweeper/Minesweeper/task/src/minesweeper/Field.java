package minesweeper;

public class Field {
    private static final char MARKED_SIGN = '*';
    private static final char HIDDEN_SIGN = '.';
    private static final char BOMB_SIGN = 'X';
    private static final char EMPTY_SIGN = '/';

    private FieldStates state = FieldStates.EMPTY;

    private boolean hidden = true;
    private boolean marked = false;
    private boolean safe = false;

    private int bombsAround = 0;

    public Field() {}

    public void setAsBomb() {
        state = FieldStates.BOMB;
    }

    public boolean isBomb(){
        return state == FieldStates.BOMB;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isEmpty() {
        return state == FieldStates.EMPTY;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setAsSafe() { safe = true; }

    public void addBombAround() {
        state = FieldStates.NUMBER;
        bombsAround++;
    }

    public void toggleMark() {
        marked = !marked;
    }

    public void reveal() {
        hidden = false;
    }

    public char getValue() {
        if(hidden) {
            return marked ? MARKED_SIGN : HIDDEN_SIGN;
        }

        switch(state) {
            case NUMBER: return Integer.toString(bombsAround).charAt(0);
            case BOMB: return BOMB_SIGN;
            case EMPTY:
            default: return EMPTY_SIGN;
        }
    }

}
