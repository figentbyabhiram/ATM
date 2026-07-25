import java.util.*;
import java.io.*;
public class Main5 {
    public static void displayWelcomeScreen()throws NoSuchElementException {
        clearConsol();
        System.out.print(
                      "\n\t\t" + " ****** WELCOME TO ATM! ****** " + "\n" +
                        "\t\t" + " _____________________________ " + "\n" +
                        "\t\t" + "|                             |" + "\n" +
                        "\t\t" + "|____ 1) Login _______________|" + "\n" +
                        "\t\t" + "|                             |" + "\n" +
                        "\t\t" + "|____ 2) Register_____________|" + "\n" +
                        "\t\t" + "|                             |" + "\n" +
                        "\t\t" + "|_____________________________|" + "\n");
        System.out.print("\n\t\tYour Choice: ");
        try (Scanner scan = new Scanner(System.in)) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    try {
                        if(User.signIn()){
                            displayMainMenu();
                           }
                           else{
                            System.out.println("or Password");
                           }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                System.out.println("Enter your age: ");
                int age = scan.nextInt();
                scan.nextLine();  

                System.out.println("Enter your Name: ");
                String Name = scan.nextLine();

                System.out.println("Enter your Aadhaar Number: ");
                String aadhaar = scan.nextLine();

                System.out.println("Enter your Email: ");
                String email = scan.nextLine();

                System.out.println("Enter your Phone Number: ");
                String phno = scan.nextLine();

                String pan = "";
                if (age >= 18) {
                    System.out.println("Enter Permanent Account Number (PAN): ");
                    pan = scan.nextLine();
                }

                System.out.println("Set a Password for your Account: ");
                String pass = scan.nextLine();

                System.out.println("Confirm your Password: ");
                String confirmPass = scan.nextLine();

                if (pass.equals(confirmPass)) {
                    try {
                        Registration.createAccount(Name, aadhaar, email, phno, pan);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Registration.setPin(confirmPass);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Account created successfully!");
                } else {
                    System.out.println("Passwords do not match. Please try again.");
                }
                break;
                default:
                    System.out.print("\n\t\tInvalid Choice!\n");
                    try {
                        System.out.print("\n\nPress any key to continue...");
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    displayWelcomeScreen();
                }
      // scan.close();
        }
            
    }
    public static void displayMainMenu()throws NoSuchElementException {
        clearConsol();
        System.out.print(
                      "\n\t\t" + "*********** MAIN MENU ***********" + "\n" +
                        "\t\t" + " _______________________________ " + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 1) View Balance __________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 2) Withdraw Cash _________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 3) Deposit Money _________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 4) Transfer to Account ___|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 5)Mini Statement _________|" + "\n" + 
                        "\t\t" + "|                               |" + "\n"+
                        "\t\t" + "|____ 6)Change Pin______________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 7) Logout ________________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|_______________________________|" + "\n");
        System.out.print("\n\t\tYour Choice: ");
        try (Scanner scan = new Scanner(System.in)) {
            try{ 
            int choice = scan.nextInt();
            
      
            switch (choice) {
                case 1:
                try{ 
                File f = new File("./ACCOUNTS/" + User.acc + "/balance.csv");
                System.out.println("Your current balance is: " + User.getBalance(f));
                }
                 catch (IOException e) {
                    e.printStackTrace();
                    
                }
                    break;
                case 2:
                handleIOException(() -> User.withdraw());
                    break;
                case 3:
                    try {
                        User.deposit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                     try{
                        User.Transfer();
                     }
                     catch(IOException e){
                        e.printStackTrace();
                     }
                     break;
                case 5:
                      try{
                        User.printTransaction();
                      }
                      catch(IOException e){
                        e.printStackTrace();
                      }
                      break;
                case 6:
                System.out.println("Enter your previous password: ");
                String oldpass = scan.nextLine();
                System.out.println("Enter new password: ");
                String newpass = scan.nextLine();
                boolean isPinChanged = User.changePin(User.acc, oldpass, newpass);
                if (isPinChanged) {
                    System.out.println("PIN changed successfully.");
                } else {
                    System.out.println("Failed to change PIN.");
                }
                break;

                case 7:
                    System.out.print("\n\t\tLogged Out!!\n");
                    try {
                        System.out.print("\n\nPress any key to continue...");
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    displayWelcomeScreen();
                    break;
                default:
                    System.out.print("\n\t\tInvalid Choice!\n");
                    try {
                        System.out.print("\n\nPress any key to continue...");
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    displayMainMenu();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
      // scan.close();
        }
    }
    public  static void clearConsol()
    {
        System.out.print("\033[H\033[2J"); 
        System.out.flush();
    }
    public static void main(String[] args) throws IOException,NoSuchElementException {

        displayWelcomeScreen();
    }
    

}


