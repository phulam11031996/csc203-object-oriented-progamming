package equality;
import java.time.LocalTime;
import java.util.Objects;


public class CourseSection
{
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
      final int enrollment, final LocalTime startTime, final LocalTime endTime)
   {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   // TODO: equals and hashCode methods for CourseSection

   @Override
   public boolean equals(Object obj) {
      if (obj == null)
         return false;

      if (this == obj)
         return true;

      if (this.getClass() != obj.getClass())
         return false;

      CourseSection otherCourseSection = (CourseSection) obj;
      return (this.enrollment == otherCourseSection.enrollment &&
                Objects.equals(this.prefix, otherCourseSection.prefix) &&
                Objects.equals(this.number, otherCourseSection.number) &&
                Objects.equals(this.startTime, otherCourseSection.startTime) &&
                Objects.equals(this.endTime, otherCourseSection.endTime));
   }

   @Override
   public int hashCode() {
      int result = 1;

      result = result * 37 + ((this.prefix == null) ? 0 : this.prefix.hashCode());
      result = result * 37 + ((this.number == null) ? 0 : this.number.hashCode());
      result = result * 37 + ((this.startTime == null) ? 0 : this.startTime.hashCode());
      result = result * 37 + ((this.endTime == null) ? 0 : this.endTime.hashCode());
      result = result * 37 + enrollment;

      return result;
   }
}
