package ejb;

import domain.RegisterDomain;
import jpa.Student;
import jpa.Teacher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class RegisterServiceImpl implements RegisterService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void registerUser(RegisterDomain User) {
        //Kan skrivas om för att se finare ut senare
        if(User.getRole().equals("Teacher")){
            Teacher t = new Teacher();
            t.setFirstname(User.getFirstname());
            t.setLastname(User.getLastname());
            t.setEmail(User.getEmail());
            t.setPassword(User.getPassword());
            em.persist(t);
        }
        else if(User.getRole().equals("Student")){
            Student s = new Student();
            s.setFirstname(User.getFirstname());
            s.setLastname(User.getLastname());
            s.setEmail(User.getEmail());
            s.setPassword(User.getPassword());
            em.persist(s);
        }
    }
}
