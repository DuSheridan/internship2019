import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/*Account Class
  Type is the account type, for example: SILVER, GOLD
  interestList is a list of Interests, because it can have any number of interest rates for any number of intervals
  amountAvailable is how much cash we have in an account at the moment
  expDate is the expiration date

 */
public class Account implements AccountValidator{
    private String type;
    private List<Interest> interestList;
    private String expDate;
    private int amountAvailable;

    //Basic Constructor
    public Account(String typeNew, List<Interest> interestListNew, String expDateNew, int amountAvailableNew)
    {
        type = typeNew;
        interestList = new ArrayList<>(interestListNew);
        expDate = expDateNew;
        amountAvailable = amountAvailableNew;
    }
    //Constructor that ignores expDate
    public Account(String typeNew, List<Interest> interestListNew, int amountAvailableNew)
    {
        type = typeNew;
        interestList = new ArrayList<>(interestListNew);
        expDate = "N/A";
        amountAvailable = amountAvailableNew;
    }

    //Copy constructor
    public Account(Account other)
    {
        type = other.type;
        interestList = new ArrayList<>(other.interestList);
        expDate = other.expDate;
        amountAvailable = other.amountAvailable;

    }
    //No parameter constructor, for tests mostly
    public Account()
    {
        type = "Default";
        interestList = new ArrayList<>();
        expDate = "Default expire date";
        amountAvailable = 0;
    }

    //Basic getters and setters
    public String getType() {
        return type;
    }

    public List<Interest> getInterestList() {
        return new ArrayList<>(interestList);
    }

    public String getExpDate() {
        return expDate;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInterestList(List<Interest> interestList)
    {
        this.interestList = new ArrayList<>(interestList);
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }


    //This fills the account with money based on a certain interest from that account
    public int fillInterestRange(int moneyToAdd, int interestIndex)
    {
        int actualMoneyToAdd = Math.min(interestList.get(interestIndex).getIntervalEnd()
                - interestList.get(interestIndex).getIntervalBegin(), moneyToAdd);
        amountAvailable += actualMoneyToAdd;
        return moneyToAdd - actualMoneyToAdd;


    }

    //This function calculates how much time would it take for an account to expire ( in months)
    public int monthsUntilExpire(String currentDate)
    {
        int yearCurrent = Integer.parseInt("" + currentDate.charAt(6) + currentDate.charAt(7) + currentDate.charAt(8)
                + currentDate.charAt(9));
        int yearExpire = Integer.parseInt("" + expDate.charAt(6) + expDate.charAt(7) + expDate.charAt(8) + expDate.charAt(9));

        int yearDifference = yearExpire - yearCurrent;

        int monthCurrent = Integer.parseInt("" + currentDate.charAt(3) + currentDate.charAt(4));
        int monthExpire = Integer.parseInt("" + expDate.charAt(3) + expDate.charAt(4));

        int monthDifference = monthExpire - monthCurrent;
        return yearDifference*12 + monthDifference;
    }

    /*
    This function calculates how much money you would have on this particular account in numberOfMonths, while taking
    into consideration the expire date.
     */
    public double futureMoneyPerAccount(int numberOfMonths,String currentDate)
    {
        int monthsUntilExpire = monthsUntilExpire(currentDate);
        double months = Math.min(monthsUntilExpire,numberOfMonths) + 0.0;
        int currentMoney = amountAvailable;
        double futureMoney = 0;
        int indexList = 0;
        while (currentMoney > 0 && indexList < interestList.size())
        {
            double interest = interestList.get(indexList).getInterestRate();
            int amountOfMoney = Math.min(interestList.get(indexList).getIntervalEnd()
                    - interestList.get(indexList).getIntervalBegin(), currentMoney);
            currentMoney -= amountOfMoney;
            futureMoney += amountOfMoney*(interest/100)*(months/12);
            indexList += 1;
        }
        return futureMoney + amountAvailable;

    }

    //With this we validate the account objects
    @Override
    public void validate() throws AccountException
    {
        if(type.isEmpty())
            throw new AccountException("Type is empty");
        if(amountAvailable < 0)
            throw new AccountException("Amount negative");
        if(expDate.isEmpty())
            throw new AccountException("Expire date is empty");
        if(expDate.length() != 10)
            throw new AccountException("Date format is wrong");
        int end=0;
        if(expDate.matches(".*[a-zA-Z].*"))
            throw new AccountException("Letters not allowed");

        for(Interest currentInterest : interestList)
        {
            if(currentInterest.getIntervalBegin() < end)
                throw new AccountException("Interest ranges overlap or are not sorted");
            end = currentInterest.getIntervalBegin();

        }
    }
}
