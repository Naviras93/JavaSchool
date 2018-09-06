package ejb;

import javax.ejb.Local;

import domain.StudentDomain;
        import jpa.Student;
        import sun.security.validator.ValidatorException;

        import javax.ejb.Local;


@Local
public interface LoginService {
    void submitLogin(String email, String password);
    void setUserRole(String role);
    String getUserRole();
    void setId(Long id);
    Long getId();
}
