package jsf;

import domain.CourseDomain;
import domain.StudentDomain;
import ejb.AttendanceService;
import ejb.CourseService;
import ejb.StudentService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class adminAttendanceBean {
    private List<StudentDomain> students;
    private List<CourseDomain> courses;

    @EJB
    StudentService studentService;

    @EJB
    CourseService courseService;

    @EJB
    AttendanceService attendanceService;



    public List<CourseDomain> getCourses() {
        return courseService.getCourses();
    }
    public List<StudentDomain> getStudents() {
        return students = studentService.getStudents();
    }


    public StringBuilder getCourseAttendanceStatistics(Long courseId){
        return attendanceService.getCourseStatistics(courseId);
    }

    public StringBuilder getStudentAttendanceStatistics(Long studentId){
        return  attendanceService.getStudentStatistics(studentId);
    }

}
