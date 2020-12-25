package battleship;

import java.util.Scanner;

public class Game {

    private Player[] players;
    private Scanner scanner;
    int id;
    public Game(Player... players) {
        this.players = players;
        this.scanner = new Scanner(System.in);
        this.id = 0;
    }

    public void initializeGame(){
        for(Player player : players){
            initializeField(player);
            System.out.println("Press Enter and pass the move to another player\n...");
            scanner.nextLine();
        }
    }

    private void nextPlayer(){
        id = (id + 1) % 2;
    }

    private void show(){
        System.out.println(otherPlayer().fog());
        System.out.println("---------------------");
        System.out.println(currentPlayer());


    }
    private Player currentPlayer(){
        return players[id];
    }

    private Player otherPlayer(){
        return players[(id +1) % 2];
    }

    public void start() {

        boolean finish = false;

        while (!finish) {

                show();
                boolean repeat = true;
                System.out.println(currentPlayer().getName() + ", it's your turn:");
                while (repeat) {
                    System.out.println();
                    String loc = scanner.nextLine();
                    try {
                        String result = otherPlayer().shot(loc);
                        finish = otherPlayer().sinkAll();
                        System.out.println(result);
                        repeat = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error! " + e.getMessage() + "Try again:");
                    }
                }

                System.out.println("Press Enter and pass the move to another player\n...\n");
                scanner.nextLine();
                nextPlayer();

        }
    }
    private void initializeField(Player player) {
        Ship[] ships = new Ship[]{new Ship("Aircraft Carrier", 5),
                new Ship("Battleship ", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 2)
        };
        System.out.println(player.getName() + ", place your ships on the game field");
        System.out.println(player);
        for (int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            boolean validPosition = false;
            System.out.println("Enter the coordinates of the " + ship.getName());
            while (!validPosition) {

                boolean repeat = true;
                while (repeat) {
                    String[] positions = scanner.nextLine().split(" ");
                    try {
                        ship.setPosition(positions);
                        repeat = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error! " + e.getMessage() + "Try again :");
                    }
                }

                validPosition = player.addShip(ship);
                if (!validPosition) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                }

            }

            System.out.println(player);
        }

    }

}
