package part2;

import part1.CourseGrade;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.List;

public class Applicant {

    private final String name;
    private final List<CourseGrade> grades;
    private final List<String> languages;

    public Applicant(String name, List<CourseGrade> grades, List<String> languages){
        this.name = name;
        this.grades = grades;
        this.languages = languages;
    }

    public String getName(){
        return this.name;
    }

    public List getLanguages(){
        return this.languages;
    }

    public List<CourseGrade> getGrades(){
        return this.grades;
    }

    public CourseGrade getGradeFor(String course){
        if (course.equals("Intro to CS")) { return this.grades.get(0); }
        if (course.equals("Data Structures")) { return this.grades.get(1); }
        if (course.equals("Algorithms")) { return this.grades.get(2); }
        if (course.equals("Computer Organization")) { return this.grades.get(3); }
        if (course.equals("Operating Systems")) { return this.grades.get(4); }
        return this.grades.get(5);
    }
}
