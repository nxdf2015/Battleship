package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Field field = new Field();
        System.out.println(field);
        Ship[] ships = new Ship[]{new Ship("Aircraft Carrier", 5)
                ,
                new Ship("Battleship ", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 2)
        };


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

                validPosition = field.addShip(ship);
                if (!validPosition) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                }

            }

            System.out.println(field);
        }

        System.out.println("The game starts!");
        System.out.println(field.fog());
        System.out.println("Take a shot!");
        boolean repeat = true;
        while (repeat) {
            String loc = scanner.next();
            try {
                boolean hit = field.shot(loc);
                System.out.println(field.fog());
                System.out.println(hit ? "You hit a ship!" : "You missed!");
                System.out.println(field);
                repeat = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Error! " + e.getMessage() + "Try again:");
            }
        }

    }
}
