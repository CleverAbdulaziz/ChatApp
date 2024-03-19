import uz.pdp.MyFilter;
import uz.pdp.MyFormatter;
import uz.pdp.MyHandler;

import java.io.*;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Registration_Login chatApp = new Registration_Login();
        Scanner scanner = new Scanner(System.in);
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("D:\\Java Goal 3 modul\\ChatApp\\mylogging.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new MyHandler());
        try {
            Handler fileHandler = new FileHandler("D:\\Java Goal 3 modul\\ChatApp\\mylogging.properties", 1000, 10);
            fileHandler.setFormatter(new MyFormatter());
            fileHandler.setFilter(new MyFilter());
            logger.addHandler(fileHandler);
            while (true) {
                System.out.println("1. Register\n2. Login\n3. Exit");
                System.out.println("Enter your choice: ");

                String choice = scanner.next();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case "1":
                        logger.log(Level.OFF,"Enter your email:");
                        String email = scanner.nextLine();
                        chatApp.registerEmail(email);
                        break;
                    case "2":
                        logger.log(Level.OFF,"Enter your email:");
                        email = scanner.nextLine();
                        chatApp.loginEmail(email);
                        System.out.println("Enter Chat");
                        String chat = scanner.nextLine();
                         chatApp.chat1(chat,email);
                        break;
                    case "3":
                        System.exit(0);
                    default:
                        logger.log(Level.OFF,"Invalid choice!");
                }
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}

