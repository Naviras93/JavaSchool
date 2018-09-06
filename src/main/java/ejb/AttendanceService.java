package ejb;

import domain.AttendanceDomain;
import domain.StudentDomain;
import jpa.Course;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Local
public interface AttendanceService {
    StringBuilder getAttendanceStatistics(Long courseId, Long studentId);
    void saveAttendances(List<StudentDomain> students, Long currentCourseId);

    StringBuilder getCourseStatistics(Long courseid);

    StringBuilder getStudentStatistics(Long studentId);

    Set<Date> getAttendanceDatesByCourse(Long courseId);
}
