package battleship;


import java.util.ArrayList;
import java.util.List;

public class Ship {

    private Position locStart;
    private Position locEnd;
    private int size;
    private String name;

    public Ship(String name, int size){
        this.size = size;
        this.name = name;
    }

    public Ship(Position locStart, Position locEnd) {

        this.locStart = locStart;
        this.locEnd = locEnd;
        order();
    }

    public Ship() {
    }

    public Ship(String locStart, String locEnd) {
        this(new Position(locStart), new Position(locEnd));
    }

    public int getSize() {
        return this.size;
    }

    public   String getName(){
        return  name;
    }

    public void order() {
        if (locStart.getCol() > locEnd.getCol() || locStart.getRow() > locEnd.getRow()) {
            Position tmp = locStart;
            locStart = locEnd;
            locEnd = tmp;
        }
    }

    private void setLocStart(Position locStart) {
        this.locStart = locStart;
    }

    private void setLocEnd(Position locEnd) {
        this.locEnd = locEnd;
    }

    public void setPosition(String[] p) throws IllegalArgumentException{
        this.locStart = new Position(p[0]);
        this.locEnd = new Position(p[1]);
        if (!(isHorizontal() || isVertical())) {
            throw new IllegalArgumentException("wrong ship location position!");
        }
        if (locStart.distanceTo(locEnd) !=   getSize() ){
            throw new IllegalArgumentException("Wrong length of " + getName() + "! ");
        }
        order();
    }

    public boolean isHorizontal() {
        return locStart.getRow() == locEnd.getRow();
    }

    public boolean isVertical() {
        return locStart.getCol() == locEnd.getCol();
    }

    public List<Position> getCoordinates() {
        List<Position> coordinates = new ArrayList<>();
        if (isHorizontal()) {
            Position current = locStart;
            for (int i = locStart.getCol(); i <= locEnd.getCol(); i++) {
                coordinates.add(current);
                current = current.right();
            }
        } else {
            Position current = locStart;
            for (int i = locStart.getRow(); i <= locEnd.getRow(); i++) {
                coordinates.add(current);
                current = current.up();
            }
        }
        return coordinates;
    }


    public List<Position> getNeighboors() {
        List<Position> coordinates = new ArrayList<>();

        if (isHorizontal()) {
            Position p;
            p = locStart.left();
            if (p.validPosition()) {
                coordinates.add(p);
            }
            p = locEnd.right();
            if (p.validPosition()){
                coordinates.add(p);
            }
        } else {
            Position p;
            p = locStart.down();
            if (p.validPosition()) {
                coordinates.add(p);
            }
            p =locEnd.up();
            if (p.validPosition()) {
                coordinates.add(p);
            }

        }

        for(Position current : getCoordinates()) {
            Position p;
            if(isHorizontal()) {

                p = current.up();
                if (p.validPosition()) {
                    coordinates.add(p);
                }

                p = current.down();
                if (p.validPosition()) {
                    coordinates.add(p);
                }

            } else {
                p = current.left();
                if (p.validPosition()) {
                    coordinates.add(p);
                }
                p = current.right();
                if (p.validPosition()) {
                    coordinates.add(p);
                }

            }
        }
        return coordinates;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "locStart=" + locStart +
                ", locEnd=" + locEnd +
                '}';
    }

}
