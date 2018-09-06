package ejb;

import domain.CourseDomain;
import domain.StudentDomain;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CourseService {
    void updateCourse(CourseDomain course);
    CourseDomain getCourse(Long id);
    void removeCourse(Long id);
    List<CourseDomain> getCourses();
    void registerCourse(Long courseId, Long studentId);
    void unregisterCourse(Long courseId, Long studentId);
    StringBuilder checkRegister(Long courseId, Long studentId);
    List<CourseDomain> getCoursesFromTeacher(Long teacherId);
    void addCourse(String newCourseName, Long teacherId, List<StudentDomain> studentsForCourse);
}
