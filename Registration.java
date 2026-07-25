import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Registration {
    public static  String Name;
    public static  String aadhaar;
    public static  String email;
    public static  String phno;
    public static  String pan="";
    static String accno;

    public  static void createAccount(String Name, String aadhaar,String email, String phno, String pan) throws IOException {
        int min = 1000000;
        int max = 9999999;
        int randomNumber;
        
        File accountDir;
        do {
            randomNumber = (int) (Math.random() * (max - min + 1) + min);
            accno = randomNumber + "";
            accountDir = new File("./ACCOUNTS/" + accno);
        } while (accountDir.exists());


        if (accountDir.mkdirs()) {
            Path accountPath = java.nio.file.Paths.get("ACCOUNTS", accno);
            java.nio.file.Files.createDirectories(accountPath);
            java.nio.file.Path detailsPath = accountPath.resolve("Details.csv");
            java.nio.file.Path balancePath = accountPath.resolve("Balance.csv");
            java.nio.file.Path transactionPath = accountPath.resolve("TransactionList.csv");
            java.nio.file.Files.createFile(detailsPath);
            java.nio.file.Files.createFile(balancePath);
            java.nio.file.Files.createFile(transactionPath);
            try (java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(balancePath)) {
                writer.write("0");
                writer.close();
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }
            try (FileWriter writer = new FileWriter("./ACCOUNTS/"+accno+"/Details.csv")) {
            writer.write(Name+","+aadhaar+","+email+","+phno+","+pan);
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
            System.out.println("Account created successfully with Account Number: " + accno);
        } else {
            System.out.println("Failed to create account directory.");
        }
    }
    
    public static void setPin(String pin) throws IOException{
        File accountDir;
        accountDir = new File("./PASSWORDS/" + accno);
        if (accountDir.mkdirs()) {
            File f=new File("./PASSWORDS/"+accno+"/Pass.csv");
            f.createNewFile();
            try (FileWriter writer = new FileWriter("./PASSWORDS/"+accno+"/Pass.csv")) {
            writer.write(Hash.hashPassword(pin));
            System.out.println("Pin set successfully.");
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
        } else {
            System.out.println("Failed to create account directory.");
        }

    }

}
