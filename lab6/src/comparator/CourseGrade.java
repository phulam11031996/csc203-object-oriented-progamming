package comparator;

import java.util.Objects;

/*
 * This file should remain unchanged.
 */
public class CourseGrade {
  private String courseName;
  private int score; 

  /**
   * This is a constructor. It creates a new CourseGrade with the specified scores.
   * @param courseName
   * @param score 
   */
  public CourseGrade(String courseName, int score) {
    this.courseName = courseName;
    this.score = score;
  }

  public String getCourseName() {
    return this.courseName;
  }

  public int getScore() {
    return this.score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CourseGrade that = (CourseGrade) o;
    return score == that.score && Objects.equals(courseName, that.courseName);
  }
}
