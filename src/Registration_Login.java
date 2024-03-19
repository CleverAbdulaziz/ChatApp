import uz.pdp.MyFilter;
import uz.pdp.MyFormatter;
import uz.pdp.MyHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.*;
import java.util.regex.Pattern;

public class Registration_Login {
    private static final String FILE_PATH = "user_email.txt";
    public void registerEmail(String email) {
        Set<String> emails = loadEmails();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format! " + email);
            return;
        }
        if (emails.contains(email)) {
            System.out.println("This email already exists.");
        } else {
            try (FileWriter fw = new FileWriter(FILE_PATH, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(email);
                System.out.println("Email registered successfully.");

            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }
    }
    public static boolean loginEmail(String email) {
        Set<String> emails = loadEmails();

        if (emails.contains(email)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Email not found.");
            return false;
        }
    }
    private static Set<String> loadEmails() {
        Set<String> emails = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                emails.add(line.trim());
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return emails;
    }
    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        return pattern.matcher(email).matches();
    }
    public boolean chat1(String chat,String email) {
        Logger logger = Logger.getLogger(Registration_Login.class.getName());
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("chat.txt"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new MyHandler());
        Handler fileHandler = null;
        try {
            fileHandler = new FileHandler("chat.txt", 1000, 10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileHandler.setFormatter(new MyFormatter());
        fileHandler.setFilter(new MyFilter());
        logger.addHandler(fileHandler);

            System.out.println("Chat wrote");
            logger.info(chat+","+email);

      return true;
    }
}
