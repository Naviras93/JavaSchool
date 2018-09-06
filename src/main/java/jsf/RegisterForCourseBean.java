package jsf;

import domain.CourseDomain;
import ejb.*;
import jpa.Course;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class RegisterForCourseBean {
    private List<CourseDomain> courses;

    @EJB
    CourseService courseService;
    @EJB
    AttendanceService attendanceService;

    public List<CourseDomain> getCourses() {
        return courseService.getCourses();
    }

    public void setCourses(List<CourseDomain> courses) {
        this.courses = courses;
    }


    public void registerCourse(Long courseId, Long studentId){
        courseService.registerCourse(courseId, studentId);

    }

    public void unRegisterCourse(Long courseId, Long studentId){
        courseService.unregisterCourse(courseId, studentId);

    }

    public StringBuilder checkRegister(Long courseId, Long studentId){
        return courseService.checkRegister(courseId, studentId);
    }

    public StringBuilder getAttendanceStatistics(Long courseId, Long studentId){
        return attendanceService.getAttendanceStatistics(courseId, studentId);
    }
}
