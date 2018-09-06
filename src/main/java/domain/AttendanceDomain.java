package domain;

import jpa.Course;
import jpa.Student;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class AttendanceDomain {
    private long id;

    @Temporal(TemporalType.DATE)
    private Date date;
    private boolean presence;
    private Course course;
    private Student student;

    public AttendanceDomain(long id, Date date, boolean presence) {
        this.id = id;
        this.date = date;
        this.presence = presence;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
