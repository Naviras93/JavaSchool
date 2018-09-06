package ejb;


import javax.ejb.Local;

import javax.ejb.Startup;

@Local
public interface GenerateService {
    void generateData();
}
