package comparator;

import java.util.List;
import java.util.Objects;

public class Applicant {
    private String name;
    private int yearsOfExperience;
    private List<CourseGrade> grades;

    public Applicant(String name, List<CourseGrade> grades) {
        this(name, grades, 0);
    }

    public Applicant(String name, List<CourseGrade> grades, int yearsOfExperience) {
        this.name = name;
        this.grades = grades;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }

    public List<CourseGrade> getGrades() {
        return grades;
    }

    public int getYearsOfExperience() {
        return this.yearsOfExperience;
    }

    /**
     * @return This Applicant's average grade, rounded up to the nearest integer.
     */
    public int getAverageGrade() {
        double sum = 0;
        for (CourseGrade current : this.grades) {
            sum = sum + current.getScore();
        }

        return (int) Math.ceil(sum / this.grades.size());
    }

    public CourseGrade getGradeFor(String course) {
        switch (course) { // Can also do with if-else ladder
            case "Intro to CS":
                return this.grades.get(0);
            case "Data Structures":
                return this.grades.get(1);
            case "Algorithms":
                return this.grades.get(2);
            case "Computer Organization":
                return this.grades.get(3);
            case "Operating Systems":
                return this.grades.get(4);
            case "Non-CS GPA":
                return this.grades.get(5);
            default:
                return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant that = (Applicant) other;

        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.grades, that.grades) &&
                this.yearsOfExperience == that.yearsOfExperience;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.yearsOfExperience + " years of exp., " + this.getAverageGrade() + " average.";
    }
}
