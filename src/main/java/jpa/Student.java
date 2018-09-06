package jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name="StudentFindByEmailAndPassword", query ="SELECT s FROM Student s WHERE s.email = :email and " +
        "s.password = :password"),
        @NamedQuery(name="GetAllStudents", query="SELECT s FROM Student s ")
        })
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @ManyToMany
    private List<Course> courses;

    @OneToMany()
    private List<Attendance> attendances;

    public Student(){}

    public Student(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(String s) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addCourses(Course course){
        if ( courses == null )
            courses = new ArrayList<>();
        this.courses.add(course);
        course.getStudents().add(this);

    }

    public void removeCourse(Course course){
        this.courses.remove(course);
        course.getStudents().remove(this);

    }

    public void addAttendances(Attendance attendance){
        if ( attendances == null )
            attendances = new ArrayList<>();
        this.attendances.add(attendance);

    }


}

