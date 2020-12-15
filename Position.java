package battleship;

public class Position {
    private int  row;
    private int col;

    public Position(String position) {

        row =  position.charAt(0) - 'A';
        col =  Integer.parseInt( position.substring(1)) - 1;
    }

    public Position(int row, int col)   {
        this.row = row;
        this.col = col ;
    }

    public int  getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Position move(int drow, int dcol) {
        return  new Position(row + drow, col + dcol);
    }

    private boolean valid(int x) {
        return x >= 0 && x < 10;
    }


    public boolean validPosition(){
        return valid(row) && valid(col);
    }

    public Position left() {
        return move(0,-1);
    }

    public Position right() {
        return move(0, 1);
    }

    public Position up() {
        return move(1, 0);
    }

    public Position down() {
        return move(-1, 0);
    }

    public int distanceTo(Position p) {
        return Math.abs((p.getRow() - row ) + (p.getCol() - col)) + 1;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
