package ejb;

import domain.TeacherDomain;
import jpa.Teacher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TeacherServiceImpl implements TeacherService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<TeacherDomain> getTeachers() {
        List<Teacher> list  = em.createNamedQuery("GetAllTeachers").getResultList();
        return list.stream().
                map(t->new TeacherDomain(t.getId(),t.getFirstname(),t.getLastname(),t.getEmail())).
                collect(Collectors.toList());
    }
}
