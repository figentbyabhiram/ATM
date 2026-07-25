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
            File f=new File("./ACCOUNTS/"+accno+"/Details.csv");
            f.createNewFile();
            File f1=new File("./ACCOUNTS/"+accno+"/Balance.csv");
            f1.createNewFile();
            File f2=new File("./ACCOUNTS/"+accno+"/TransactionList.csv");
            f2.createNewFile();
            try (FileWriter writer = new FileWriter("./ACCOUNTS/"+accno+"/Balance.csv")) {
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
    
    public static void setPin(String account, String pin) throws IOException {
        if (account == null) {
            throw new IllegalArgumentException("Account number cannot be null");
        }
        Path accountDir = Paths.get("./PASSWORDS", account);
        Files.createDirectories(accountDir);
        Path pinFile = accountDir.resolve("Pass.csv");
        // Write hashed PIN with buffering
        try (BufferedWriter writer = Files.newBufferedWriter(pinFile,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(Hash.hashPassword(pin));
        }
        // Restrict file permissions to owner only (if supported)
        try {
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-------");
            Files.setPosixFilePermissions(pinFile, perms);
        } catch (UnsupportedOperationException ignored) {
        }
        System.out.println("Pin set successfully.");
    }
            System.out.println("Failed to create account directory.");
        }

    }

}
