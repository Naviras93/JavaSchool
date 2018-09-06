package ejb;

import domain.RegisterDomain;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegisterService {
    void registerUser(RegisterDomain User);
}
