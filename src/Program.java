import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
class Program {

    //Where the program is actually ran
    static void app()
    {
        String dateString = Utils.readString("Read current date");
        System.out.println("Current date: " + dateString);
        List<Account> accountList = Utils.readAccountList();
        accountList = redistributeMoney(accountList);
        int numberOfMonths = Utils.readInt("Number of months to show total money after ");
        System.out.println("You will have " +futureTotalMoney(accountList,numberOfMonths,dateString) + " RON in "
            + numberOfMonths + " months");
        System.out.println("Or " + futureTotalMoney(accountList,numberOfMonths,"00.00.0000") + " if expire date " +
                "doesn't matter");
    }
    /*
    The function that redistributes money for maximum profit
    This focuses only on the interest rates.
    The function first takes all the money from all the accounts. It the sorts all the interest rates in decreasing order,
    and then distributes the money by interest rate.
    */
    private static List<Account> redistributeMoney(List<Account> accounts)
    {
        int moneyToDistribute = 0;
        ArrayList<InterestIndices> indicesList = new ArrayList<>();
        List<Account> newList = new ArrayList<Account>(accounts);

        for(int indexAccount = 0; indexAccount < newList.size(); indexAccount++)
        {
            Account thisAccount = new Account(newList.get(indexAccount));
            moneyToDistribute += thisAccount.getAmountAvailable();
            thisAccount.setAmountAvailable(0);
            newList.set(indexAccount,thisAccount);
            List<Interest> thisList = new ArrayList<>(thisAccount.getInterestList());
            for(int indexInterest = 0; indexInterest < thisList.size(); indexInterest++)
            {
                Interest thisInterest = thisList.get(indexInterest);
                indicesList.add(new InterestIndices(thisInterest.getInterestRate(),indexAccount,indexInterest));
            }
        }
        indicesList.sort(new SortByInterest());
        int moneyLeft = moneyToDistribute;
        for(InterestIndices theseIndices : indicesList)
        {
             Account thisAccount = new Account(newList.get(theseIndices.accountIndex));
             moneyLeft = thisAccount.fillInterestRange(moneyLeft,theseIndices.interestIndex);
             newList.set(theseIndices.accountIndex,thisAccount);
             if(moneyLeft <= 0)
                 break;
        }
        return newList;
    }

    /*
    This functions calculates all the money you would have if numberOfMonths passed

     */
    private static double futureTotalMoney(List<Account> someAccountList, int numberOfMonths, String currentDate)
    {
        double sumOfMoney=0;
        for(Account thisAccount : someAccountList)
            sumOfMoney += thisAccount.futureMoneyPerAccount(numberOfMonths, currentDate);
        return sumOfMoney;
    }

}
