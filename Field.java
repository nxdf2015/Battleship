package battleship;

import java.util.Arrays;
import java.util.List;

public class Field {
    boolean[][]  field;
    private Position position;
    public Field() {
        this.field = new boolean[10][10];

    }



     public boolean addShip(Ship ship) {


        List<Position> coordinates = ship.getNeighboors();
        coordinates.addAll(ship.getCoordinates());
        for(Position  p : coordinates){

            if (!isFree(p)){
                return false;
            }
        }

        for(Position p : ship.getCoordinates()){

            field[p.getRow()][p.getCol()] = true;
        }
        return true;
     }

     public boolean isFree(Position p){
        return !field[p.getRow()][p.getCol()] ;
     }


    @Override
    public String toString() {
        String result = "  1 2 3 4 5 6 7 8 9 10\n";

        for(int i = 0; i < 10; i++) {
            String line = "";
            for(int j = 0; j < 10; j++){
                line += (field[i][j] ? "o" : "~") + " ";
            }
            result += ((char) ('A' + i)) + " " + line+ "\n";
        }
        return result;
    }
}
