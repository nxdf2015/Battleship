package battleship;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Field {
    Status[][]  field;
    private Position position;
    private int countShip;
    private List<Ship> ships;

    public Field() {
        this.field = new Status[10][10];
        this.ships = new ArrayList<>();
        countShip = 0;
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
         countShip++;
        for(Position p : ship.getCoordinates()){

            field[p.getRow()][p.getCol()] = Status.SHIP;
            ships.add(ship);

        }
        return true;
     }

     public boolean sinkAll() {
        return countShip == 0;
     }

     public String shot(String loc) throws IllegalArgumentException {
          Position position = new Position(loc);

          if (!position.validPosition()) {
              throw new IllegalArgumentException("You entered the wrong coordinates! ");
          }

         Status status = getStatus(position);
         String message = "";
          if (status == Status.FREE) {
              status = Status.MISS;
              message =  "You missed. Try again:";

          }
          if (status == Status.SHIP) {

              Ship ship = findShip(position);
              ship.addShot();
              status = Status.HIT;
              boolean sink = ship.isSink();
              if (sink) {
                  countShip--;
              }

              if (countShip == 0) {
                  message =  "You sank the last ship. You won. Congratulations!";
              }
              else if (sink) {
                  message =  "you sank a ship! specify a new target:";
              }
              else {
                  message =  "You hit a ship! Try again:";
              }

          }

          changeStatus(position, status);
          return message;

     }

    public Ship findShip(Position position) {
        return ships.stream().filter( ship -> ship.getCoordinates().contains(position)).collect(Collectors.toList()).get(0);
    }

    private Status getStatus(Position position) {
        return field[position.getRow()][position.getCol()];
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
