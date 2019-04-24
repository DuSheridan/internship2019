import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//Read functions used throughout the program
public class Utils {

    //Reads an int using Scanner
    public static int readInt(String messageToShow)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println(messageToShow);
        int readInt;
        try {
            readInt = scan.nextInt();
        }
        catch(Exception exc) {
            System.out.println(exc.getMessage());
            return readInt("Try again, input invalid");
        }
        return readInt;
    }

    //Reads a double using Scanner
    public static double readDouble(String messageToShow)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println(messageToShow);
        double readDouble;
        try {
            readDouble = scan.nextDouble();
        }
        catch(Exception exc) {
            System.out.println(exc.getMessage());
            return readDouble("Try again, input invalid");
        }
        return readDouble;
    }

    //Reads a String using Scanner
    public static String readString(String messageToShow)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println(messageToShow);
        String readString;
        try {
            readString = scan.nextLine();
        }
        catch(Exception exc) {
            System.out.println(exc.getMessage());
            return readString("Try again, input invalid");
        }

        return readString;
    }

    //This returns the Account list in the example
    public static List<Account> readDefaultAccList()
    {
        List<Account> someList = new ArrayList<Account>();
        List<Interest> interestListSilver = new ArrayList<>();
        Interest interestSilver1 = new Interest(0.3,0,500);
        Interest interestSilver2 = new Interest(0.2,500,5000);
        interestListSilver.add(interestSilver1);
        interestListSilver.add(interestSilver2);
        Account accountSilver = new Account("Silver",interestListSilver,"23.05.2020",5000);

        List<Interest> interestListGold = new ArrayList<>();
        Interest interestGold1 = new Interest(0.6,0,500);
        Interest interestGold2 = new Interest(0.4,500,5000);
        interestListGold.add(interestGold1);
        interestListGold.add(interestGold2);
        Account accountGold = new Account("Gold",interestListGold,"05.07.2020",700);

        List<Interest> interestListPlatinum = new ArrayList<>();
        Interest interestPlatinum1 = new Interest(0.9,0,500);
        Interest interestPlatinum2 = new Interest(0.5,500,5000);
        interestListPlatinum.add(interestPlatinum1);
        interestListPlatinum.add(interestPlatinum2);
        Account accountPlatinum = new Account("Platinum", interestListPlatinum,"15.03.2020",300);
        someList.add(accountSilver);
        someList.add(accountGold);
        someList.add(accountPlatinum);

        return someList;

    }
    //This is used to read the list of accounts that is used in the program
    //Can choose to get the default(example) accounts
    public static List<Account> readAccountList()
    {
        List<Account> someList = new ArrayList<Account>();
        int choice = readInt("Press 0 for default values, any other number for new ones: ");
        if(choice == 0)
        {
            someList = readDefaultAccList();
        }
        else {
            int numberOfAccounts = Utils.readInt("Enter the number of accounts: ");
            for (int i = 0; i < numberOfAccounts; i++) {
                System.out.println("Enter the details for account " + i);
                String type = Utils.readString("Enter the account type: ");

                List<Interest> interestList = readInterestList();
                String expDate = Utils.readString("Enter the account expiration date: ");
                int amountAvailable = Utils.readInt("Enter the amount available in the account: ");
                Account account = new Account(type, interestList, expDate, amountAvailable);
                try {
                    account.validate();
                    someList.add(account);
                } catch (AccountException exc) {
                    System.out.println(exc.getMessage());
                    System.out.println("Account invalid, try again");
                }
            }
        }
        return someList;
    }
    //This gets you a list of interests
    public static List<Interest> readInterestList() {
        List<Interest> someList = new ArrayList<Interest>();
        int numberOfInterests = Utils.readInt("Enter the number of interest rates: ");
        int i = 0;
        while(i < numberOfInterests)
        {
            System.out.println("Enter the details for the interest rate: " + i);
            double interestRate = readDouble("Enter the interest rate: ");
            int interestBegin = readInt("Enter the begin of the interest rate: ");
            int interestEnd = readInt("Enter the end of the interest rate: ");
            Interest interest = new Interest(interestRate,interestBegin,interestEnd);
            try {
                interest.validate();
                someList.add(interest);
                i += 1;
            }
            catch(InterestException exc) {
                System.out.println(exc.getMessage());
                System.out.println("Interest invalid, try again");
            }

        }
        return someList;
    }
}
