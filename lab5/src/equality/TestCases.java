package equality;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;


import java.util.Arrays;
import java.time.LocalTime;

import org.junit.Test;

public class TestCases
{
   @Test
   public void testExercise1()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void testExercise2()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(1, 10), LocalTime.of(2, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void testExercise3()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void testExercise4()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 34,
         LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

   // TODO: Write test cases for equals and hashCode in Student.
   //    What would convince you that those methods are working as expected?
   @Test
   public void testExercise1Student()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
              LocalTime.of(1, 10), LocalTime.of(2, 0));

      final List<CourseSection> phuCourses = new ArrayList<>();

      phuCourses.add(one);
      phuCourses.add(two);

      final Student phu1 = new Student("Phu", 25, phuCourses);
      final Student phu2 = new Student("Phu", 25, phuCourses);

      assertTrue(phu1.equals(phu2));
      assertTrue(phu2.equals(phu1));
   }

   @Test
   public void testExercise2Student()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
              LocalTime.of(1, 10), LocalTime.of(2, 0));

      final List<CourseSection> phuCourses = new ArrayList<>();

      phuCourses.add(one);
      phuCourses.add(two);

      final Student phu = new Student("Phu", 25, phuCourses);
      final Student lam = new Student("Lam", 25, phuCourses);

      assertFalse(phu.equals(lam));
      assertFalse(lam.equals(phu));
   }

   @Test
   public void testExercise3Student()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
              LocalTime.of(1, 10), LocalTime.of(2, 0));

      final List<CourseSection> phuCourses = new ArrayList<>();

      phuCourses.add(one);
      phuCourses.add(two);

      final Student phu1 = new Student("Phu", 25, phuCourses);
      final Student phu2 = new Student("Phu", 25, phuCourses);

      assertEquals(phu1.hashCode(), phu2.hashCode());
   }

   @Test
   public void testExercise4Student()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
              LocalTime.of(1, 10), LocalTime.of(2, 0));

      final List<CourseSection> phuCourses = new ArrayList<>();

      phuCourses.add(one);
      phuCourses.add(two);

      final Student phu = new Student("Phu", 25, phuCourses);
      final Student lam = new Student("Lam", 25, phuCourses);

      assertNotEquals(phu.hashCode(), lam.hashCode());
   }
}
