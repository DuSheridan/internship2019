import java.util.Comparator;

class SortByInterest implements Comparator<InterestIndices>
{
    // Used for sorting in ascending order of
    // interest rate
    public int compare(InterestIndices a, InterestIndices b)
    {
        return (int)((b.interest - a.interest)*100);
    }
}
