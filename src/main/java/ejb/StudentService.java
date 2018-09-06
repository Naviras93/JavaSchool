package ejb;

import javax.ejb.Local;
import java.util.List;
import domain.StudentDomain;
import jpa.Student;
import javax.ejb.Local;
import java.util.List;


@Local
public interface StudentService {
    void addStudent(StudentDomain Student);
    void updateStudent(StudentDomain Student);
    StudentDomain getStudent(Long id);
    void removeStudent(Long id);
    List<StudentDomain> getStudents();
    List<StudentDomain> getStudentsFromCourses(long courseId);

}
