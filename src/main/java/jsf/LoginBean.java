package jsf;


import ejb.LoginService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String message;
    private String role;
    @EJB
    LoginService loginService;


    @PersistenceContext
    private EntityManager em;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String loginAction() {

        String returnPage = "login";

        loginService.submitLogin(email, password);
        id = loginService.getId();
        role = loginService.getUserRole();

        if(role == "student") {

            returnPage = "registerForCourse";
        }
        else if(role == "teacher"){
            returnPage = "checkAttendance";
        }
        else if(role == "admin"){
            returnPage = "registerUser";
        }
        else
            {
                setPassword("");
                setMessage("Login failed");
            }
        return returnPage + "?faces-redirect=true";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
