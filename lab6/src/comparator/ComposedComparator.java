package comparator;

import java.util.Comparator;

public class ComposedComparator implements Comparator<Applicant> {

    private Comparator<Applicant> c1;
    private Comparator<Applicant> c2;

    public ComposedComparator(Comparator<Applicant> c1, Comparator<Applicant> c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public int compare(Applicant a1, Applicant a2) {
        return c1.compare(a1, a2) == 0 ? c2.compare(a1, a2) : c1.compare(a1, a2);
    }
}
