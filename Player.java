package battleship;

public class Player {

    private String name;

    private Field field;

    public Player(String name) {
        this.name = name;
        field = new Field();
    }

    public boolean addShip(Ship ship){
        return field.addShip(ship);
    }

    public String fog(){
        return field.fog();
    }

    @Override
    public String toString() {
        return field.toString();
    }

    public String getName() {
        return name;
    }

    public String shot(String loc) {
        return field.shot(loc);
    }

    public boolean sinkAll() {
        return field.sinkAll();
    }
}
