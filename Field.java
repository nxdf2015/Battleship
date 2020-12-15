package battleship;

import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class Field {
    Status[][]  field;
    private Position position;
    public Field() {
        this.field = new Status[10][10];
        initialize();
    }

    private void initialize() {
        for (int i = 0 ; i < 10; i++ ){
            Arrays.fill(field[i], Status.FREE);
        }
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

            field[p.getRow()][p.getCol()] = Status.SHIP;
        }
        return true;
     }

     public boolean shot(String loc) throws IllegalArgumentException {
          Position position = new Position(loc);

          if (!position.validPosition()) {
              throw new IllegalArgumentException("You entered the wrong coordinates! ");
          }
          boolean free = isFree(position);
          if (!free) {
               changeStatus(position, Status.HIT);
          } else {
              changeStatus(position, Status.MISS);
          }
          return !free;
     }


     public void changeStatus (Position position, Status status) {
        field[position.getRow()][position.getCol()] = status;
     }

     public boolean isFree(Position p){
        return field[p.getRow()][p.getCol()] == Status.FREE;
     }

   public String fog(){
       String result = "  1 2 3 4 5 6 7 8 9 10\n";
       for(int i = 0; i < 10; i++) {
           String line = "";
           for(int j = 0; j < 10; j++){
               Status status = field[i][j];
               line +=  (status  == Status.SHIP ? Status.FREE : status) + " ";
           }
           result += ((char) ('A' + i)) + " " + line+ "\n";
       }
       return result;
   }
    @Override
    public String toString() {
        String result = "  1 2 3 4 5 6 7 8 9 10\n";
        for(int i = 0; i < 10; i++) {
            String line = "";
            for(int j = 0; j < 10; j++){
              line += field[i][j] + " ";
            }
            result += ((char) ('A' + i)) + " " + line+ "\n";
        }
        return result;
    }
}
