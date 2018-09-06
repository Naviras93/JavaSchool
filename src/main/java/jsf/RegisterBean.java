package jsf;

import domain.RegisterDomain;
import ejb.LoginService;
import ejb.RegisterService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class RegisterBean implements Serializable {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String message;
    private String role;
    @EJB
    RegisterService registerService;

    public RegisterBean(String firstname, String lastname, String email, String password, String message, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.message = message;
        this.role = role;
    }

    public RegisterBean() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void register() {
        RegisterDomain rd = new RegisterDomain();
        rd.setFirstname(getFirstname());
        rd.setLastname(getLastname());
        rd.setEmail(getEmail());
        rd.setPassword(getPassword());
        rd.setRole(getRole());
        registerService.registerUser(rd);
        clearRegister();
    }

    private void clearRegister(){
        setFirstname("");
        setLastname("");
        setEmail("");
        setPassword("");
        setMessage("User registered!");
    }
}
