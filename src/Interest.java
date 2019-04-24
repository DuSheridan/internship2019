/* This class pairs interest rates with their amount intervals, since they depend on each other
   interestRate
   intervalBegin is the left-most side of the (closed) interval
   intervalEnd is the right-most element of the (closed) interval
 */

import java.util.ArrayList;
import java.util.List;

public class Interest implements InterestValidator{
    private double interestRate;
    private int intervalBegin;
    private int intervalEnd;

    //Basic Constructor
    public Interest(double interestRateNew, int intervalBeginNew, int intervalEndNew)
    {
        interestRate = interestRateNew;
        intervalBegin = intervalBeginNew;
        intervalEnd = intervalEndNew;
    }
    //Copy constructor
    public Interest(Interest other)
    {
        interestRate = other.interestRate;
        intervalBegin = other.intervalBegin;
        intervalEnd = other.intervalEnd;

    }
    //No parameter constructor, mostly for testing
    public Interest()
    {
        interestRate = 0.0;
        intervalBegin = 0;
        intervalEnd = 0;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getIntervalBegin() {
        return intervalBegin;
    }

    public int getIntervalEnd() {
        return intervalEnd;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setIntervalBegin(int intervalBegin) {
        this.intervalBegin = intervalBegin;
    }

    public void setIntervalEnd(int intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    @Override
    public void validate() throws InterestException
    {
        if(intervalBegin < 0)
            throw new InterestException("Invalid intervalBegin");
        if(intervalEnd < 0 || intervalEnd < intervalBegin)
            throw new InterestException("Invalid intervalEnd");

    }
}