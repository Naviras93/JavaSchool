package jsf;

import domain.StudentDomain;
import domain.TeacherDomain;
import ejb.CourseService;
import ejb.StudentService;
import ejb.TeacherService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CreateCourseBean {
    private String newCourseName;
    private Long teacherId;
    private List<TeacherDomain> teachers;
    private List<StudentDomain> studentsFromDb;
    private List<StudentDomain> studentsForCourse = new LinkedList<>();

    @EJB
    TeacherService teacherService;

    @EJB
    StudentService studentService;

    @EJB
    CourseService courseService;

    public void createCourse(){
        courseService.addCourse(newCourseName,teacherId, studentsForCourse);
    }

    public void addStudentToCourse(Long studentId){
        for(StudentDomain s: studentsForCourse) {
            if (s.getId() == studentId)
                return;
        }

        int index = 0;
        for(StudentDomain st: studentsFromDb){
            if(st.getId() == studentId){
                studentsForCourse.add(studentsFromDb.get(index));
                return;
            }
            index++;
        }


    }
    public void removeStudentToCourse(Long studentId){

        if(studentsForCourse.size() > 0)
        {
            for(StudentDomain s: studentsForCourse)
            {
                if(s.getId() == studentId)
                {
                    studentsForCourse.remove(s);
                }
            }
        }
    }

    public String ifStudentIsPicked(Long studentId){

        for(StudentDomain s: studentsForCourse) {
            if (s.getId() == studentId) {
                return "Student is in the course";
            }
        }
        return "Student is not in the course";
    }

    public String getNewCourseName() {
        return newCourseName;
    }

    public void setNewCourseName(String newCourseName) {
        this.newCourseName = newCourseName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<TeacherDomain> getTeachers() {
        return teachers = teacherService.getTeachers();
    }

    public void setTeachers(List<TeacherDomain> teachers) {
        this.teachers = teachers;
    }

    public List<StudentDomain> getStudentsFromDb() {
        return studentsFromDb = studentService.getStudents();
    }

    public void setStudentsFromDb(List<StudentDomain> studentsFromDb) {
        this.studentsFromDb = studentsFromDb;
    }

    public List<StudentDomain> getStudentsForCourse() {
        return studentsForCourse;
    }

    public void setStudentsForCourse(List<StudentDomain> studentsForCourse) {
        this.studentsForCourse = studentsForCourse;
    }
}
