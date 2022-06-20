package equality;

import java.util.List;
import java.util.Objects;

public class Student
{
   private String name;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String name, final int age,
      final List<CourseSection> currentCourses)
   {
      this.name = name;
      this.age = age;
      this.currentCourses = currentCourses;
   }

   // TODO: equals and hashCode methods for Student
   @Override
   public boolean equals(Object obj) {
      if (obj == null)
         return false;

      if (this == obj)
         return true;

      if (this.getClass() != obj.getClass())
         return false;

      Student otherStudent = (Student) obj;
      if (!(Objects.equals(this.name, otherStudent.name) && this.age == otherStudent.age))
         return false;

      if (this.currentCourses.size() != otherStudent.currentCourses.size())
         return false;

      return Objects.equals(this.currentCourses, otherStudent.currentCourses);
   }

   @Override
   public int hashCode() {
      int result = 1;

      result = result * 37 + ((this.name == null) ? 0 : this.name.hashCode());
      result = result * 37 + age;

      for (CourseSection currentCourse : currentCourses){
         result = result * 37 + currentCourse.hashCode();
      }
      return result;
   }

}
