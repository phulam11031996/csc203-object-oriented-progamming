package comparator;

import java.util.Comparator;

public class NameComparator implements Comparator<Applicant> {
    /**
     * @param s1
     * @param s2
     * @return int
     */
    public int compare(Applicant s1, Applicant s2)
    {
        return s1.getName().compareTo(s2.getName());
    }
}
