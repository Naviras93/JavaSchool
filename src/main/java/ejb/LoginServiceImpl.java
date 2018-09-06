package ejb;

import jpa.Admin;
import jpa.Student;
import jpa.Teacher;
import jsf.LoginBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LoginServiceImpl implements LoginService {

    @PersistenceContext
    private EntityManager em;
    private String userRole = "Login";
    private Long id;

    @Override
    public void submitLogin(String email, String password) {

        Query queryS = em.createNamedQuery("StudentFindByEmailAndPassword");
        queryS.setParameter("email", email);
        queryS.setParameter("password", password);
        List<Student> s = queryS.getResultList();
        if (s.size()>0) {
            setId(s.get(0).getId());
            setUserRole("student");
            return;
        }

        Query queryT = em.createNamedQuery("TeacherFindByEmailAndPassword");
        queryT.setParameter("email", email);
        queryT.setParameter("password", password);
        List<Teacher> t = queryT.getResultList();
        if (t.size()>0) {
            setId(t.get(0).getId());
            setUserRole("teacher");
            return;
        }

        Query queryA = em.createNamedQuery("AdminFindByEmailAndPassword");
        queryA.setParameter("email", email);
        queryA.setParameter("password", password);
        List<Admin> a = queryA.getResultList();
        if (a.size()>0) {
            setId(a.get(0).getId());
            setUserRole("admin");
            return;
        }
    }

    @Override
    public void setUserRole(String role) {
        this.userRole = role;
    }

    @Override
    public String getUserRole() {
        return userRole;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
