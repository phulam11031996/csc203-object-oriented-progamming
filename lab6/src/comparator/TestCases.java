package comparator;

import java.lang.reflect.Array;
import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCases
{
   private static final Applicant[] applicants = new Applicant[] {
           new Applicant("Aakash", Arrays.asList(
                   new CourseGrade("Intro to CS", 89),
                   new CourseGrade("Data Structures", 86),
                   new CourseGrade("Operating Systems", 89),
                   new CourseGrade("Non-CS", 92)
           ), 5),
           new Applicant("Sarah", Arrays.asList(
                   new CourseGrade("Intro to CS", 96),
                   new CourseGrade("Data Structures", 94),
                   new CourseGrade("Operating Systems", 86),
                   new CourseGrade("Non-CS", 90)
           ), 5),
           new Applicant("Moe", Arrays.asList(
                   new CourseGrade("Intro to CS", 86),
                   new CourseGrade("Data Structures", 92),
                   new CourseGrade("Operating Systems", 93),
                   new CourseGrade("Non-CS", 89)
           ), 10),
           new Applicant("Sue", Arrays.asList(
                   new CourseGrade("Intro to CS", 82),
                   new CourseGrade("Data Structures", 97),
                   new CourseGrade("Operating Systems", 83),
                   new CourseGrade("Non-CS", 89)
           ), 6),
           new Applicant("Moe", Arrays.asList(
                   new CourseGrade("Intro to CS", 94),
                   new CourseGrade("Data Structures", 87),
                   new CourseGrade("Operating Systems", 73),
                   new CourseGrade("Non-CS", 84)
           ), 5)
   };

   // returns array list of unsorted Applicant
   public ArrayList<Applicant> createApplicantArrayList() {
       ArrayList<Applicant> applicantArrayList = new ArrayList<>();
       applicantArrayList.add(applicants[0]);
       applicantArrayList.add(applicants[1]);
       applicantArrayList.add(applicants[2]);
       applicantArrayList.add(applicants[3]);
       applicantArrayList.add(applicants[4]);

       return applicantArrayList;
   }

    // returns array list of sorted Applicant by name
    public ArrayList<Applicant> createApplicantArrayListSortedByName() {
        ArrayList<Applicant> sortedApplicantArrayList = new ArrayList<>();
        sortedApplicantArrayList.add(applicants[0]);
        sortedApplicantArrayList.add(applicants[2]);
        sortedApplicantArrayList.add(applicants[4]);
        sortedApplicantArrayList.add(applicants[1]);
        sortedApplicantArrayList.add(applicants[3]);
        return sortedApplicantArrayList;
    }

    // returns array list of sorted Applicant by grade average
    public ArrayList<Applicant> createApplicantArrayListSortedByGradeAverage() {
        ArrayList<Applicant> sortedApplicantArrayList = new ArrayList<>();
        sortedApplicantArrayList.add(applicants[4]);
        sortedApplicantArrayList.add(applicants[3]);
        sortedApplicantArrayList.add(applicants[0]);
        sortedApplicantArrayList.add(applicants[2]);
        sortedApplicantArrayList.add(applicants[1]);
        return sortedApplicantArrayList;
    }

    // return array list of sorted Application by years of experience
    public ArrayList<Applicant> createApplicantArrayListSortedByYearsOfExperience() {
        ArrayList<Applicant> sortedApplicantArrayList = new ArrayList<>();
        sortedApplicantArrayList.add(applicants[2]);
        sortedApplicantArrayList.add(applicants[3]);
        sortedApplicantArrayList.add(applicants[0]);
        sortedApplicantArrayList.add(applicants[1]);
        sortedApplicantArrayList.add(applicants[4]);
        return sortedApplicantArrayList;
    }

    // return array list of sorted Application by years of experience then grade average
    public ArrayList<Applicant> createApplicantArrayListSortedByYearsThenGrades() {
        ArrayList<Applicant> sortedApplicantArrayList = new ArrayList<>();
        sortedApplicantArrayList.add(applicants[2]);
        sortedApplicantArrayList.add(applicants[3]);
        sortedApplicantArrayList.add(applicants[4]);
        sortedApplicantArrayList.add(applicants[0]);
        sortedApplicantArrayList.add(applicants[1]);
        return sortedApplicantArrayList;
    }
   @Test
   public void testNameComparator() {
       ArrayList<Applicant> expectedList = createApplicantArrayListSortedByName();
       ArrayList<Applicant> actualList = createApplicantArrayList();
       Collections.sort(actualList, new NameComparator());


       assertEquals(actualList, expectedList);
   }

   @Test
   public void testLambdaAverageComparator()
   {
       Comparator<Applicant> compAverGradeComp = (a1, a2) -> {
           if (a1.getAverageGrade() > a2.getAverageGrade())
               return 1;
           else if (a1.getAverageGrade() < a2.getAverageGrade())
               return -1;
           else
               return 0;
       };

       ArrayList<Applicant> actualList = createApplicantArrayList();
       ArrayList<Applicant> expectedList = createApplicantArrayListSortedByGradeAverage();
       Collections.sort(actualList, compAverGradeComp);

       assertEquals(expectedList, actualList);
   }

   @Test
   public void testYearsOfExperienceExtractorComparator()
   {
       Comparator<Applicant> yearsOfExperience =
               Comparator.comparing(Applicant::getYearsOfExperience).reversed();

       ArrayList<Applicant> actualList = createApplicantArrayList();
       ArrayList<Applicant> expectedList = createApplicantArrayListSortedByYearsOfExperience();
       Collections.sort(actualList, yearsOfExperience);

       assertEquals(expectedList, actualList);
   }

   @Test
   public void testComposedComparator()
   {
       Comparator<Applicant> compYearsOfExperience =
               Comparator.comparing(Applicant::getYearsOfExperience).reversed();

       Comparator<Applicant> compAverGradeComp =
               Comparator.comparing(Applicant::getAverageGrade);

       ArrayList<Applicant> expectedList = createApplicantArrayListSortedByYearsThenGrades();
       ArrayList<Applicant> actualList = createApplicantArrayList();
       Collections.sort(actualList, new ComposedComparator(compYearsOfExperience, compAverGradeComp));

       assertEquals(expectedList, actualList);
   }

   @Test
   public void testThenComparing()
   {
       Comparator<Applicant> thenComparing =
               Comparator.comparing(Applicant::getYearsOfExperience).reversed().thenComparing(Applicant::getAverageGrade);

       ArrayList<Applicant> expectedList = createApplicantArrayListSortedByYearsThenGrades();
       ArrayList<Applicant> actualList = createApplicantArrayList();
       Collections.sort(actualList, thenComparing);

       assertEquals(expectedList, actualList);
   }


   @Test
   public void runSort()
   {
      List<Applicant> applicantList = new ArrayList<>(Arrays.asList(applicants));
      List<Applicant> expectedList = Arrays.asList(
              new Applicant("Aakash", Arrays.asList(
                      new CourseGrade("Intro to CS", 89),
                      new CourseGrade("Data Structures", 86),
                      new CourseGrade("Operating Systems", 89),
                      new CourseGrade("Non-CS", 92)
              ), 5),
              new Applicant("Moe", Arrays.asList(
                      new CourseGrade("Intro to CS", 94),
                      new CourseGrade("Data Structures", 87),
                      new CourseGrade("Operating Systems", 73),
                      new CourseGrade("Non-CS", 84)
              ), 5),
              new Applicant("Moe", Arrays.asList(
                      new CourseGrade("Intro to CS", 86),
                      new CourseGrade("Data Structures", 92),
                      new CourseGrade("Operating Systems", 93),
                      new CourseGrade("Non-CS", 89)
              ), 10),
              new Applicant("Sarah", Arrays.asList(
                      new CourseGrade("Intro to CS", 96),
                      new CourseGrade("Data Structures", 94),
                      new CourseGrade("Operating Systems", 86),
                      new CourseGrade("Non-CS", 90)
              ), 5),
              new Applicant("Sue", Arrays.asList(
                      new CourseGrade("Intro to CS", 82),
                      new CourseGrade("Data Structures", 97),
                      new CourseGrade("Operating Systems", 83),
                      new CourseGrade("Non-CS", 89)
              ), 6)
      );

      Comparator<Applicant> compNameYearsGrade =
              Comparator.comparing(Applicant::getName).thenComparing(Applicant::getYearsOfExperience).thenComparing(Applicant::getAverageGrade);

      Collections.sort(applicantList, compNameYearsGrade);

      assertEquals(expectedList, applicantList);
   }
}
