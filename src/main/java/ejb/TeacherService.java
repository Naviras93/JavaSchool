package ejb;

import domain.TeacherDomain;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TeacherService {
     List<TeacherDomain> getTeachers();
}
