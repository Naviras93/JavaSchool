package jpa;

import javax.ejb.Startup;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "selectAll", query = "SELECT c FROM Course c")
})
//selectCoursesFromTeacher fungerar inte
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;


    @ManyToOne
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY) //Check what CascadeType is and if we need it
    private List<Attendance> attendances;

    @ManyToMany
    private List<Student> students;

    public Course(String name) {
        this.name = name;
    }

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public List<Student> getStudents() {
        if ( students == null )
            students = new ArrayList<>();
        return students;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addAttendances(Attendance attendance){
        if ( attendances == null )
            attendances = new ArrayList<>();
        this.attendances.add(attendance);

    }

    public void addStudents(Student student){
        if ( students == null )
            students = new ArrayList<>();
        this.students.add(student);

    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }
}

