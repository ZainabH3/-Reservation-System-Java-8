package reservationsystem;

import java.util.Scanner;

public class ReservationSystem {

    static Scanner input;
    static int selected;
    static int[] seates;
    static int availableSeats;

    // 0 available, 1 reserved
    public static void main(String[] args) {
        while (true) {
            try {
                if (mainFunction() == 5) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\n\nSomething error, try agin!\n\n\n");
            }
        }

    }

    public static int mainFunction() {
        input = new Scanner(System.in);
        seates = new int[28];

        availableSeats = 0;
        selected = 0;

        welcoming();
        do {
            selection();
            switch (selected) {
                case 1 ->
                    availableSeats();
                case 2 ->
                    displayAllSeats();
                case 3 ->
                    reserveSeat();
                case 4 ->
                    deleteReservation();
            }
            System.out.println("\n\n---------------------------------------------\n\n");
        } while (selected != 5);
        return selected;
    }

    public static void welcoming() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("------------------ Welcome in Reservation System App ------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\n\n\n");
    }

    public static void selection() {
        System.out.println("""
                           1. Display number of available seats. 
                           2. Display seat status for all seats. 
                           3. Reserve seat(s). 
                           4. Delete reservation(s). 
                           5. Exit. 
                           Please select your choice[1, 2, 3, 4 or 5]:""");

        selected = input.nextInt();
        System.out.println("\n\n");
    }

    public static void availableSeats() {
        availableSeats = 0;
        availableSeats = countAvailablesSeats();
        System.out.println("Number of available seates = " + availableSeats);
    }

    public static int countAvailablesSeats() {
        int count = 0;
        for (int a : seates) {
            if (a == 0) {
                count++;
            }
        }
        return count;
    }

    public static void displayAllSeats() {
        for (int i = 0; i < seates.length; i++) {
            if (i % 4 == 0) {
                System.out.println("");
            }

            if (seates[i] == 0) {
                System.out.print("        " + (i + 1) + "        ");
            } else {
                System.out.print("  " + "Reserved" + "        ");
            }
        }
    }

    public static void reserveSeat() {
        displayAllSeats();
        System.out.println(
                "\n\nThere are "
                + countAvailablesSeats()
                + " seats avaialble. Please enter required number of seats: ");
        int countSeatsReserved = input.nextInt();

        if (countSeatsReserved <= countAvailablesSeats()) {
            for (int i = 0; i < countSeatsReserved; i++) {
                System.out.println("\n\nEnter number " + (i + 1) + " seated you need resreved:");
                int numberSeat = input.nextInt();
                if (numberSeat > 0 && numberSeat < 29) {
                    if (seates[numberSeat - 1] == 1) {
                        i--;
                        System.out.println("\nSeat are researved, try again.");
                    } else {
                        seates[numberSeat - 1] = 1;
                        System.out.println("\nSeat researved successfully.");
                    }
                } else {
                    i--;
                    System.out.println("\nError, invalid number!");
                }

            }
            System.out.println("\n\n" + countSeatsReserved + " seats have been successfully reserved.");
        } else {
            System.out.println("Error: Please enter number seats less than or equal count available seats.");
        }

    }

    public static void deleteReservation() {
        if (countAvailablesSeats() != 28) {
            displayAllSeats();
            int researvedSeats = 28 - countAvailablesSeats();
            System.out.println(
                    "\n\nThere are "
                    + researvedSeats
                    + " seats researved. Please enter required number of seats delete researve: ");
            int countSeatsDelteReserved = input.nextInt();

            if (countSeatsDelteReserved <= researvedSeats) {
                for (int i = 0; i < countSeatsDelteReserved; i++) {
                    System.out.println("\n\nEnter number " + (i + 1) + " seated you need delete resreved:");
                    int numberSeat = input.nextInt();
                    if (numberSeat > 0 && numberSeat < 29) {
                        if (seates[numberSeat - 1] == 0) {
                            i--;
                            System.out.println("\nSeat are not researved, try again.");
                        } else {
                            seates[numberSeat - 1] = 0;
                            System.out.println("\nSeat delete researved successfully.");
                        }
                    } else {
                        i--;
                        System.out.println("\nError, invalid number!");
                    }
                }
            } else {
                System.out.println("Error: Please enter number seats less than or equal count researved seats.");
            }

        } else {
            System.out.println("Not exist any seat researved");
        }
    }
}
