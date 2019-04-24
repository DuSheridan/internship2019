import java.util.Comparator;

public class InterestIndices {
     double interest;
     int accountIndex;
     int interestIndex;

    public InterestIndices(double interest, int accountIndex, int interestIndex) {
        this.interest = interest;
        this.accountIndex = accountIndex;
        this.interestIndex = interestIndex;
    }

    public InterestIndices(InterestIndices other)
    {
        this.interest = other.interest;
        this.accountIndex = other.accountIndex;
        this.interestIndex = other.interestIndex;
    }

    public double getInterest() {
        return interest;
    }

    public int getAccountIndex() {
        return accountIndex;
    }

    public int getInterestIndex() {
        return interestIndex;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setAccountIndex(int accountIndex) {
        this.accountIndex = accountIndex;
    }

    public void setInterestIndex(int interestIndex) {
        this.interestIndex = interestIndex;
    }
}