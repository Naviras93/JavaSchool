package jsf;

import domain.CourseDomain;
import domain.StudentDomain;
import ejb.AttendanceService;
import ejb.CourseService;
import ejb.StudentService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class CheckAttendanceBean {
    private List<StudentDomain> students;
    private List<CourseDomain> courses;
    private Long currentCourseId;
    @EJB
    StudentService studentService;

    @EJB
    CourseService courseService;

    @EJB
    AttendanceService attendanceService;

    public List<StudentDomain> getStudents() {
        return students;
    }

    public void getStudentsFromCourse(long courseId){
        currentCourseId = courseId;
        students = studentService.getStudentsFromCourses(courseId);
    }


    public void getCoursesFromTeacher(long id) {
        courses = courseService.getCoursesFromTeacher(id);
    }

    public List<CourseDomain> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDomain> courses) {
        this.courses = courses;
    }

    public void SaveAttendances(){
        attendanceService.saveAttendances(students, currentCourseId);
    }

    public void changePresence(StudentDomain s){
        if(s.getPresence() != true)
            s.setPresence(true);
        else
            s.setPresence(false);
    }

    public StringBuilder getAttendanceStatistics(Long courseid){
        return attendanceService.getCourseStatistics(courseid);

    }
}
