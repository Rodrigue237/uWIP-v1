/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Box;
import entities.Client;
import entities.Distributor;
import entities.Note;
import entities.Organization;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface NoteFacadeLocal {

    void create(Note note);

    void edit(Note note);

    void remove(Note note);

    Note find(Object id);

    List<Note> findAll();

    List<Note> findRange(int[] range);

    List<Organization> findAllOrganization();

    List<Box> findAllBox();

    List<Client> findAllClient();

    List<Distributor> findAllDistributor();

    int count();
}
