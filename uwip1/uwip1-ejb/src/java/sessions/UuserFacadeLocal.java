/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Uuser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface UuserFacadeLocal {

    void create(Uuser uuser);

    void edit(Uuser uuser);

    void remove(Uuser uuser);

    Uuser find(Object id);

    List<Uuser> findAll();

    List<Uuser> findRange(int[] range);

    List<Uuser> findAll2(String profilSA);

    int count();

    public Uuser connexion(String login, String mdp);

    public int findCopieDouble(String loginToCount);
}
