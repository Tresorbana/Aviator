import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class AviatorGame {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        double balance = 100.0;
        boolean isRunning = true;

        System.out.println("✈ Welcome to Aviator!");
        System.out.println("Cash out before the plane crashes!");

        while (isRunning) {
            System.out.println("\n💰 Your balance: $" + balance);
            System.out.println("1️⃣ Play");
            System.out.println("2️⃣ Quit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    if (balance <= 0) {
                        System.out.println("❌ You have no money left! Please deposit to continue.");
                        break;
                    }

                    System.out.print("Enter your bet: $");
                    double bet = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    if (bet <= 0 || bet > balance) {
                        System.out.println("⚠ Invalid bet amount!");
                        break;
                    }

                    balance -= bet;

                    double multiplier = 1.0;
                    double crashPoint = 1.0 + random.nextDouble() * 5.0;
                    boolean cashedOut = false;

                    System.out.println("🚀 The plane is taking off! Press ENTER to cash out!");

                    while (multiplier < crashPoint) {
                        multiplier += 0.1;
                        System.out.printf("Multiplier: %.2fx\r", multiplier);
                        Thread.sleep(500);

                        if (System.in.available() > 0) {
                            scanner.nextLine(); // consume ENTER
                            cashedOut = true;
                            double winnings = bet * multiplier;
                            balance += winnings;
                            System.out.printf("\n✅ You cashed out at %.2fx! You won $%.2f%n", multiplier, winnings);
                            break;
                        }
                    }

                    if (!cashedOut) {
                        System.out.printf("\n💥 The plane crashed at %.2fx! You lost $%.2f%n", crashPoint, bet);
                    }
                }

                case 2 -> {
                    System.out.println("👋 Thanks for playing! You leave with $" + balance);
                    isRunning = false;
                }

                default -> System.out.println("⚠ Please choose a valid option.");
            }
        }

        scanner.close();
    }
}
