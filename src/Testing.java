import java.util.ArrayList;
import java.util.List;

public class Testing {

    public static void test()
    {
        assert(true);
        Interest thisInterest1 = new Interest();
        Interest thisInterest2 = new Interest(thisInterest1);

        assert(thisInterest1.getInterestRate() == 0.0);
        assert(thisInterest2.getInterestRate() == 0.0);

        thisInterest2.setInterestRate(0.2);
        assert(thisInterest2.getInterestRate() == 0.2);
        assert(thisInterest1.getInterestRate() == 0.0);

        List<Interest> intList = new ArrayList<>();
        intList.add(thisInterest1);
        intList.add(thisInterest2);
        assert(intList.get(1).getInterestRate() == 0.2);



        Account thisAccount = new Account("Gold",intList,"some exp date",1000);

        assert(thisAccount.getAmountAvailable() == 1000);
        assert(thisAccount.getInterestList().get(1).getInterestRate() == 0.2);
        assert(thisAccount.getInterestList().get(0).getInterestRate() == 0.0);

        Interest thisInterest3 = new Interest(1,0,2000);
        intList.add(thisInterest3);
        thisAccount.setInterestList(intList);
        assert(thisAccount.fillInterestRange(2000, 2) == 1000);
        assert(thisAccount.getAmountAvailable() == 2000);



        assert(thisAccount.futureMoneyPerAccount(12,"20.02.2020") == 2020);
        assert(thisAccount.futureMoneyPerAccount(24,"20.02.2020") == 2040);
        System.out.println("Tests passed");
    }
}
