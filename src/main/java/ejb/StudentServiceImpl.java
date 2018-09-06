package ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import domain.StudentDomain;
import jpa.Course;
import jpa.Student;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class StudentServiceImpl implements StudentService{

    @PersistenceContext
    EntityManager em;

    @Override
    public void addStudent(StudentDomain Student) {

    }

    @Override
    public void updateStudent(StudentDomain Student) {

    }

    @Override
    public StudentDomain getStudent(Long id) {
        return null;
    }

    @Override
    public void removeStudent(Long id) {

    }

    @Override
    public List<StudentDomain> getStudents() {

        List<Student> list  = em.createNamedQuery("GetAllStudents").getResultList();
        return list.stream().
                map(s->new StudentDomain(s.getId(),s.getFirstname(),s.getLastname(),s.getEmail())).
                collect(Collectors.toList());
    }

    @Override
    public List<StudentDomain> getStudentsFromCourses(long courseId) {
        Course c = em.find(Course.class, courseId);
        em.refresh(c);
        return c.getStudents().stream().
                map(s->new StudentDomain(s.getId(),s.getFirstname(), s.getLastname(), s.getEmail())).
                collect(Collectors.toList());
    }
}
