/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Client;
import entities.Mmoperator;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface ClientFacadeLocal {

    void create(Client client);

    void edit(Client client);

    void remove(Client client);

    Client find(Object id);

    List<Client> findAll();

    List<Client> findRange(int[] range);

    int count();

    int minId();

    List<Mmoperator> findAllOperator(String operat);

    List<Client> findFirstClient();

    public int findToUpdateTotalBox(long idclientinstal);

    public int findCopieDouble(String firstnameToCount, String lastnameToCount);
    
    public long findIdClient(String filtreClientFirstName, String filtreClientLastName);
}
