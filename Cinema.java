package cinema;

import java.util.Scanner;

public class Cinema {
    static int purchasedTickets = 0;
    static double percentagePurchased = 0;
    static int currentIncome = 0;
    static int totalIncome;
    static int rows;
    static int seatsInRow;


    public static void showSeats(char[][] cinema) {
        System.out.println("Cinema:");
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.printf("%c ", cinema[i][j]);
            }
            System.out.println();
        }
    }

    public static void buyTicket(char[][] cinema) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int userRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int userSeat = scanner.nextInt();
        if (userRow > rows || userSeat > seatsInRow) {
            System.out.println("Wrong input!");
            buyTicket(cinema);
            return;
        } else if (cinema[userRow][userSeat] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(cinema);
            return;
        } else {
            int ticketPrice = 10;
            if ((cinema.length - 1) * (cinema[0].length - 1) > 60 && userRow > (cinema.length - 1) / 2) {
                ticketPrice = 8;
            }
            System.out.printf("Ticket price: $%d%n", ticketPrice);
            cinema[userRow][userSeat] = 'B';
            purchasedTickets++;
            percentagePurchased = (double) 100 / (rows * seatsInRow) * purchasedTickets;
            currentIncome += ticketPrice;
        }
    }

    public static void statistics() {
        System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentagePurchased);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    public static void menu(char[][] cinema) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n");
        switch (scanner.nextInt()) {
            case 1:
                showSeats(cinema);
                menu(cinema);
                break;
            case 2:
                buyTicket(cinema);
                menu(cinema);
                break;
            case 3:
                statistics();
                menu(cinema);
                break;
            case 0:
                break;
            default:
                menu(cinema);
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsInRow = scanner.nextInt();
        if (rows * seatsInRow > 60) {
            totalIncome = rows / 2 * seatsInRow * 10 + (rows / 2 + rows % 2) * seatsInRow * 8;
        } else {
            totalIncome = rows * seatsInRow * 10;
        }
        char[][] cinema = new char[rows + 1][seatsInRow + 1];
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seatsInRow + 1; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = ' ';
                } else if (i == 0 || j == 0) {
                    cinema[i][j] = Character.forDigit(i + j, 10);
                } else {
                    cinema[i][j] = 'S';
                }
            }
        }
        menu(cinema);
    }
}