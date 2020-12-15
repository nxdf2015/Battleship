package battleship;

public enum Status {
    FREE("~"),
    HIT("X"),
    SHIP("O"),
    MISS("M");
    private String label;
    private Status(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
